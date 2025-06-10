package com.linmjie.linmjietestmod.screen.custom;

import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.block.entity.custom.ATMBlockEntity;
import com.linmjie.linmjietestmod.component.ModDataComponentTypes;
import com.linmjie.linmjietestmod.item.ModItems;
import com.linmjie.linmjietestmod.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ATMMenu extends AbstractContainerMenu {
    public final ATMBlockEntity blockEntity;
    private final Level level;

    private static final int CARD_SLOT = 0;
    private static final int DEPOSIT_SLOT = 1;
    private static final int WITHDRAW_SLOT = 2;

    public ATMMenu(int pContainerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(pContainerId, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public ATMMenu(int pContainerId, Inventory inventory, BlockEntity blockEntity){
        super(ModMenuTypes.ATM_MENU.get(), pContainerId);
        this.blockEntity = (ATMBlockEntity) blockEntity;
        this.level = inventory.player.level();

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, CARD_SLOT, 114, 18){
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.is(ModItems.BANK_CARD.get());
            }
        });

        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, DEPOSIT_SLOT, 54, 18){
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.is(Items.EMERALD);
            }
        });

        this.addSlot(new SlotItemHandler(this.blockEntity.inventory, WITHDRAW_SLOT, 114, 61){
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        });
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 3;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean clickMenuButton(Player pPlayer, int pId) {
        if (pId < 4) {
            ItemStack card = this.blockEntity.inventory.getStackInSlot(CARD_SLOT);
            ItemStack withdrawal = this.blockEntity.inventory.getStackInSlot(WITHDRAW_SLOT);
            if(card.get(ModDataComponentTypes.EMERALDS_ACCOUNT.get()) != null && withdrawal.getCount() != 64) {
                int emeraldsToWithdraw = pId == 0 ? 1:
                                        pId == 1 ? 8:
                                        pId == 2 ? 32:
                                                   64;
                int emeraldsInAccount = card.get(ModDataComponentTypes.EMERALDS_ACCOUNT.get());

                if(emeraldsInAccount < emeraldsToWithdraw) {
                    emeraldsToWithdraw = card.get(ModDataComponentTypes.EMERALDS_ACCOUNT.get());
                }
                if((withdrawal.getCount() + emeraldsToWithdraw) >= 64){
                    emeraldsToWithdraw = 64 - withdrawal.getCount();
                }

                if(withdrawal.is(Items.EMERALD)) {
                    withdrawal.grow(emeraldsToWithdraw);
                } else {
                    this.blockEntity.inventory.setStackInSlot(WITHDRAW_SLOT, new ItemStack(Items.EMERALD, emeraldsToWithdraw));
                }
                card.set(ModDataComponentTypes.EMERALDS_ACCOUNT.get(), (emeraldsInAccount - emeraldsToWithdraw) != 0 ?
                        emeraldsInAccount - emeraldsToWithdraw : null);
                this.blockEntity.getLevel().playSound(null, this.blockEntity.getBlockPos(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 2.0F);
            }
            return true;
        }
        return false;
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
