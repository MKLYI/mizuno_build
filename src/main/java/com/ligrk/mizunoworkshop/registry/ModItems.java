package com.ligrk.mizunoworkshop.registry;

import com.ligrk.mizunoworkshop.MizunoWorkshop;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MizunoWorkshop.MOD_ID);

    // Mizuno 方块合成消耗的基础材料。
    public static final DeferredItem<Item> MIZUNO_ELEMENT =
            ITEMS.register("mizuno_element", () -> new Item(new Item.Properties()));

    private ModItems() {
    }
}
