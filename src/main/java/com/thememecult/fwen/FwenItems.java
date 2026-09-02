package com.thememecult.fwen;

import com.thememecult.fwen.item.BadAppleItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class FwenItems {
    private FwenItems() {}

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Fwen.MOD_ID);

    private static final FoodProperties BAD_APPLE_FOOD = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.3F)
            .alwaysEdible()
            .build();

    public static final DeferredHolder<Item, BadAppleItem> BAD_APPLE =
            ITEMS.register("bad_apple", () -> new BadAppleItem(new Item.Properties().food(BAD_APPLE_FOOD)));
}
