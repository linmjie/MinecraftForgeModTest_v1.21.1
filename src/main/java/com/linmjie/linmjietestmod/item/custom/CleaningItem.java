package com.linmjie.linmjietestmod.item.custom;
import com.linmjie.linmjietestmod.block.ModBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;


public class CleaningItem extends Item{
    private static final Map<Block,Block> CLEANING_BLOCK_MAP=Map.of(
            Blocks.NETHERITE_BLOCK,Blocks.GOLD_BLOCK,
            ModBlocks.SIGMA_BLOCK.get(),ModBlocks.NEON_BLOCK.get()
    );

    public CleaningItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if (CLEANING_BLOCK_MAP.containsKey(clickedBlock)){
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(pContext.getClickedPos(),CLEANING_BLOCK_MAP.get(clickedBlock).defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, pContext.getClickedPos(), SoundEvents.BUCKET_FILL, SoundSource.BLOCKS);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
