package com.linmjie.linmjietestmod.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class AdvancedCrossbowItem extends CrossbowItem {

    public AdvancedCrossbowItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void performShooting(
            Level pLevel, LivingEntity pShooter, InteractionHand pHand, ItemStack pWeapon, float pVelocity, float pInaccuracy, @Nullable LivingEntity pTarget
    ) {
        if (pLevel instanceof ServerLevel serverlevel) {
            if (pShooter instanceof Player player && net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pWeapon, pShooter.level(), player, 1, true) < 0) return;
            ChargedProjectiles chargedprojectiles = pWeapon.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
            if (chargedprojectiles != null && !chargedprojectiles.isEmpty()) {
                this.shoot(
                        serverlevel, pShooter, pHand, pWeapon, chargedprojectiles.getItems(), pVelocity, pInaccuracy, pShooter instanceof Player, pTarget
                );
                this.shoot(
                        serverlevel, pShooter, pHand, pWeapon, chargedprojectiles.getItems(), pVelocity, pInaccuracy+7.5F, pShooter instanceof Player, pTarget
                );
                if (pShooter instanceof ServerPlayer serverplayer) {
                    CriteriaTriggers.SHOT_CROSSBOW.trigger(serverplayer, pWeapon);
                    serverplayer.awardStat(Stats.ITEM_USED.get(pWeapon.getItem()));
                }
            }
        }
    }
}
