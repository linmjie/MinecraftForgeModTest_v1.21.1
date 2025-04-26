package com.linmjie.linmjietestmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;


public class ShinyNeonBlock extends Block {
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");

    public ShinyNeonBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(CLICKED, true));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos,
                                               Player pPlayer, BlockHitResult pHitResult) {
        if(!pLevel.isClientSide()){

            boolean currentState = pState.getValue(CLICKED);
            pLevel.setBlockAndUpdate(pPos, pState.setValue(CLICKED, !currentState));
        }
        pLevel.playSound(pPlayer, pPos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 2.8F, 4.5F);
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            boolean currentState = pState.getValue(CLICKED);
            if (currentState != pLevel.hasNeighborSignal(pPos)) {
                if (currentState) {
                    pLevel.scheduleTick(pPos, this, 4);
                } else {
                    pLevel.setBlock(pPos, pState.cycle(CLICKED), 2);
                }
            }
        }
    }


    @Override
    protected void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(CLICKED) && !pLevel.hasNeighborSignal(pPos)) {
            pLevel.setBlock(pPos, pState.cycle(CLICKED), 2);
        }
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(CLICKED);
    }
}
