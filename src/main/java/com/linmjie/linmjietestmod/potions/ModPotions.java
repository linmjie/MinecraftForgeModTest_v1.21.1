package com.linmjie.linmjietestmod.potions;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModPotions {
    public static DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, TestingMod.MOD_ID);

    public static final RegistryObject<Potion> RADIATED_POTION = POTIONS.register("radiated_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.RADIATED_EFFECT.getHolder().get(), 900, 0)));
    public static final RegistryObject<Potion> STRONG_RADIATED_POTION = POTIONS.register("strong_radiated_potion",
            () -> new Potion("radiated_potion", new MobEffectInstance(ModEffects.RADIATED_EFFECT.getHolder().get(), 432, 1)));
    public static final RegistryObject<Potion> LONG_RADIATED_POTION = POTIONS.register("long_radiated_potion",
            () -> new Potion("radiated_potion", new MobEffectInstance(ModEffects.RADIATED_EFFECT.getHolder().get(), 1800, 0)));

    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}
