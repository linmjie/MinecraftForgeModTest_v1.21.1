package com.linmjie.linmjietestmod.datagen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.block.custom.BananaBerryBushBlock;
import com.linmjie.linmjietestmod.block.custom.ShinyNeonBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

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
        blockWithItem(ModBlocks.END_URANIUM_ORE);
        blockWithItem(ModBlocks.CONDENSED_SOAP_BLOCK);


        blockWithItem(ModBlocks.NEON_BLOCK);
        shinyNeonBlockWithItem(ModBlocks.SHINY_NEON_BLOCK.get(), "neon_block", "shiny_neon_block_unlit");

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

        logBlock(ModBlocks.FIR_LOG.get());
        axisBlock(ModBlocks.FIR_WOOD.get(), blockTexture(ModBlocks.FIR_LOG.get()), blockTexture(ModBlocks.FIR_LOG.get()));
        logBlock(ModBlocks.STRIPPED_FIR_LOG.get());
        axisBlock(ModBlocks.STRIPPED_FIR_WOOD.get(), blockTexture(ModBlocks.STRIPPED_FIR_LOG.get()), blockTexture(ModBlocks.STRIPPED_FIR_LOG.get()));

        blockItem(ModBlocks.FIR_LOG);
        blockItem(ModBlocks.FIR_WOOD);
        blockItem(ModBlocks.STRIPPED_FIR_LOG);
        blockItem(ModBlocks.STRIPPED_FIR_WOOD);

        blockWithItem(ModBlocks.FIR_PLANKS);

        leavesBlock(ModBlocks.FIR_LEAVES);
        saplingBlock(ModBlocks.FIR_SAPLING);

        makeBush((SweetBerryBushBlock) ModBlocks.BANANA_BERRY_BUSH.get(),"banana_bush_stage", "banana_bush_stage" );
    }

    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(BananaBerryBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "block/" + textureName + state.getValue(BananaBerryBushBlock.AGE))).renderType("cutout"));

        return models;
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void shinyNeonBlockWithItem(Block block, String litBlockString, String unlitBlockString) {
        getVariantBuilder(block).forAllStates(state -> {
            if(state.getValue(ShinyNeonBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll(litBlockString,
                        ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "block/" + litBlockString)))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll(unlitBlockString,
                        ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "block/" + unlitBlockString)))};
            }
        });
        simpleBlockItem(block, models().cubeAll(litBlockString,
                ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "block/" + litBlockString)));
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
