package com.linmjie.linmjietestmod.worldgen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_URANIUM_ORE_KEY = registerKey("uranium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_URANIUM_ORE_LARGE_KEY = registerKey("uranium_ore_large");

    public static final ResourceKey<ConfiguredFeature<?, ?>> END_URANIUM_ORE_KEY = registerKey("end_uranium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_URANIUM_ORE_LARGE_KEY = registerKey("end_uranium_ore_large");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_KEY = registerKey("fir");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA_BERRY_BUSH_KEY = registerKey("banana_berry_bush");

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

        //TreeFeatures.java for Vanilla Trees
        register(context, FIR_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.FIR_LOG.get()),
                        new StraightTrunkPlacer(6, 2, 1),
                        BlockStateProvider.simple(ModBlocks.FIR_LEAVES.get()),
                        new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)),
                        new TwoLayersFeatureSize(2, 0, 2)
                )
                        .ignoreVines()
                        .build()
        );

        register(context,
                BANANA_BERRY_BUSH_KEY,
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(
                                BlockStateProvider.simple(ModBlocks.BANANA_BERRY_BUSH.get().defaultBlockState()
                                        .setValue(SweetBerryBushBlock.AGE, Integer.valueOf(3)))
                        ),
                        List.of(Blocks.GRASS_BLOCK)
                )
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
