package com.thememecult.fwen;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class FwenCreativeTabs {
    private FwenCreativeTabs() {}

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Fwen.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FWEN =
            TABS.register("fwen", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.fwen"))
                    .icon(() -> new ItemStack(FwenItems.BAD_APPLE.get()))
                    .displayItems((params, output) -> output.accept(FwenItems.BAD_APPLE.get()))
                    .build());
}
