package com.linmjie.linmjietestmod.block.entity.custom;

import com.linmjie.linmjietestmod.block.entity.ModBlockEntities;
import com.linmjie.linmjietestmod.screen.custom.ATMMenu;
import com.linmjie.linmjietestmod.screen.custom.SlotsMachineMenu;
import com.linmjie.linmjietestmod.util.SlotSymbol;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class SlotsMachineBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler inventory = new ItemStackHandler(1);
    public int deposit = 0;

    public enum State{
        IDLE,
        PREPARING,
        ONGOING
    }

    private State state = State.IDLE;

    public SlotsMachineBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SLOTS_MACHINE_BE.get(), pPos, pBlockState);
    }

    public void setDeposit(int deposit){
        this.deposit = deposit;
    }

    public State getState() {
        return state;
    }

    public boolean isIdle(){
        return this.state == State.IDLE;
    }

    public boolean prepareState(){
        if (this.state == State.IDLE){
            this.state = State.PREPARING;
            return true;
        }
        return false;
    }

    public void drops(){
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for (int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    private static void createSymbolTable(HashSet<SlotSymbol> basicSet){
        float total = 0F;
        for (SlotSymbol slotSymbol: basicSet){
            total += slotSymbol.odds;
        }

        float multiplier = 1.0F / total; //standardize all symbols as a fractional event
        for (SlotSymbol slotSymbol: basicSet){
            slotSymbol.odds = slotSymbol.odds * multiplier;
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("ATM");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new SlotsMachineMenu(pContainerId, pPlayerInventory, this);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState){

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
