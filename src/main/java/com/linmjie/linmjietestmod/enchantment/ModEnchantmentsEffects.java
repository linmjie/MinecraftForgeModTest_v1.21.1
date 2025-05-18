package com.linmjie.linmjietestmod.enchantment;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.enchantment.custom.DolphinSpawnerEnchantmentEffect;
import com.linmjie.linmjietestmod.enchantment.custom.RadiatorEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantmentsEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, TestingMod.MOD_ID);

    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> DOLPHIN_SPAWNER =
            ENTITY_ENCHANTMENT_EFFECTS.register("dolphin_spawner", () -> DolphinSpawnerEnchantmentEffect.CODEC);
    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> RADIATOR =
            ENTITY_ENCHANTMENT_EFFECTS.register("radiator", () -> RadiatorEnchantmentEffect.CODEC);

    public static void register(IEventBus eventBus){
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
