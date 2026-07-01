package com.ligrk.mizunoworkshop;

import com.ligrk.mizunoworkshop.registry.ModBlocks;
import com.ligrk.mizunoworkshop.registry.ModCreativeTabs;
import com.ligrk.mizunoworkshop.registry.ModMenus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(MizunoWorkshop.MOD_ID)
public final class MizunoWorkshop {
    public static final String MOD_ID = "mizuno_build";

    public MizunoWorkshop(IEventBus modEventBus) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
    }
}
