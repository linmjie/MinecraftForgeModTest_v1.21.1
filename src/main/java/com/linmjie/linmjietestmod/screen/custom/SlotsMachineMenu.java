package com.linmjie.linmjietestmod.screen.custom;

import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.block.entity.custom.ATMBlockEntity;
import com.linmjie.linmjietestmod.block.entity.custom.SlotsMachineBlockEntity;
import com.linmjie.linmjietestmod.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public class SlotsMachineMenu extends AbstractContainerMenu {
    public final SlotsMachineBlockEntity blockEntity;
    private final Level level;

    public SlotsMachineMenu(int pContainerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(pContainerId, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()));
    }
    public SlotsMachineMenu(int pContainerId, Inventory inventory, BlockEntity blockEntity) {
        super(ModMenuTypes.SLOTS_MACHINE_MENU.get(), pContainerId);
        this.blockEntity = (SlotsMachineBlockEntity) blockEntity;
        this.level = inventory.player.level();

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.ATM.get());
    }

    private void addPlayerInventory(Inventory playerInventory){
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i *18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory){
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
