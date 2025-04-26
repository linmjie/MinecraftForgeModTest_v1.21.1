package com.linmjie.linmjietestmod.item.custom;
import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.component.ModDataComponentTypes;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;


public class CleaningItem extends Item{
    private static final Map<Block,Block> CLEANING_BLOCK_MAP=Map.of(
            Blocks.NETHERITE_BLOCK,Blocks.GOLD_BLOCK,
            ModBlocks.SIGMA_BLOCK.get(),ModBlocks.NEON_BLOCK.get()
    );
    private static final List<Block> CLEANING_BLOCK_RANDOM_PULL=List.of(
            Blocks.DIRT,
            Blocks.ANDESITE,
            Blocks.CHISELED_SANDSTONE,
            Blocks.DIAMOND_BLOCK,
            Blocks.DIORITE,
            Blocks.END_STONE,
            Blocks.BLUE_ORCHID,
            Blocks.BROWN_SHULKER_BOX,
            Blocks.DETECTOR_RAIL,
            Blocks.CUT_SANDSTONE,
            Blocks.DEAD_HORN_CORAL_BLOCK,
            Blocks.CRYING_OBSIDIAN,
            Blocks.ACACIA_BUTTON,
            Blocks.ANCIENT_DEBRIS,
            Blocks.BROWN_CANDLE_CAKE,
            Blocks.CHAIN,
            Blocks.JUNGLE_PLANKS,
            Blocks.BAMBOO_MOSAIC,
            Blocks.BAMBOO_FENCE,
            Blocks.FLOWER_POT,
            Blocks.LADDER,
            Blocks.NETHERITE_BLOCK
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

                pContext.getItemInHand().set(ModDataComponentTypes.COORDINATES.get(), pContext.getClickedPos());
            }
        }

        if (clickedBlock==ModBlocks.NEON_BLOCK.get()){
            if (!level.isClientSide()) {
                int random = Math.toIntExact(Math.round(Math.random() * CLEANING_BLOCK_RANDOM_PULL.toArray().length-1)); //random index from list
                level.setBlockAndUpdate(pContext.getClickedPos(),CLEANING_BLOCK_RANDOM_PULL.get(random).defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, pContext.getClickedPos(), SoundEvents.BUCKET_FILL, SoundSource.BLOCKS);

                pContext.getItemInHand().set(ModDataComponentTypes.COORDINATES.get(), pContext.getClickedPos());
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(Screen.hasShiftDown())
            pTooltipComponents.add(Component.translatable("tooltip.linmjietestmod.cleaning_item.shift_down"));
        else
            pTooltipComponents.add(Component.translatable("tooltip.linmjietestmod.cleaning_item"));

        if(pStack.get(ModDataComponentTypes.COORDINATES.get()) != null){
            pTooltipComponents.add(Component.literal("Last Clean at " + pStack.get(ModDataComponentTypes.COORDINATES.get())+"!!!"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
