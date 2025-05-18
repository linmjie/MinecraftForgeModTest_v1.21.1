package com.linmjie.linmjietestmod.enchantment.custom;

import com.linmjie.linmjietestmod.effect.ModEffects;
import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;


public record RadiatorEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<RadiatorEnchantmentEffect> CODEC = MapCodec.unit(RadiatorEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {
        if (pEntity instanceof LivingEntity livingentity) {
            if(pEnchantmentLevel==1) {
                livingentity.addEffect(new MobEffectInstance(ModEffects.RADIATED_EFFECT.getHolder().get(), 400, 0));
            }
            if(pEnchantmentLevel==2) {
                livingentity.addEffect(new MobEffectInstance(ModEffects.RADIATED_EFFECT.getHolder().get(), 400, 1));
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
