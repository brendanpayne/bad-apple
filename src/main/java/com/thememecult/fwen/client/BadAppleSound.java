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
 *
 * <p>Minecraft's sound engine cannot seek, so audio is only started for a
 * freshly applied effect. Rejoining midway leaves you with a silent video,
 * which is the honest outcome rather than a track that is minutes out of sync.
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
        boolean fresh = elapsed <= FRESH_TICKS;
        boolean wasFresh = lastElapsed <= FRESH_TICKS;

        // Eating a second Bad Apple refreshes the duration to full, which sends
        // the video back to frame 0. Entering the fresh window from outside it
        // is how we spot that, so the track restarts with the video instead of
        // carrying on minutes ahead of it. Testing the edge rather than the
        // level keeps duration jitter from the server resync out of it.
        if (fresh && !wasFresh) {
            stop(mc);
            start(mc);
        }

        // After stop(), which clears this back to "no effect seen".
        lastElapsed = elapsed;
    }

    private static void start(Minecraft mc) {
        // Relative, unattenuated, at the listener: a non-positional track that
        // still answers to the Jukebox/Records volume slider.
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
