package com.thememecult.fwen.client;

import com.thememecult.fwen.BadApple;
import com.thememecult.fwen.Fwen;
import com.thememecult.fwen.FwenEffects;
import com.thememecult.fwen.FwenSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

/**
 * Starts the track when the effect is first applied and stops it when the
 * effect goes away, whether that is milk, death or the duration running out.
 */
@EventBusSubscriber(modid = Fwen.MOD_ID, value = Dist.CLIENT)
public final class BadAppleSound {
    private BadAppleSound() {}

    /** How fresh an effect must be for us to assume it was just eaten. */
    private static final int FRESH_TICKS = 20;

    private static SoundInstance playing;
    /** Elapsed ticks seen last tick, so a refreshed effect can be detected. */
    private static int lastElapsed = Integer.MAX_VALUE;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            stop(mc);
            return;
        }

        MobEffectInstance effect = mc.player.getEffect(FwenEffects.BAD_APPLE);
        if (effect == null) {
            stop(mc);
            return;
        }

        int elapsed = BadApple.DURATION_TICKS - effect.getDuration();
        // Lower bound matters for /effect give with a duration longer than video
        boolean fresh = elapsed >= 0 && elapsed <= FRESH_TICKS;
        boolean wasFresh = lastElapsed >= 0 && lastElapsed <= FRESH_TICKS;

        // Eating a second Bad Apple refreshes the duration to full
        if (fresh && !wasFresh) {
            stop(mc);
            start(mc);
        }
        lastElapsed = elapsed;
    }

    private static void start(Minecraft mc) {
        // Relative, unattenuated, at the listener
        playing = new SimpleSoundInstance(
                FwenSounds.BAD_APPLE.get().getLocation(),
                SoundSource.RECORDS,
                1.0F, 1.0F,
                RandomSource.create(),
                false, 0,
                SoundInstance.Attenuation.NONE,
                0.0D, 0.0D, 0.0D,
                true);
        mc.getSoundManager().play(playing);
    }

    private static void stop(Minecraft mc) {
        lastElapsed = Integer.MAX_VALUE;
        if (playing != null) {
            mc.getSoundManager().stop(playing);
            playing = null;
        }
    }
}
