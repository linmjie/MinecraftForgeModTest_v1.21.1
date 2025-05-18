package com.linmjie.linmjietestmod.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdvancedCrossbowItem extends CrossbowItem {
    //abomination that only changes three lines of code from CrossbowItem
    private static final CrossbowItem.ChargingSounds DEFAULT_SOUNDS = new CrossbowItem.ChargingSounds(
            Optional.of(SoundEvents.CROSSBOW_LOADING_START), Optional.of(SoundEvents.CROSSBOW_LOADING_MIDDLE), Optional.of(SoundEvents.CROSSBOW_LOADING_END)
    );
    private boolean startSoundPlayed = false;
    private boolean midLoadSoundPlayed = false;

    public AdvancedCrossbowItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void performShooting(
            Level pLevel, LivingEntity pShooter, InteractionHand pHand, ItemStack pWeapon, float pVelocity, float pInaccuracy, @Nullable LivingEntity pTarget) {
        if (pLevel instanceof ServerLevel serverlevel) {
            if (pShooter instanceof Player player && net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pWeapon, pShooter.level(), player, 1, true) < 0) return;
            ChargedProjectiles chargedprojectiles = pWeapon.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
            if (chargedprojectiles != null && !chargedprojectiles.isEmpty()) {
                this.shoot(
                        serverlevel, pShooter, pHand, pWeapon, chargedprojectiles.getItems(), pVelocity, pInaccuracy, pShooter instanceof Player, pTarget
                );
                this.shoot(
                        serverlevel, pShooter, pHand, pWeapon, chargedprojectiles.getItems(), pVelocity, pInaccuracy+7.5F, pShooter instanceof Player, pTarget
                ); //line one changed
                if (pShooter instanceof ServerPlayer serverplayer) {
                    CriteriaTriggers.SHOT_CROSSBOW.trigger(serverplayer, pWeapon);
                    serverplayer.awardStat(Stats.ITEM_USED.get(pWeapon.getItem()));
                }
            }
        }
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        ChargedProjectiles chargedprojectiles = itemstack.get(DataComponents.CHARGED_PROJECTILES);
        if (chargedprojectiles != null && !chargedprojectiles.isEmpty()) {
            this.performShooting(pLevel, pPlayer, pHand, itemstack, getShootingPower(chargedprojectiles), 1.0F, null);
            return InteractionResultHolder.consume(itemstack);
        } else if (!pPlayer.getProjectile(itemstack).isEmpty() && !(pPlayer.getProjectile(itemstack).getCount() < 2)) { //line two changed
            this.startSoundPlayed = false;
            this.midLoadSoundPlayed = false;
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }
    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        int i = this.getUseDuration(pStack, pEntityLiving) - pTimeLeft;
        float f = getPowerForTime(i, pStack, pEntityLiving);
        if (f >= 1.0F && !isCharged(pStack) && tryLoadTwoProjectiles(pEntityLiving, pStack)) {
            CrossbowItem.ChargingSounds crossbowitem$chargingsounds = this.getChargingSounds(pStack);
            crossbowitem$chargingsounds.end()
                    .ifPresent(
                            p_343691_ -> pLevel.playSound(
                                    null,
                                    pEntityLiving.getX(),
                                    pEntityLiving.getY(),
                                    pEntityLiving.getZ(),
                                    p_343691_.value(),
                                    pEntityLiving.getSoundSource(),
                                    1.0F,
                                    1.0F / (pLevel.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F
                            )
                    );
        }
    }
    private static boolean tryLoadTwoProjectiles(LivingEntity pShooter, ItemStack pCrossbowStack) {
        List<ItemStack> list = drawTwo(pCrossbowStack, pShooter.getProjectile(pCrossbowStack), pShooter);
        if (!list.isEmpty()) {
            pCrossbowStack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(list));
            return true;
        } else {
            return false;
        }
    }
    protected static List<ItemStack> drawTwo(ItemStack pWeapon, ItemStack pAmmo, LivingEntity pShooter) {
        if (pAmmo.isEmpty()) {
            return List.of();
        } else {
            int i = pShooter.level() instanceof ServerLevel serverlevel ? EnchantmentHelper.processProjectileCount(serverlevel, pWeapon, pShooter, 1) : 1;
            List<ItemStack> list = new ArrayList<>(i);
            ItemStack itemstack1 = pAmmo.copy();
            boolean infinite = pAmmo.getItem() instanceof ArrowItem arrow && arrow.isInfinite(pAmmo, pWeapon, pShooter);

            for (int j = 0; j < i; j++) {
                ItemStack itemstack = useTwoAmmo(pWeapon, j == 0 ? pAmmo : itemstack1, pShooter, j > 0 || infinite);
                if (!itemstack.isEmpty()) {
                    list.add(itemstack);
                }
            }

            return list;
        }
    }
    protected static ItemStack useTwoAmmo(ItemStack pWeapon, ItemStack pAmmo, LivingEntity pShooter, boolean pIntangable) {
        int i = !pIntangable && !pShooter.hasInfiniteMaterials() && pShooter.level() instanceof ServerLevel serverlevel
                ? EnchantmentHelper.processAmmoUse(serverlevel, pWeapon, pAmmo, 1)
                : 0;
        if (i > pAmmo.getCount()) {
            return ItemStack.EMPTY;
        } else if (i == 0) {
            ItemStack itemstack1 = pAmmo.copyWithCount(1);
            itemstack1.set(DataComponents.INTANGIBLE_PROJECTILE, Unit.INSTANCE);
            return itemstack1;
        } else {
            ItemStack itemstack = pAmmo.split(2); //line three changed
            if (pAmmo.isEmpty() && pShooter instanceof Player player) {
                player.getInventory().removeItem(pAmmo);
            }

            return itemstack;
        }
    }
    private static float getShootingPower(ChargedProjectiles pProjectile) {
        return pProjectile.contains(Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
    }

    private static float getPowerForTime(int pTimeLeft, ItemStack pStack, LivingEntity pShooter) {
        float f = (float)pTimeLeft / (float)getChargeDuration(pStack, pShooter);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }
    CrossbowItem.ChargingSounds getChargingSounds(ItemStack pStack) {
        return EnchantmentHelper.pickHighestLevel(pStack, EnchantmentEffectComponents.CROSSBOW_CHARGING_SOUNDS).orElse(DEFAULT_SOUNDS);
    }
}
