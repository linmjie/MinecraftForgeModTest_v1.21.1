package com.linmjie.linmjietestmod.item;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.entity.ModEntities;
import com.linmjie.linmjietestmod.item.custom.*;
import com.linmjie.linmjietestmod.sound.ModSounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestingMod.MOD_ID);

    //REGISTRIES START
    public static final RegistryObject<Item> SOAP = ITEMS.register("soap",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NEON = ITEMS.register("neon",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHARPIE = ITEMS.register("sharpie",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CONDENSED_SOAP = ITEMS.register("condensed_soap",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = ITEMS.register("netherite_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.NETHERITE, AnimalArmorItem.BodyType.EQUESTRIAN
                    ,false, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> URANIUM = ITEMS.register("uranium",
            () -> new FuelItem(new Item.Properties().food(ModFoodProperties.URANIUM), 3600));

    public static final RegistryObject<Item> URANIUM_HELMET = ITEMS.register("uranium_helmet",
            () -> new ModArmorItem(ModArmorMaterials.URANIUM_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
            new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(25))){
        @Override
        public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
            pTooltipComponents.add(Component.translatable("tooltip.linmjietestmod.radioactive_armor_tooltip"));
            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }});
    public static final RegistryObject<Item> URANIUM_CHESTPLATE = ITEMS.register("uranium_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.URANIUM_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(25))){
        @Override
        public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
            pTooltipComponents.add(Component.translatable("tooltip.linmjietestmod.radioactive_armor_tooltip"));
            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        }});
    public static final RegistryObject<Item> URANIUM_LEGGINGS = ITEMS.register("uranium_leggings",
            () -> new ModArmorItem(ModArmorMaterials.URANIUM_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(25))){
        @Override
        public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
            pTooltipComponents.add(Component.translatable("tooltip.linmjietestmod.radioactive_armor_tooltip"));
            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        }});
    public static final RegistryObject<Item> URANIUM_BOOTS = ITEMS.register("uranium_boots",
            () -> new ModArmorItem(ModArmorMaterials.URANIUM_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(25))){
        @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
            pTooltipComponents.add(Component.translatable("tooltip.linmjietestmod.radioactive_armor_tooltip"));
            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        }});
    public static final RegistryObject<Item> REPEATER = ITEMS.register("repeater",
            () -> new AdvancedCrossbowItem(new Item.Properties().durability(500).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)));
    public static final RegistryObject<Item> URANIUM_SWORD = ITEMS.register("uranium_sword",
            () -> new SwordItem(ModToolTiers.URANIUM, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.URANIUM, 3, -2.4F))));
    public static final RegistryObject<Item> URANIUM_PICKAXE = ITEMS.register("uranium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.URANIUM, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.URANIUM, 1, -2.8F))));
    public static final RegistryObject<Item> URANIUM_SHOVEL = ITEMS.register("uranium_shovel",
            () -> new ShovelItem(ModToolTiers.URANIUM, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.URANIUM, 1.5F, -3.0F))));
    public static final RegistryObject<Item> URANIUM_AXE = ITEMS.register("uranium_axe",
            () -> new AxeItem(ModToolTiers.URANIUM, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.URANIUM, 6, -3.2F))));
    public static final RegistryObject<Item> URANIUM_HOE = ITEMS.register("uranium_hoe",
            () -> new HoeItem(ModToolTiers.URANIUM, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.URANIUM, 0, -3.0F))));

    public static final RegistryObject<Item> URANIUM_PLOUGH = ITEMS.register("uranium_plough",
            () -> new AdvancedShovelItem(ModToolTiers.URANIUM, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.URANIUM, 3F, -3.5F))));

    public static final RegistryObject<Item> RAINBOW_SHARD = ITEMS.register("rainbow_shard",
            () -> new Item(new Item.Properties().food((ModFoodProperties.RAINBOW_SHARD))){
                @Override
                public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.linmjietestmod.stardew_not_plagarized_tooltip"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }});
    public static final RegistryObject<Item> RAINBOW_CANDY = ITEMS.register("rainbow_candy",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RAINBOW_CANDY)){
                @Override
                public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.linmjietestmod.stardew_not_plagarized_tooltip"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }});

    public static final RegistryObject<Item> SCRUB_DADDY = ITEMS.register("scrub_daddy",
            () -> new CleaningItem(new Item.Properties().durability(24)));
    public static final RegistryObject<Item> ADVANCED_SCRUB_DADDY = ITEMS.register("advanced_scrub_daddy",
            () -> new CleaningItem(new Item.Properties().durability(320)));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));


    public static final RegistryObject<Item> I_DO_MUSIC_DISC = ITEMS.register("i_do_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.I_DO_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> DRIFT_AWAY_MUSIC_DISC = ITEMS.register("drift_away_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.DRIFT_AWAY_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> JAZZ_JAZZ_JAZZ_MUSIC_DISC = ITEMS.register("jazz_jazz_jazz_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.JAZZ_JAZZ_JAZZ_KEY).stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> VIVACE_MUSIC_DISC = ITEMS.register("vivace_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.VIVACE_KEY).stacksTo(1).rarity(Rarity.RARE)));


    public static final RegistryObject<Item> BETA_EVAN_SPAWN_EGG = ITEMS.register("beta_evan_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BETA_EVAN,  0x53524b, 0xdac741, new Item.Properties()));

    public static final RegistryObject<Item> JACK_BLACK_SPAWN_EGG = ITEMS.register("jack_black_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.JACK_BLACK,  0x00b1af, 0xd99072, new Item.Properties()));


    public static final RegistryObject<Item> BANANA = ITEMS.register("banana",
            () -> new ItemNameBlockItem(ModBlocks.BANANA_BERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.BANANA)));
    public static final RegistryObject<Item> BANANA_BOOTS = ITEMS.register("banana_boots",
            () -> new ModArmorItem(ModArmorMaterials.BANANA_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(12))));

    public static final RegistryObject<Item> BANK_CARD = ITEMS.register("bank_card",
            () -> new BankCardItem(new Item.Properties().stacksTo(1)));

    //REGISTRIES END
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
