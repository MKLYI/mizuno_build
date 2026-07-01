package com.ligrk.mizunoworkshop.menu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

final class MizunoResultSlot extends Slot {
    private final MizunoWorkshopMenu menu;

    MizunoResultSlot(MizunoWorkshopMenu menu, Container container, int slot, int x, int y) {
        super(container, slot, x, y);
        this.menu = menu;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        menu.consumeInput();
        super.onTake(player, stack);
    }
}
