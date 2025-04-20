package com.linmjie.linmjietestmod.item.custom;

import com.linmjie.linmjietestmod.block.ModBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class ChiselItem extends Item {
    public ChiselItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if (clickedBlock == ModBlocks.NEON_BLOCK.get()) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(pContext.getClickedPos(), ModBlocks.NEON_HOLE_BLOCK.get().defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, pContext.getClickedPos(), SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
