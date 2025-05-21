package com.linmjie.linmjietestmod.event;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.entity.custom.EvanEntity;
import com.linmjie.linmjietestmod.item.ModItems;
import com.linmjie.linmjietestmod.item.custom.AdvancedShovelItem;
import com.linmjie.linmjietestmod.potions.ModPotions;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = TestingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEventsClass {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    @SubscribeEvent
    public static void onAdvancedShovelUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof AdvancedShovelItem advancedShovel && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : AdvancedShovelItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !advancedShovel.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }
    private static final List<String> evanBananaResponseList = List.of(
            "Evan Britton REALLY liked that",
            "Evan Britton wants you to give him more bananas",
            "Evan Britton wishes you hit him harder",
            "Evan Britton ate the banana",
            "Evan Britton devoured the banana",
            "Evan Britton wants another banana",
            "Evan Britton says \"I love long yellow things shoved down my throat\" -(from Thomas Yu)",
            "Evan Britton says \"I wish that was Tyler's\" -(from Thomas Yu)",
            "Evan Britton says \"Oooo wowww... A big yellow butt plug!\" -(from Tyler Revere)",
            "Evan Britton says \"I'm used to eating bigger bananas\" -(from Kaylie Fainsan)",
            "Evan Britton says \"I'm sorry for cheating on you with Oran I only want you Tyler\" -(from Rhema Erebholo)"
    );
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if(event.getEntity() instanceof EvanEntity evan && event.getSource().getDirectEntity() instanceof Player player) {
            if(player.getMainHandItem().getItem() == ModItems.BANANA.get()) {
                int random = Math.toIntExact(Math.round(Math.random()*evanBananaResponseList.toArray().length-1));
                player.sendSystemMessage(Component.literal(evanBananaResponseList.get(random)));
                evan.addEffect(new MobEffectInstance(MobEffects.HEAL, 1));
            }
        }
    }
    @SubscribeEvent
    public static void onBrewRecipeRegister(BrewingRecipeRegisterEvent event){
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, ModItems.URANIUM.get(), ModPotions.RADIATED_POTION.getHolder().get());
        builder.addMix(ModPotions.RADIATED_POTION.getHolder().get(), Items.REDSTONE, ModPotions.LONG_RADIATED_POTION.getHolder().get());
        builder.addMix(ModPotions.RADIATED_POTION.getHolder().get(), Items.GLOWSTONE_DUST, ModPotions.STRONG_RADIATED_POTION.getHolder().get());
    }
}
