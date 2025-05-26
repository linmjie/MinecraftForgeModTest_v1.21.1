package com.linmjie.linmjietestmod.entity.custom;

import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.entity.ModEntities;
import com.linmjie.linmjietestmod.item.ModItems;
import com.linmjie.linmjietestmod.sound.ModSounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class JackBlackEntity extends Animal{

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private final SimpleContainer inventory = new SimpleContainer(5);

    @Override
    protected void registerGoals(){
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.6));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.6, stack -> stack.is(Items.CHICKEN), false));

        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30F)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                .add(Attributes.FOLLOW_RANGE, 300);
    }
    public JackBlackEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setCanPickUpLoot(true);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(ModBlocks.LAVA_CHICKEN.get().asItem());
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    @Override
    public boolean canBreed() {
        return false;
    }

    private void setupAnimationState(){
        if(this.idleAnimationTimeout<=0){
            this.idleAnimationTimeout = 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()){
            this.setupAnimationState();
        }
    }

    //SOUNDS
    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return ModSounds.JACK_BLACK_AMBIENT.get();
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.PLAYER_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.PLAYER_DEATH;
    }

    @Override
    protected void pickUpItem(ItemEntity pItemEntity) {
        ItemStack itemstack = pItemEntity.getItem();
        if (this.wantsItem(itemstack)) {
            this.onItemPickup(pItemEntity);
            ItemStack itemstack1 = this.inventory.addItem(itemstack);
            if (itemstack1.isEmpty()) {
                pItemEntity.discard();
            } else {
                itemstack.setCount(itemstack1.getCount());
            }
        }
        //drop a lava chicken if possible
        int chickenIndex=-1;
        int lavaIndex=-1;
        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            if(this.inventory.getItem(i).is(Items.CHICKEN)){
                chickenIndex=i;
            }
            if(this.inventory.getItem(i).is(Items.LAVA_BUCKET)){
                lavaIndex=i;
            }
        }
        if (chickenIndex >= 0 && lavaIndex >= 0){
            ItemEntity itemEntity = new ItemEntity(this.level(),
                    this.getX(), this.getY(), this.getZ(), new ItemStack(ModBlocks.LAVA_CHICKEN.get(), 1)
            );
            this.inventory.removeItem(chickenIndex, 1);
            this.inventory.removeItem(lavaIndex, 1);
            this.level().addFreshEntity(itemEntity);
            this.playSound(ModSounds.LAVA_CHICKEN.get());
        }
    }

    private boolean wantsItem(ItemStack itemstack) {
        return (itemstack.is(Items.CHICKEN) || itemstack.is(Items.LAVA_BUCKET));
    }
}
