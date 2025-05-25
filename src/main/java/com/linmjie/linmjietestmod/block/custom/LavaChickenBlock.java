package com.linmjie.linmjietestmod.block.custom;

import com.linmjie.linmjietestmod.util.ModBlockStateProperties;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class LavaChickenBlock extends HorizontalDirectionalBlock {
    public static final int MAX_LAVA_CHICKEN_BITES = 3;
    public static final IntegerProperty LAVA_CHICKEN_BITES = ModBlockStateProperties.LAVA_CHICKEN_BITES;
    public static final MapCodec<LavaChickenBlock> CODEC = simpleCodec(LavaChickenBlock::new);
    /*
        Placeholder
        Will want to adjust voxel shapes for stage 2 and 3( -2 and -7 on z axis respectively)
        needs to change with lengths go with which dimensions as these box shapes have absolute direction
    */
    public static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{
            Block.box(3, 0, 2, 13, 7, 14),
            Block.box(3, 0, 2, 13, 7, 14),
            Block.box(3, 0, 2, 13, 7, 14),
            Block.box(3, 0, 2, 13, 7, 14)
    };

    public LavaChickenBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LAVA_CHICKEN_BITES, Integer.valueOf(0)).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_BITE[pState.getValue(LAVA_CHICKEN_BITES)];
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (pLevel.isClientSide) {
            if (eat(pLevel, pPos, pState, pPlayer).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (pPlayer.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return eat(pLevel, pPos, pState, pPlayer);
    }
    protected static InteractionResult eat(LevelAccessor pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pPlayer.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            pPlayer.awardStat(Stats.EAT_CAKE_SLICE);
            pPlayer.getFoodData().eat(4, 0.5F);
            int i = pState.getValue(LAVA_CHICKEN_BITES);
            pLevel.gameEvent(pPlayer, GameEvent.EAT, pPos);
            if (i < 3) {
                pLevel.setBlock(pPos, pState.setValue(LAVA_CHICKEN_BITES, Integer.valueOf(i + 1)), 3);
            } else {
                pLevel.removeBlock(pPos, false);
                pLevel.gameEvent(pPlayer, GameEvent.BLOCK_DESTROY, pPos);
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING)
                .add(LAVA_CHICKEN_BITES);
    }
}
