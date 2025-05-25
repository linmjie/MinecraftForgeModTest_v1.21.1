package com.linmjie.linmjietestmod.worldgen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.entity.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_URANIUM_ORE = registerKey("add_uranium_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_URANIUM_ORE = registerKey("add_end_uranium_ore");

    public static final ResourceKey<BiomeModifier> ADD_FIR_TREE = registerKey("add_fir_tree");

    public static final ResourceKey<BiomeModifier> ADD_BANANA_BERRY_BUSH = registerKey("add_banana_berry_bush");

    public static final ResourceKey<BiomeModifier> SPAWN_EVAN = registerKey("spawn_evan");
    public static final ResourceKey<BiomeModifier> SPAWN_JACK_BLACK = registerKey("spawn_jack_black");

    public static void bootstrap(BootstrapContext<BiomeModifier> context){
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes =  context.lookup(Registries.BIOME);

        context.register(ADD_URANIUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.URANIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_END_URANIUM_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.END_URANIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_FIR_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.FOREST), biomes.getOrThrow(Biomes.WINDSWEPT_FOREST)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.FIR_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_BANANA_BERRY_BUSH, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_JUNGLE),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.BANANA_BERRY_BUSH_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(SPAWN_EVAN, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_JUNGLE),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.BETA_EVAN.get(), 25, 1, 3))
        ));
        context.register(SPAWN_JACK_BLACK, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.JACK_BLACK.get(), 12, 1, 1))
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name){
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, name));
    }
}

