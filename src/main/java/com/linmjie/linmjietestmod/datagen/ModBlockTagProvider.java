package com.linmjie.linmjietestmod.datagen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TestingMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.URANIUM_BLOCK.get())
                .add(ModBlocks.URANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.get())
                .add(ModBlocks.SIGMA_BLOCK.get())
                .add(ModBlocks.NEON_BLOCK.get())
                .add(ModBlocks.NEON_HOLE_BLOCK.get())
                .add(ModBlocks.CONDENSED_SOAP_BLOCK.get())
                .add(ModBlocks.NEON_BRICK_STAIRS.get())
                .add(ModBlocks.NEON_BRICK_SLAB.get())
                .add(ModBlocks.NEON_BUTTON.get())
                .add(ModBlocks.NEON_PRESSURE_PLATE.get())
                .add(ModBlocks.NEON_FENCE.get())
                .add(ModBlocks.NEON_FENCE_GATE.get())
                .add(ModBlocks.NEON_WALL.get())
                .add(ModBlocks.NEON_DOOR.get())
                .add(ModBlocks.NEON_TRAPDOOR.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.SOAP_BLOCK.get());
        tag(BlockTags.FENCES)
                .add(ModBlocks.NEON_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.NEON_FENCE_GATE.get());
        tag(BlockTags.WALLS)
                .add(ModBlocks.NEON_WALL.get());

        tag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.SIGMA_BLOCK.get())
                .addTag(ModTags.Blocks.NEEDS_URANIUM_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        tag(ModTags.Blocks.NEEDS_URANIUM_TOOL)
                .add(ModBlocks.SIGMA_BLOCK.get())
                .addTag(BlockTags.NEEDS_IRON_TOOL);


        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_URANIUM_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_URANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_URANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_URANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_URANIUM_TOOL);

    }
}
