package com.linmjie.linmjietestmod.item;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.item.custom.AdvancedShovelItem;
import com.linmjie.linmjietestmod.item.custom.ChiselItem;
import com.linmjie.linmjietestmod.item.custom.CleaningItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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

    public static final RegistryObject<Item> URANIUM = ITEMS.register("uranium",
            () -> new FuelItem(new Item.Properties(), 3600));

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

    //REGISTRIES END
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
