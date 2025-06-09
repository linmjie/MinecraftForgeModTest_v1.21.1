package com.linmjie.linmjietestmod.block.entity.custom;

import com.linmjie.linmjietestmod.block.entity.ModBlockEntities;
import com.linmjie.linmjietestmod.component.ModDataComponentTypes;
import com.linmjie.linmjietestmod.screen.custom.ATMMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class  ATMBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler inventory = new ItemStackHandler(3){
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return slot == 0 ? 1 : 64;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int CARD_SLOT = 0;
    private static final int DEPOSIT_SLOT = 1;
    private static final int WITHDRAW_SLOT = 2;

    public ATMBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ATM_BE.get(), pPos, pBlockState);
    }

    public void clearContents(){
        inventory.setStackInSlot(CARD_SLOT, ItemStack.EMPTY);
    }

    public void drops(){
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for (int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("ATM");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ATMMenu(pContainerId, pPlayerInventory, this);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState){
        if (this.inventory.getStackInSlot(DEPOSIT_SLOT).is(Items.EMERALD)){
            int emeraldCount = this.inventory.getStackInSlot(DEPOSIT_SLOT).getCount();
            int currentEmeralds = this.inventory.getStackInSlot(CARD_SLOT).get(ModDataComponentTypes.EMERALDS_ACCOUNT.get()) != null ?
                    this.inventory.getStackInSlot(CARD_SLOT).get(ModDataComponentTypes.EMERALDS_ACCOUNT.get()) : 0;
            this.inventory.getStackInSlot(CARD_SLOT).set(ModDataComponentTypes.EMERALDS_ACCOUNT.get(), currentEmeralds + emeraldCount);
            this.inventory.getStackInSlot(DEPOSIT_SLOT).shrink(emeraldCount);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.put("inventory", inventory.serializeNBT(pRegistries));
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        inventory.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
}
