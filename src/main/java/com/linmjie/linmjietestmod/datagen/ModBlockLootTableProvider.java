package com.linmjie.linmjietestmod.datagen;

import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }


    @Override
    protected void generate() {
        dropSelf(ModBlocks.URANIUM_BLOCK.get());
        dropSelf(ModBlocks.SOAP_BLOCK.get());
        dropSelf(ModBlocks.CONDENSED_SOAP_BLOCK.get());
        dropSelf(ModBlocks.NEON_BLOCK.get());
        dropSelf(ModBlocks.NEON_HOLE_BLOCK.get());

        this.add(ModBlocks.URANIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.URANIUM_ORE.get(),
                        ModItems.URANIUM.get()));
        this.add(ModBlocks.DEEPSLATE_URANIUM_ORE.get(),
                block -> (createMultiOreDrops(ModBlocks.DEEPSLATE_URANIUM_ORE.get(),
                        ModItems.URANIUM.get(), 2F, 4F)));
    }


    protected LootTable.Builder createMultiOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    /*@Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

     */
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(block -> block != ModBlocks.SIGMA_BLOCK.get()) // exclude the one block
                .toList();
    }
}
