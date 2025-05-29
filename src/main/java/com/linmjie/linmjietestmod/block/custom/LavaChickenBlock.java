package com.linmjie.linmjietestmod.block.custom;

import com.linmjie.linmjietestmod.effect.ModEffects;
import com.linmjie.linmjietestmod.util.ModBlockStateProperties;
import com.linmjie.linmjietestmod.util.ModUtils;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
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
    public static final VoxelShape[] SHAPE_BY_BITE = ModUtils.makeDirectionalVoxelShapes(new VoxelShape[]{
            Block.box(3, 0, 4, 13, 7, 14),
            Block.box(3, 0, 4, 13, 7, 14),
            Block.box(5, 0, 5, 11, 6, 12),
            Block.box(5, 0, 5, 11, 6, 8)
    });

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
        return SHAPE_BY_BITE[ModUtils.getIndexForDirectionalVoxelShape(pState.getValue(LAVA_CHICKEN_BITES), pState.getValue(FACING), MAX_LAVA_CHICKEN_BITES)];
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
            pPlayer.addEffect(new MobEffectInstance(ModEffects.JACK_BLACKED_EFFECT.getHolder().get(), 12000, 0, false, false));
            pLevel.playSound(null, pPos, SoundEvents.GENERIC_EAT, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING)
                .add(LAVA_CHICKEN_BITES);
    }
}
