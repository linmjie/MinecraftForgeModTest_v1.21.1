package com.linmjie.linmjietestmod.enchantment;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.enchantment.custom.DolphinSpawnerEnchantmentEffect;
import com.linmjie.linmjietestmod.enchantment.custom.RadiatorEnchantmentEffect;
import com.linmjie.linmjietestmod.util.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> DOLPHIN_SPAWNER = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "dolphin_spawner"));
    public static final ResourceKey<Enchantment> RADIATOR = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "radiator"));

    public static void bootstrap(BootstrapContext<Enchantment> context){
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, DOLPHIN_SPAWNER, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                5,
                3,
                Enchantment.dynamicCost(7, 12),
                Enchantment.dynamicCost(25, 12),
                4,
                EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new DolphinSpawnerEnchantmentEffect()));

        register(context, RADIATOR, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.FIRE_ASPECT_ENCHANTABLE),
                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        2,
                        2,
                        Enchantment.dynamicCost(10, 20),
                        Enchantment.dynamicCost(60, 20),
                        6,
                        EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(ModTags.Enchantments.MOB_EFFECT_APPLIER_EXCLUSIVE))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new RadiatorEnchantmentEffect()));
    }


private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder){
        registry.register(key, builder.build(key.location()));
    }
}
