package com.thememecult.fwen;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

/**
 * Common entry point. Everything registered here exists on both sides;
 * rendering lives in {@code com.thememecult.fwen.client} and is never
 * classloaded on a dedicated server.
 */
@Mod(Fwen.MOD_ID)
public class Fwen {
    public static final String MOD_ID = "fwen";

    public Fwen(IEventBus modBus, ModContainer container) {
        FwenEffects.EFFECTS.register(modBus);
        FwenItems.ITEMS.register(modBus);
        FwenSounds.SOUNDS.register(modBus);
        FwenCreativeTabs.TABS.register(modBus);
    }
}
