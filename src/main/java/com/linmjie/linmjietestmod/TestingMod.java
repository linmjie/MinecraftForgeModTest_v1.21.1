package com.linmjie.linmjietestmod;

import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.block.entity.ModBlockEntities;
import com.linmjie.linmjietestmod.block.entity.renderer.ATMBlockEntityRenderer;
import com.linmjie.linmjietestmod.component.ModDataComponentTypes;
import com.linmjie.linmjietestmod.effect.ModEffects;
import com.linmjie.linmjietestmod.enchantment.ModEnchantmentsEffects;
import com.linmjie.linmjietestmod.entity.ModEntities;
import com.linmjie.linmjietestmod.entity.client.EvanRenderer;
import com.linmjie.linmjietestmod.entity.client.JackBlackRenderer;
import com.linmjie.linmjietestmod.item.ModCreativeModeTabs;
import com.linmjie.linmjietestmod.item.ModItems;
import com.linmjie.linmjietestmod.loot.ModLootModifiers;
import com.linmjie.linmjietestmod.potions.ModPotions;
import com.linmjie.linmjietestmod.screen.ModMenuTypes;
import com.linmjie.linmjietestmod.screen.custom.ATMScreen;
import com.linmjie.linmjietestmod.sound.ModSounds;
import com.linmjie.linmjietestmod.util.ModItemProperties;
import com.linmjie.linmjietestmod.villager.ModVillagers;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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

        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);

        ModDataComponentTypes.register(modEventBus);

        ModSounds.register(modEventBus);

        ModEnchantmentsEffects.register(modEventBus);

        ModEntities.register(modEventBus);

        ModVillagers.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModMenuTypes.register(modEventBus);


        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // ADD ITEMS TO CREATIVE MENU
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
            event.accept(ModItems.BETA_EVAN_SPAWN_EGG);
            event.accept(ModItems.JACK_BLACK_SPAWN_EGG);
        }
        //COMBAT TAB
        if (event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(ModItems.URANIUM_SWORD);
            event.accept(ModItems.URANIUM_AXE);
            event.accept(ModItems.URANIUM_HELMET);
            event.accept(ModItems.URANIUM_CHESTPLATE);
            event.accept(ModItems.URANIUM_LEGGINGS);
            event.accept(ModItems.BANANA_BOOTS);
            event.accept(ModItems.URANIUM_BOOTS);
            event.accept(ModItems.NETHERITE_HORSE_ARMOR);
            event.accept(ModItems.REPEATER);
        }
        //TOOLS TAB
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(ModItems.I_DO_MUSIC_DISC);
            event.accept(ModItems.DRIFT_AWAY_MUSIC_DISC);
            event.accept(ModItems.JAZZ_JAZZ_JAZZ_MUSIC_DISC);
            event.accept(ModItems.SCRUB_DADDY);
            event.accept(ModItems.ADVANCED_SCRUB_DADDY);
            event.accept(ModItems.CHISEL);
            event.accept(ModItems.URANIUM_SHOVEL);
            event.accept(ModItems.URANIUM_PICKAXE);
            event.accept(ModItems.URANIUM_AXE);
            event.accept(ModItems.URANIUM_HOE);
            event.accept(ModItems.BANK_CARD);
        }

        //FOOD TAB
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(ModItems.BANANA);
            event.accept(ModItems.RAINBOW_SHARD);
            event.accept(ModItems.RAINBOW_CANDY);
            event.accept(ModBlocks.LAVA_CHICKEN);
        }

        //INGREDIENTS TAB
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.RAINBOW_SHARD);
            event.accept(ModItems.SOAP);
            event.accept(ModItems.NEON);
            event.accept(ModItems.SHARPIE);
            event.accept(ModItems.CONDENSED_SOAP);
            event.accept(ModItems.URANIUM);
        }

        //BUILDING BLOCKS TAB
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.SHINY_NEON_BLOCK);
            event.accept(ModBlocks.SOAP_BLOCK);
            event.accept(ModBlocks.SIGMA_BLOCK);
            event.accept(ModBlocks.CONDENSED_SOAP_BLOCK);
            event.accept(ModBlocks.NEON_BLOCK);
            event.accept(ModBlocks.NEON_HOLE_BLOCK);
            event.accept(ModBlocks.URANIUM_BLOCK);
            event.accept(ModBlocks.NEON_BRICKS);
            event.accept(ModBlocks.NEON_BRICK_STAIRS);
            event.accept(ModBlocks.NEON_BRICK_SLAB);
            event.accept(ModBlocks.NEON_BUTTON);
            event.accept(ModBlocks.NEON_PRESSURE_PLATE);
            event.accept(ModBlocks.NEON_FENCE);
            event.accept(ModBlocks.NEON_FENCE_GATE);
            event.accept(ModBlocks.NEON_WALL);
            event.accept(ModBlocks.NEON_DOOR);
            event.accept(ModBlocks.NEON_TRAPDOOR);
            event.accept(ModBlocks.ATM);
            event.accept(ModBlocks.SLOTS_MACHINE);

        }

        //NATURAL BLOCKS TAB
        if(event.getTabKey()==CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ModBlocks.URANIUM_ORE);
            event.accept(ModBlocks.DEEPSLATE_URANIUM_ORE);
            event.accept(ModBlocks.END_URANIUM_ORE);
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
            ModItemProperties.addCustomItemProperties();
            EntityRenderers.register(ModEntities.BETA_EVAN.get(), EvanRenderer::new);
            EntityRenderers.register(ModEntities.JACK_BLACK.get(), JackBlackRenderer::new);

            MenuScreens.register(ModMenuTypes.ATM_MENU.get(), ATMScreen::new);
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.ATM_BE.get(), ATMBlockEntityRenderer::new);
        }
    }
}
