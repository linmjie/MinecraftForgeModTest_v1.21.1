package com.linmjie.linmjietestmod;

import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.item.ModCreativeModeTabs;
import com.linmjie.linmjietestmod.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TestingMod.MOD_ID)
public class TestingMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "linmjietestmod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public TestingMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


        //stuff added
        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);


        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // ADD ITEMS TO CREATIVE MENU
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        //TOOLS TAB
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(ModItems.SCRUB_DADDY);
            event.accept(ModItems.ADVANCED_SCRUB_DADDY);
            event.accept(ModItems.CHISEL);
        }

        //INGREDIENTS TAB
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.SOAP);
            event.accept(ModItems.NEON);
            event.accept(ModItems.SHARPIE);
            event.accept(ModItems.CONDENSED_SOAP);
        }

        //BUILDING BLOCKS TAB
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.SOAP_BLOCK);
            event.accept(ModBlocks.SIGMA_BLOCK);
            event.accept(ModBlocks.CONDENSED_SOAP_BLOCK);
            event.accept(ModBlocks.NEON_BLOCK);
            event.accept(ModBlocks.NEON_HOLE_BLOCK);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
