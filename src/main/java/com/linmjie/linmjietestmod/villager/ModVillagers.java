package com.linmjie.linmjietestmod.villager;

import com.google.common.collect.ImmutableSet;
import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.sound.ModSounds;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, TestingMod.MOD_ID);
    public  static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, TestingMod.MOD_ID);

    public static final RegistryObject<PoiType> SIGMA_POI = POI_TYPES.register("sigmager_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.SIGMA_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> SIGMAGER = VILLAGER_PROFESSION.register("sigmager",
            () -> new VillagerProfession("sigmager", holder -> holder.value() == SIGMA_POI.get(),
                    holder -> holder.value() == SIGMA_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    ModSounds.LAVA_CHICKEN.get()));

    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSION.register(eventBus);
    }
}
