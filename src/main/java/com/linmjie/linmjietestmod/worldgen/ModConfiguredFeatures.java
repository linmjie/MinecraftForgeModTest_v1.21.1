package com.linmjie.linmjietestmod.worldgen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_URANIUM_ORE_KEY = registerKey("uranium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_URANIUM_ORE_LARGE_KEY = registerKey("uranium_ore_large");

    public static final ResourceKey<ConfiguredFeature<?, ?>> END_URANIUM_ORE_KEY = registerKey("end_uranium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_URANIUM_ORE_LARGE_KEY = registerKey("end_uranium_ore_large");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest endstoneReplaceables = new BlockMatchTest(Blocks.END_STONE);


        List<OreConfiguration.TargetBlockState> overworldUraniumOresList = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.URANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_URANIUM_ORE.get().defaultBlockState())
        );

        register(context, OVERWORLD_URANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldUraniumOresList, 3));
        register(context, OVERWORLD_URANIUM_ORE_LARGE_KEY, Feature.ORE, new OreConfiguration(overworldUraniumOresList, 8));
        register(context, END_URANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(endstoneReplaceables,
                ModBlocks.END_URANIUM_ORE.get().defaultBlockState(),6));
        register(context, END_URANIUM_ORE_LARGE_KEY, Feature.ORE, new OreConfiguration(endstoneReplaceables,
                ModBlocks.END_URANIUM_ORE.get().defaultBlockState(),12));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
