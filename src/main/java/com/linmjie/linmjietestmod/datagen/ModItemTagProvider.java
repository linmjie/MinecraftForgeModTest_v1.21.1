package com.linmjie.linmjietestmod.datagen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.item.ModItems;
import com.linmjie.linmjietestmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, TestingMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.DEEPSLATE_SOLID)
                .add(Items.DEEPSLATE)
                .add(Items.COBBLED_DEEPSLATE);

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.URANIUM_HELMET.get())
                .add(ModItems.URANIUM_CHESTPLATE.get())
                .add(ModItems.URANIUM_LEGGINGS.get())
                .add(ModItems.URANIUM_BOOTS.get());
        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.URANIUM_HELMET.get());
        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.URANIUM_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.URANIUM_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.URANIUM_BOOTS.get())
                .add(ModItems.BANANA_BOOTS.get());

        tag(ItemTags.SWORDS)
                .add(ModItems.URANIUM_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(ModItems.URANIUM_PICKAXE.get());
        tag(ItemTags.CLUSTER_MAX_HARVESTABLES)
                .add(ModItems.URANIUM_PICKAXE.get());
        tag(ItemTags.AXES)
                .add(ModItems.URANIUM_AXE.get());
        tag(ItemTags.HOES)
                .add(ModItems.URANIUM_HOE.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.URANIUM_SHOVEL.get());
        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.URANIUM_PLOUGH.get());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.FIR_LOG.get().asItem())
                .add(ModBlocks.FIR_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_FIR_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_FIR_WOOD.get().asItem());
        tag(ItemTags.PLANKS)
                .add(ModBlocks.FIR_PLANKS.get().asItem());
        tag(ModTags.Items.FIR_LOGS)
                .add(ModBlocks.FIR_LOG.get().asItem())
                .add(ModBlocks.FIR_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_FIR_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_FIR_WOOD.get().asItem());
    }
}
