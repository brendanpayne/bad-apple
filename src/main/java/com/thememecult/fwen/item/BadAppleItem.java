package com.thememecult.fwen.item;

import com.thememecult.fwen.BadApple;
import com.thememecult.fwen.FwenEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class BadAppleItem extends Item {
    public BadAppleItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        // Apply server-side so the effect is synced to the client normally,
        // which is what lets the overlay survive a relog.
        if (!level.isClientSide()) {
            entity.addEffect(new MobEffectInstance(
                    FwenEffects.BAD_APPLE,
                    BadApple.DURATION_TICKS,
                    0,
                    false,  // ambient
                    false,  // visible particles
                    true    // show icon
            ));
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
