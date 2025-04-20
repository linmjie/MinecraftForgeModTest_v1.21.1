package com.linmjie.linmjietestmod.item;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.item.custom.ChiselItem;
import com.linmjie.linmjietestmod.item.custom.CleaningItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
