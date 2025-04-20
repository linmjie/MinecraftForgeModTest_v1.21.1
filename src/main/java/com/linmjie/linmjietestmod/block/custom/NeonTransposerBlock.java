package com.linmjie.linmjietestmod.block.custom;

import com.linmjie.linmjietestmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class NeonTransposerBlock extends TransparentBlock {
    public NeonTransposerBlock(Properties properties){
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {

        pLevel.playSound(pPlayer, pPos, SoundEvents.CALCITE_STEP, SoundSource.BLOCKS, 0.8F, 1.5F);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {

        if(pEntity instanceof ItemEntity itemEntity){
            if(itemEntity.getItem().getItem() == ModItems.NEON.get()) {
                itemEntity.setItem(new ItemStack(Items.NETHERITE_INGOT));
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
