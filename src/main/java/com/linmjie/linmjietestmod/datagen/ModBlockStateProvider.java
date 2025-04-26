package com.linmjie.linmjietestmod.datagen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TestingMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SOAP_BLOCK);
        blockWithItem(ModBlocks.SIGMA_BLOCK);

        blockWithItem(ModBlocks.URANIUM_BLOCK);
        blockWithItem(ModBlocks.DEEPSLATE_URANIUM_ORE);
        blockWithItem(ModBlocks.URANIUM_ORE);

        blockWithItemAndRenderType(ModBlocks.NEON_HOLE_BLOCK, "minecraft:cutout");

        blockWithItem(ModBlocks.NEON_BLOCK);

        blockWithItem(ModBlocks.NEON_BRICKS);
        stairsBlock(ModBlocks.NEON_BRICK_STAIRS.get(), blockTexture(ModBlocks.NEON_BRICKS.get()));
        slabBlock(ModBlocks.NEON_BRICK_SLAB.get(), blockTexture(ModBlocks.NEON_BRICKS.get()), blockTexture(ModBlocks.NEON_BRICKS.get()));

        buttonBlock(ModBlocks.NEON_BUTTON.get(), blockTexture(ModBlocks.NEON_BLOCK.get()));
        pressurePlateBlock(ModBlocks.NEON_PRESSURE_PLATE.get(), blockTexture(ModBlocks.NEON_BLOCK.get()));

        fenceBlock(ModBlocks.NEON_FENCE.get(), blockTexture(ModBlocks.NEON_BLOCK.get()));
        fenceGateBlock(ModBlocks.NEON_FENCE_GATE.get(), blockTexture(ModBlocks.NEON_BLOCK.get()));
        wallBlock(ModBlocks.NEON_WALL.get(), blockTexture(ModBlocks.NEON_BRICKS.get()));

        doorBlockWithRenderType(ModBlocks.NEON_DOOR.get(), modLoc("block/neon_door_bottom"), modLoc("block/neon_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.NEON_TRAPDOOR.get(), modLoc("block/neon_trapdoor"), true, "cutout");

        blockItem(ModBlocks.NEON_BRICK_STAIRS);
        blockItem(ModBlocks.NEON_BRICK_SLAB);
        blockItem(ModBlocks.NEON_PRESSURE_PLATE);
        blockItem(ModBlocks.NEON_FENCE_GATE);
        blockItem(ModBlocks.NEON_TRAPDOOR, "_bottom");


        blockWithItem(ModBlocks.CONDENSED_SOAP_BLOCK);

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockWithItemAndRenderType(RegistryObject<Block> blockRegistryObject, String renderType) {
        String blockName = blockRegistryObject.getId().getPath();
        ModelFile parentModel = models().cubeAll(blockName, modLoc("block/" + blockName));
        BlockModelBuilder model = models().getBuilder(blockName)
                .parent(parentModel)
                .renderType(renderType);
        simpleBlock(blockRegistryObject.get(), model);
        simpleBlockItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("linmjietestmod:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("linmjietestmod:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}
