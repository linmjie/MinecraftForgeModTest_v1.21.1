package com.linmjie.linmjietestmod.util;

import com.linmjie.linmjietestmod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;

public class ModItemProperties {
    public static void addCustomItemProperties(){
        makeCustomCrossbow(ModItems.REPEATER.get());
    }
    public static void makeCustomCrossbow(Item item){
        ItemProperties.register(
                item,
                ResourceLocation.withDefaultNamespace("pull"),
                (itemStack, clientLevel, livingEntity, i) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    } else {
                        return CrossbowItem.isCharged(itemStack)
                                ? 0.0F
                                : (float)(itemStack.getUseDuration(livingEntity) - livingEntity.getUseItemRemainingTicks()) / (float)CrossbowItem.getChargeDuration(itemStack, livingEntity);
                    }
                }
        );
        ItemProperties.register(
                item,
                ResourceLocation.withDefaultNamespace("pulling"),
                (itemStack, clientLevel, livingEntity, i) -> livingEntity != null
                        && livingEntity.isUsingItem()
                        && livingEntity.getUseItem() == itemStack
                        && !CrossbowItem.isCharged(itemStack)
                        ? 1.0F
                        : 0.0F
        );
        ItemProperties.register(
                item,
                ResourceLocation.withDefaultNamespace("charged"),
                (itemStack, clientLevel, livingEntity, i) -> CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F
        );
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("firework"), (itemStack, clientLevel, livingEntity, i) -> {
            ChargedProjectiles chargedprojectiles = itemStack.get(DataComponents.CHARGED_PROJECTILES);
            return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });
    }
}
