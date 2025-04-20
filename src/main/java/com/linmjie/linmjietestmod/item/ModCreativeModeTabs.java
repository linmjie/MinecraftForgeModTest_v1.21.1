package com.linmjie.linmjietestmod.item;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestingMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TESTMOD_ITEMS_TAB = CREATIVE_MODE_TABS.register("testmod_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SHARPIE.get()))
                    .title(Component.translatable("creativetab.linmjietestmod.testmod_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.NEON.get());
                        output.accept(ModItems.SOAP.get());
                        output.accept(ModItems.CONDENSED_SOAP.get());
                        output.accept(ModItems.SHARPIE.get());
                        output.accept(ModItems.SCRUB_DADDY.get());
                        output.accept(ModItems.ADVANCED_SCRUB_DADDY.get());
                        output.accept(ModItems.CHISEL.get());

                    }).build());

    public static final RegistryObject<CreativeModeTab> TESTMOD_BLOCKS_TAB = CREATIVE_MODE_TABS.register("testmod_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.SIGMA_BLOCK.get()))
                    .withTabsBefore(TESTMOD_ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.linmjietestmod.testmod_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.SIGMA_BLOCK.get());
                        output.accept(ModBlocks.SOAP_BLOCK.get());
                        output.accept(ModBlocks.CONDENSED_SOAP_BLOCK.get());
                        output.accept(ModBlocks.NEON_BLOCK.get());
                        output.accept(ModBlocks.NEON_HOLE_BLOCK.get());

                    }).build());



    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
