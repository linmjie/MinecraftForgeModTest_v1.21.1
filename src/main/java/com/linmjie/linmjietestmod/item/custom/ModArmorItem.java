package com.linmjie.linmjietestmod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.linmjie.linmjietestmod.effect.ModEffects;
import com.linmjie.linmjietestmod.item.ModArmorMaterials;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {
    private static final Map<Holder<ArmorMaterial>, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<Holder<ArmorMaterial>, List<MobEffectInstance>>())
                    .put(ModArmorMaterials.URANIUM_ARMOR_MATERIAL,
                            List.of(new MobEffectInstance(ModEffects.RADIATED_EFFECT.getHolder().get(), 100, 0, false, false),
                                    new MobEffectInstance(MobEffects.WEAKNESS, 100, 0, false, false)))
                    .build();

    public ModArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (!level.isClientSide() && hasAnyArmorOn(player)) {
            evaluateArmorEffects(player);
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<Holder<ArmorMaterial>, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            Holder<ArmorMaterial> mapArmorMaterial = entry.getKey();
            List<MobEffectInstance> mapEffect = entry.getValue();

            int numArmorPieces = countArmorPieces(mapArmorMaterial, player);
            if (numArmorPieces > 0) {
                applyEffectBasedOnArmorPieces(player, mapEffect, numArmorPieces);
            }
        }
    }

    private void applyEffectBasedOnArmorPieces(Player player, List<MobEffectInstance> baseEffects, int numArmorPieces) {
        for (MobEffectInstance effect : baseEffects) {
            if (!hasEffectWithSufficientDuration(player, effect)) {
                if (numArmorPieces > 2) {
                    player.addEffect(new MobEffectInstance(effect.getEffect(), effect.getDuration(), 1, effect.isAmbient(), effect.isVisible()));
                } else if (numArmorPieces >= 1) {
                    player.addEffect(new MobEffectInstance(effect.getEffect(), effect.getDuration(), 0, effect.isAmbient(), effect.isVisible()));
                }
            }
        }
    }
    private boolean hasEffectWithSufficientDuration(Player player, MobEffectInstance effect) {
        MobEffectInstance existingEffect = player.getEffect(effect.getEffect());
        if (existingEffect != null) {
            return existingEffect.getDuration() > 20;
        }
        return false;
    }

    private int countArmorPieces(Holder<ArmorMaterial> mapArmorMaterial, Player player) {
        int count = 0;
        for (ItemStack armorStack : player.getArmorSlots()) {
            if (armorStack.getItem() instanceof ArmorItem armorItem) {
                if (armorItem.getMaterial() == mapArmorMaterial) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean hasAnyArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() || !leggings.isEmpty() || !chestplate.isEmpty() || !helmet.isEmpty();
    }
}
