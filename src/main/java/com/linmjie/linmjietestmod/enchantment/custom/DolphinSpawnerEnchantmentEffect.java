package com.linmjie.linmjietestmod.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record DolphinSpawnerEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<DolphinSpawnerEnchantmentEffect> CODEC = MapCodec.unit(DolphinSpawnerEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {
        if(pEnchantmentLevel>=1){
            for(int i=0; i<pEnchantmentLevel; i++){
                EntityType.DOLPHIN.spawn(pLevel, pEntity.getOnPos(), MobSpawnType.TRIGGERED);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
