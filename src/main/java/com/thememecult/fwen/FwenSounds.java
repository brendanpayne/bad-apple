package com.thememecult.fwen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class FwenSounds {
    private FwenSounds() {}

    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, Fwen.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> BAD_APPLE =
            SOUNDS.register("bad_apple", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(Fwen.MOD_ID, "bad_apple")));
}
