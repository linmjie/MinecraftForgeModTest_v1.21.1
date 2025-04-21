package com.linmjie.linmjietestmod.datagen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
   public ModBlockStateProvider (PackOutput output, ExistingFileHelper exFileHelper) {
       super(output, TestingMod.MOD_ID, exFileHelper);
   }

    @Override
    protected void registerStatesAndModels() {
       blockWithItem(ModBlocks.SOAP_BLOCK);
       blockWithItem(ModBlocks.SIGMA_BLOCK);

       blockWithItem(ModBlocks.URANIUM_BLOCK);
       blockWithItem(ModBlocks.DEEPSLATE_URANIUM_ORE);
       blockWithItem(ModBlocks.URANIUM_ORE);

       blockWithItem(ModBlocks.NEON_HOLE_BLOCK);
       blockWithItem(ModBlocks.NEON_BLOCK);
       blockWithItem(ModBlocks.CONDENSED_SOAP_BLOCK);

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
       simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
