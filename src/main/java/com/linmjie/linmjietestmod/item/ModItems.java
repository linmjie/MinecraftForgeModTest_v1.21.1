package com.linmjie.linmjietestmod.item;

import com.linmjie.linmjietestmod.TestingMod;
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
    //REGISTRIES END
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
