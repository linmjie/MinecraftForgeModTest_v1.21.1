package com.linmjie.linmjietestmod.effect;

import com.linmjie.linmjietestmod.TestingMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TestingMod.MOD_ID);

    public static final RegistryObject<MobEffect> RADIATED_EFFECT = MOB_EFFECTS.register("radiated",
            () -> new RadiatedMobEffect(MobEffectCategory.HARMFUL, 0x3df550)
                    .addAttributeModifier(Attributes.MAX_HEALTH, ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "radiated")
                    , -0.2f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
