package com.linmjie.linmjietestmod.item;

import com.linmjie.linmjietestmod.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoodProperties {
    public static final FoodProperties RAINBOW_SHARD = new FoodProperties.Builder().nutrition(1).saturationModifier(0.5F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 300, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 1200,0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200), 0.5F)
            .usingConvertsTo(Items.EMERALD).alwaysEdible().build();
    public static final FoodProperties RAINBOW_CANDY = new FoodProperties.Builder().nutrition(7).saturationModifier(0.8F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 10080, 4), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10080, 4), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 10080, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10080), 1.0F)
            .effect(new MobEffectInstance(MobEffects.LUCK, 10080, 4), 1.0F)
            .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200), 0.67F)
            .usingConvertsTo(Items.STICK).alwaysEdible().build();
    public static final FoodProperties URANIUM = new FoodProperties.Builder().nutrition(7).saturationModifier(0.8F)
            .effect(new MobEffectInstance(MobEffects.HARM, 1, 12), 1.0F)
            .effect(new MobEffectInstance(ModEffects.RADIATED_EFFECT.getHolder().get(), 2000, 1), 1.0F)
            .alwaysEdible().build();
}
