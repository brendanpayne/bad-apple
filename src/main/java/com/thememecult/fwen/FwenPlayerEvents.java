package com.thememecult.fwen;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Fwen.MOD_ID)
public final class FwenPlayerEvents {
    private FwenPlayerEvents() {}

    /** Clear on logout to prevent desync. */
    @SubscribeEvent
    public static void onLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        clear(event.getEntity());
    }

    /** Safety net for sessions that ended without a clean logout. */
    @SubscribeEvent
    public static void onLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        clear(event.getEntity());
    }

    private static void clear(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.removeEffect(FwenEffects.BAD_APPLE);
        }
    }
}
