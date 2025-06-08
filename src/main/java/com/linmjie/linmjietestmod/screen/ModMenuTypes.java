package com.linmjie.linmjietestmod.screen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.screen.custom.ATMMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, TestingMod.MOD_ID);


    public static final RegistryObject<MenuType<ATMMenu>> ATM_MENU =
            MENUS.register("atm_menu", () -> IForgeMenuType.create(ATMMenu::new));

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
