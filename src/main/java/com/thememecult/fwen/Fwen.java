package com.thememecult.fwen;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

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
