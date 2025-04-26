package com.linmjie.linmjietestmod.datagen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TestingMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.SOAP.get());
        basicItem(ModItems.NEON.get());
        basicItem(ModItems.SHARPIE.get());
        basicItem(ModItems.CONDENSED_SOAP.get());
        basicItem(ModItems.CONDENSED_SOAP.get());
        basicItem(ModItems.SCRUB_DADDY.get());
        basicItem(ModItems.ADVANCED_SCRUB_DADDY.get());
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.RAINBOW_SHARD.get());
        basicItem(ModItems.RAINBOW_CANDY.get());
        basicItem(ModItems.URANIUM.get());

        buttonItem(ModBlocks.NEON_BUTTON, ModBlocks.NEON_BLOCK);
        fenceItem(ModBlocks.NEON_FENCE, ModBlocks.NEON_BLOCK);
        wallItem(ModBlocks.NEON_WALL, ModBlocks.NEON_BRICKS);

        simpleBlockItem(ModBlocks.NEON_DOOR);

    }

    public void buttonItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void fenceItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}
