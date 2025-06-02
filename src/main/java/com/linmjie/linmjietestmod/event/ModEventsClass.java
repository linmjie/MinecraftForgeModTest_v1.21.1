package com.linmjie.linmjietestmod.event;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.effect.ModEffects;
import com.linmjie.linmjietestmod.entity.custom.EvanEntity;
import com.linmjie.linmjietestmod.item.ModItems;
import com.linmjie.linmjietestmod.item.custom.AdvancedShovelItem;
import com.linmjie.linmjietestmod.potions.ModPotions;
import com.linmjie.linmjietestmod.sound.ModSounds;
import com.linmjie.linmjietestmod.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

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
    private static final String[] evanBananaResponseArray = new String[]{
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
    };
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if(event.getEntity() instanceof EvanEntity evan && event.getSource().getDirectEntity() instanceof Player player) {
            if(player.getMainHandItem().getItem() == ModItems.BANANA.get()) {
                int random = Math.toIntExact(Math.round(Math.random()*evanBananaResponseArray.length-1));
                player.sendSystemMessage(Component.literal(evanBananaResponseArray[random]));
                evan.addEffect(new MobEffectInstance(MobEffects.HEAL, 1));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickItemEvent(PlayerInteractEvent.RightClickItem event){
        if(event.getEntity().getEffect(ModEffects.JACK_BLACKED_EFFECT.getHolder().get()) != null){
            if (event.getItemStack().getItem() == Items.ENDER_PEARL){
                event.getEntity().playSound(ModSounds.ENDER_PEARL.get());
            }

            if (event.getItemStack().getItem() == Items.WATER_BUCKET){
                event.getEntity().playSound(ModSounds.WATER_BUCKET_RELEASE.get());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickBlockEvent(PlayerInteractEvent.RightClickBlock event){
        if(event.getEntity().getEffect(ModEffects.JACK_BLACKED_EFFECT.getHolder().get()) != null){
            if (event.getItemStack().getItem() == Items.FLINT_AND_STEEL){
                event.getEntity().playSound(ModSounds.FLINT_AND_STEEL.get());
            }
        }
    }



    @SubscribeEvent
    public static void onEntityTravelToDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent event){
        if(event.getEntity().getEffect(ModEffects.JACK_BLACKED_EFFECT.getHolder().get()) != null &&
                event.getTo() == Level.NETHER){
            PLAYER_TICK_QUEUE.put(event.getEntity(), 24);
        }
    }

    private static final HashMap<Player, Integer> PLAYER_TICK_QUEUE = new HashMap<>();
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player.level().isClientSide()) {
            if (PLAYER_TICK_QUEUE.containsKey(event.player)) {
                int ticksLeft = PLAYER_TICK_QUEUE.get(event.player);
                if (ticksLeft <= 0) {
                    event.player.playSound(ModSounds.THE_NETHER.get());
                    PLAYER_TICK_QUEUE.remove(event.player);
                } else {
                    PLAYER_TICK_QUEUE.put(event.player, ticksLeft - 1);
                }
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

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){
        if(event.getType() == VillagerProfession.WEAPONSMITH){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 7),
                    new ItemStack(ModItems.URANIUM_SWORD.get(), 1), 6, 4, 0.85F
            ));
        }

        if(event.getType() == ModVillagers.SIGMAGER.get()){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 7),
                    new ItemStack(Items.EMERALD, 1), 12, 4, 0.85F
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD_BLOCK, 1),
                    new ItemStack(Items.EMERALD, 2), 12, 4, 0.85F
            ));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD_ORE, 8),
                    new ItemStack(Items.EMERALD, 3), 10, 6, 0.85F
            ));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.DEEPSLATE_EMERALD_ORE, 1),
                    new ItemStack(Items.EMERALD, 4), 10, 6, 0.85F
            ));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 7),
                    new ItemStack(ModItems.I_DO_MUSIC_DISC.get(), 1), 4, 12, 0.85F
            ));
        }
    }

    @SubscribeEvent
    public static void addWanderingTrades(WandererTradesEvent event){
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 4),
                new ItemStack(ModItems.SOAP.get(), 1), 1, 6, 0.5F
        ));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 14),
                new ItemStack(ModItems.CHISEL.get(), 1), 1, 12, 0.5F
        ));
    }

}
