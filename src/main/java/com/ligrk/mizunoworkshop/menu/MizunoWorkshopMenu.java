package com.ligrk.mizunoworkshop.menu;

import com.ligrk.mizunoworkshop.registry.ModBlocks;
import com.ligrk.mizunoworkshop.registry.ModMenus;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class MizunoWorkshopMenu extends AbstractContainerMenu {
    private static final int INPUT_SLOT = 0;
    private static final int RESULT_SLOT = 1;

    private final ContainerLevelAccess access;
    private final SingleItemContainer input = new SingleItemContainer();
    private final Container result = new SimpleContainer(1);

    public MizunoWorkshopMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public MizunoWorkshopMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(ModMenus.MIZUNO_WORKSHOP.get(), containerId);
        this.access = access;

        addSlot(new Slot(input, 0, 20, 33) {
            @Override
            public void setChanged() {
                super.setChanged();
                updateResult();
            }
        });
        addSlot(new MizunoResultSlot(this, result, 0, 143, 33));

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(inventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }

        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(inventory, column, 8 + column * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, ModBlocks.MIZUNO_WORKSHOP.get());
    }

    @Override
    public void slotsChanged(Container container) {
        updateResult();
        super.slotsChanged(container);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack copied = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot == null || !slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        copied = stack.copy();

        if (index == RESULT_SLOT) {
            if (!moveItemStackTo(stack, 2, 38, true)) {
                return ItemStack.EMPTY;
            }
            slot.onQuickCraft(stack, copied);
        } else if (index == INPUT_SLOT) {
            if (!moveItemStackTo(stack, 2, 38, false)) {
                return ItemStack.EMPTY;
            }
        } else if (canConvert(stack)) {
            if (!moveItemStackTo(stack, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < 29) {
            if (!moveItemStackTo(stack, 29, 38, false)) {
                return ItemStack.EMPTY;
            }
        } else if (!moveItemStackTo(stack, 2, 29, false)) {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        if (stack.getCount() == copied.getCount()) {
            return ItemStack.EMPTY;
        }

        slot.onTake(player, stack);
        return copied;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        clearContainer(player, input);
    }

    void consumeInput() {
        input.removeItem(INPUT_SLOT, 1);
        updateResult();
    }

    private void updateResult() {
        ItemStack inputStack = input.getItem(INPUT_SLOT);
        Optional<ItemStack> output = getConversion(inputStack);
        result.setItem(0, output.orElse(ItemStack.EMPTY));
        broadcastChanges();
    }

    private static boolean canConvert(ItemStack stack) {
        return getConversion(stack).isPresent();
    }

    private static Optional<ItemStack> getConversion(ItemStack stack) {
        if (!(stack.getItem() instanceof BlockItem blockItem)) {
            return Optional.empty();
        }

        Block inputBlock = blockItem.getBlock();
        Supplier<? extends Block> outputBlock = ModBlocks.CONVERSIONS.get(inputBlock);
        if (outputBlock == null) {
            return Optional.empty();
        }

        return Optional.of(new ItemStack(outputBlock.get()));
    }
}
