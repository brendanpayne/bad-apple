package com.thememecult.fwen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class FwenEffects {
    private FwenEffects() {}

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Fwen.MOD_ID);

    public static final DeferredHolder<MobEffect, BadAppleEffect> BAD_APPLE =
            EFFECTS.register("bad_apple", BadAppleEffect::new);

    /** Does nothing but mark the player. All behaviour is client-side rendering. */
    public static class BadAppleEffect extends MobEffect {
        public BadAppleEffect() {
            super(MobEffectCategory.HARMFUL, 0x000000);
        }
    }
}
