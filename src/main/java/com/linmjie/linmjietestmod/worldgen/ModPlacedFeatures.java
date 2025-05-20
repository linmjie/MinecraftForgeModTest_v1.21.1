package com.linmjie.linmjietestmod.worldgen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.heightproviders.VeryBiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> URANIUM_ORE_PLACED_KEY = registerKey("uranium_ore_placed");
    public static final ResourceKey<PlacedFeature> URANIUM_ORE_PLACED_LARGE_KEY = registerKey("uranium_ore_large_placed");
    public static final ResourceKey<PlacedFeature> END_URANIUM_ORE_PLACED_KEY = registerKey("end_uranium_ore_placed");
    public static final ResourceKey<PlacedFeature> END_URANIUM_ORE_PLACED_LARGE_KEY = registerKey("end_uranium_ore_large_placed");

    public static final ResourceKey<PlacedFeature> FIR_PLACED_KEY = registerKey("fir_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context){
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, URANIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_URANIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24)))));
        register(context, URANIUM_ORE_PLACED_LARGE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_URANIUM_ORE_LARGE_KEY),
                ModOrePlacement.rareOrePlacement(7,
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(0)))));

        register(context, END_URANIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_URANIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(18,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(80))));
        register(context, END_URANIUM_ORE_PLACED_LARGE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_URANIUM_ORE_LARGE_KEY),
                ModOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(50))));

        register(context, FIR_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FIR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1F, 2),
                        ModBlocks.FIR_SAPLING.get()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers){
        context.register(key, new PlacedFeature(configuration, modifiers));
    }
}
