package com.linmjie.linmjietestmod.datagen;

import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.block.custom.ATMBlock;
import com.linmjie.linmjietestmod.block.custom.SlotsMachineBlock;
import com.linmjie.linmjietestmod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
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
        dropSelf(ModBlocks.SHINY_NEON_BLOCK.get());

        dropSelf(ModBlocks.NEON_BRICKS.get());
        dropSelf(ModBlocks.NEON_BRICK_STAIRS.get());
        this.add(ModBlocks.NEON_BRICK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.NEON_BRICK_SLAB.get()));
        dropSelf(ModBlocks.NEON_BUTTON.get());
        dropSelf(ModBlocks.NEON_PRESSURE_PLATE.get());

        dropSelf(ModBlocks.NEON_WALL.get());
        dropSelf(ModBlocks.NEON_FENCE.get());
        dropSelf(ModBlocks.NEON_FENCE_GATE.get());

        this.add(ModBlocks.NEON_DOOR.get(),
                block -> createDoorTable(ModBlocks.NEON_DOOR.get()));
        dropSelf(ModBlocks.NEON_TRAPDOOR.get());

        this.dropSelf(ModBlocks.FIR_LOG.get());
        this.dropSelf(ModBlocks.FIR_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_FIR_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_FIR_WOOD.get());
        this.dropSelf(ModBlocks.FIR_PLANKS.get());
        this.dropSelf(ModBlocks.FIR_SAPLING.get());

        this.add(ModBlocks.ATM.get(), block -> this.createSinglePropConditionTable(block, ATMBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ModBlocks.SLOTS_MACHINE.get(), block -> this.createSinglePropConditionTable(block, SlotsMachineBlock.HALF, DoubleBlockHalf.LOWER));

        this.add(ModBlocks.FIR_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.FIR_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));




        this.add(ModBlocks.URANIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.URANIUM_ORE.get(),
                        ModItems.URANIUM.get()));
        this.add(ModBlocks.DEEPSLATE_URANIUM_ORE.get(),
                block -> (createMultiOreDrops(ModBlocks.DEEPSLATE_URANIUM_ORE.get(),
                        ModItems.URANIUM.get(), 1F, 3F)));
        this.add(ModBlocks.END_URANIUM_ORE.get(),
                block -> (createMultiOreDrops(ModBlocks.END_URANIUM_ORE.get(),
                        ModItems.URANIUM.get(), 1F, 4F)));
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.BANANA_BERRY_BUSH.get(), block -> this.applyExplosionDecay(
                block,LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BANANA_BERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.BANANA.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BANANA_BERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.BANANA.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));
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
