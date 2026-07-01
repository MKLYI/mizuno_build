package com.ligrk.mizunoworkshop.menu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

final class SingleItemContainer implements Container {
    private ItemStack item = ItemStack.EMPTY;

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return item.isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return item;
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        if (item.isEmpty() || amount <= 0) {
            return ItemStack.EMPTY;
        }
        ItemStack removed = item.split(amount);
        if (item.isEmpty()) {
            item = ItemStack.EMPTY;
        }
        setChanged();
        return removed;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack removed = item;
        item = ItemStack.EMPTY;
        return removed;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        item = stack;
        if (!item.isEmpty() && item.getCount() > getMaxStackSize()) {
            item.setCount(getMaxStackSize());
        }
        setChanged();
    }

    @Override
    public void setChanged() {
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        item = ItemStack.EMPTY;
    }
}
