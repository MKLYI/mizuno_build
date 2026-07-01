package com.ligrk.mizunoworkshop.registry;

import com.ligrk.mizunoworkshop.MizunoWorkshop;
import com.ligrk.mizunoworkshop.menu.MizunoWorkshopMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, MizunoWorkshop.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<MizunoWorkshopMenu>> MIZUNO_WORKSHOP =
            MENUS.register("mizuno_workshop", () -> new MenuType<>(MizunoWorkshopMenu::new, FeatureFlags.DEFAULT_FLAGS));

    private ModMenus() {
    }
}
