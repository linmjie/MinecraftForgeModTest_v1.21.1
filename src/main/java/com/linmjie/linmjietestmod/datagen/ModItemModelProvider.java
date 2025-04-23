package com.linmjie.linmjietestmod.datagen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

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
    }
}
