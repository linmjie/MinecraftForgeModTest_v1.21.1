package com.linmjie.linmjietestmod.datagen;

import com.linmjie.linmjietestmod.TestingMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, TestingMod.MOD_ID, registries);
    }

    @Override
    protected void start(HolderLookup.Provider registries) {

    }
}
