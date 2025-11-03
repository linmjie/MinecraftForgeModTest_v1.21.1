package com.linmjie.linmjietestmod.block.entity.custom;

import com.linmjie.linmjietestmod.block.entity.ModBlockEntities;
import com.linmjie.linmjietestmod.component.ModDataComponentTypes;
import com.linmjie.linmjietestmod.item.ModItems;
import com.linmjie.linmjietestmod.network.ModPackets;
import com.linmjie.linmjietestmod.network.SyncSlotsMachinePacket;
import com.linmjie.linmjietestmod.screen.custom.SlotsMachineMenu;
import com.linmjie.linmjietestmod.screen.custom.SlotsMachineScreen;
import com.linmjie.linmjietestmod.util.ModUtils;
import com.linmjie.linmjietestmod.util.SlotSymbol;
import com.linmjie.linmjietestmod.util.VirtualBlitSprite;
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
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class SlotsMachineBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler inventory = new ItemStackHandler(1);
    private int emeraldsToGamble;
    private int withdraw = 0;
    private int tickCounter;
    private final SlotSymbol[] slotSymbols = new SlotSymbol[]{
            SlotSymbol.IRON,
            SlotSymbol.GOLD,
            SlotSymbol.DIAMOND,
            SlotSymbol.NETHERITE
    };
    private final ArrayList<SlotSymbol> virtualReel = new ArrayList<>();
    private int payMultiplier = 0;
    private int rollSpeed;

    private int containerId;

    public enum State{
        IDLE,
        PREPARING,
        ONGOING
    }

    private State state = State.IDLE;

    public VirtualBlitSprite[] virtualBlitSprites1 = new VirtualBlitSprite[slotSymbols.length];
    public VirtualBlitSprite[] virtualBlitSprites2 = new VirtualBlitSprite[slotSymbols.length];
    public VirtualBlitSprite[] virtualBlitSprites3 = new VirtualBlitSprite[slotSymbols.length];

    public SlotsMachineBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SLOTS_MACHINE_BE.get(), pPos, pBlockState);

        for (SlotSymbol slotSymbol : slotSymbols) {
            for (int j = 0; j < slotSymbol.getInstances(); j++) {
                virtualReel.add(slotSymbol);
            }
        }

        for (int i = 0; i < slotSymbols.length; i++) {
            virtualBlitSprites1[i] = new VirtualBlitSprite(slotSymbols[i].getResourceLocation(),  42, 42 + i*20, 20, 120);
            virtualBlitSprites2[i] = new VirtualBlitSprite(slotSymbols[i].getResourceLocation(), 78, 42 + i*20, 20, 120);
            virtualBlitSprites3[i] = new VirtualBlitSprite(slotSymbols[i].getResourceLocation(), 114, 42 + i*20,  20, 120);
        }
    }

    public State getState() {
        return state;
    }

    public SlotSymbol[] getSlotSymbols() {
        return slotSymbols;
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

    public String getWithdrawStr (){
        return Integer.toString(this.withdraw);
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
        return Component.literal("Slots Machine");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        this.containerId = pContainerId;
        return new SlotsMachineMenu(pContainerId, pPlayerInventory, this);
    }

    public void setInvalidMenu(){
        this.containerId = -1;
    }

    public void automaticWithdraw(Level level, BlockPos blockPos, BlockState blockState){
        ItemStack itemStack = this.inventory.getStackInSlot(0);
        if (itemStack.is(ModItems.BANK_CARD.get())){
            int currentEmeralds = itemStack.get(ModDataComponentTypes.EMERALDS_ACCOUNT.get()) != null?
                    itemStack.get(ModDataComponentTypes.EMERALDS_ACCOUNT.get()) : 0;
            itemStack.set(ModDataComponentTypes.EMERALDS_ACCOUNT.get(), currentEmeralds + this.withdraw);
            this.withdraw = 0;
        } else {
            int emeraldsToWithdraw = this.withdraw;
            int currentCount = itemStack.getCount();
            if (emeraldsToWithdraw + currentCount >= 64){
                emeraldsToWithdraw = 64 - currentCount;
            }
            if (itemStack.is(Items.EMERALD)){
                itemStack.grow(emeraldsToWithdraw);
            } else {
                this.inventory.setStackInSlot(0, new ItemStack(Items.EMERALD, emeraldsToWithdraw));
            }
            this.withdraw -= emeraldsToWithdraw;
        }
    }

    public SlotSymbol rollSingleReel() {
        return virtualReel.get(ModUtils.randomIndex(virtualReel.size()-1));
    }

    public void initializeReels(Level level, BlockPos blockPos, BlockState blockState){
        SlotSymbol virtualSlot1 = rollSingleReel();
        SlotSymbol virtualSlot2 = rollSingleReel();
        SlotSymbol virtualSlot3 = rollSingleReel();

        if (virtualSlot1 == virtualSlot2 && virtualSlot2 == virtualSlot3){
            this.payMultiplier = virtualSlot1.getMultiplier();
        }
        this.rollSpeed = ModUtils.randomInt(3, 7);
        this.state = State.ONGOING;
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState){
        //Automatic withdraw of emeralds from an in-machine account
        if (this.withdraw > 0) {
            automaticWithdraw(level, blockPos, blockState);
        }

        //Slots Ongoing (Currently Rolling)
        if (this.state == State.ONGOING) {
            System.out.println("STATE" + this.state);
            System.out.println(this);
                for (int i = 0; i < virtualBlitSprites1.length; i++) {
                    System.out.println(i + " " +this.virtualBlitSprites1[i].getY());
                    virtualBlitSprites1[i].changeYPos(this.rollSpeed);
                    if (virtualBlitSprites1[i].getY() > virtualBlitSprites1[i].getMaxy()) {
                        virtualBlitSprites1[i].setY(
                                virtualBlitSprites1[i].getMiny() + (virtualBlitSprites1[i].getY() - virtualBlitSprites1[i].getMaxy()));
                    }
                }
                this.tickCounter--;
        }

        //State : Prepare -> Ongoing
        if (this.state == State.PREPARING){
            initializeReels(level, blockPos, blockState);
        }

        if (containerId != -1){
            ModPackets.INSTANCE.send(
                    new SyncSlotsMachinePacket(this.getBlockPos(), this.getLevel(), virtualBlitSprites1, virtualBlitSprites2, virtualBlitSprites3),
                    PacketDistributor.TRACKING_CHUNK.with(level.getChunkAt(blockPos)));
        }
    }

    public void doSomething(){
        System.out.println("STATE"+this.state);
    }

    public void activateSlots(int emeralds){
        this.prepareState();
        this.emeraldsToGamble = emeralds;
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
