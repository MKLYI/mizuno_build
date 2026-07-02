package com.ligrk.mizunoworkshop.registry;

import com.ligrk.mizunoworkshop.MizunoWorkshop;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MizunoWorkshop.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MIZUNO_BUILD_TAB =
            CREATIVE_MODE_TABS.register("mizuno_build", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.mizuno_build"))
                    .icon(() -> ModItems.MIZUNO_ELEMENT.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.MIZUNO_ELEMENT.get());
                        ModBlocks.mizunoBlocks().forEach(block -> output.accept(block.output().get()));
                    })
                    .build());

    private ModCreativeTabs() {
    }
}
