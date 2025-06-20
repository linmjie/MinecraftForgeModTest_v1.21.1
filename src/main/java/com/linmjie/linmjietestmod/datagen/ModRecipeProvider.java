package com.linmjie.linmjietestmod.datagen;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.item.ModItems;
import com.linmjie.linmjietestmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        final List<ItemLike>URANIUM_SMELTABLES = List.of(
                ModBlocks.URANIUM_ORE.get(),
                ModBlocks.DEEPSLATE_URANIUM_ORE.get()
        );

        oreSmelting(pRecipeOutput, URANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.URANIUM.get(), 0.25F, 200, "uranium");
        oreBlasting(pRecipeOutput, URANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.URANIUM.get(), 0.25F, 100, "uranium");

        oreSmelting(pRecipeOutput, List.of(Items.NETHERITE_INGOT), RecipeCategory.MISC, ModItems.NEON.get(), 5.0F, 500, "neon");
        oreBlasting(pRecipeOutput, List.of(Items.NETHERITE_INGOT), RecipeCategory.MISC, ModItems.NEON.get(), 5.0F, 250, "neon");

        oreSmelting(pRecipeOutput, List.of(ModItems.NEON.get()), RecipeCategory.MISC, Items.NETHERITE_SCRAP, 5.0F, 200, "netherite_scrap");
        oreBlasting(pRecipeOutput, List.of(ModItems.NEON.get()), RecipeCategory.MISC, Items.NETHERITE_SCRAP, 5.0F, 100, "netherite_scrap");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CONDENSED_SOAP.get())
                .pattern(" P ")
                .pattern("OSO")
                .pattern("OOO")
                .define('P', Blocks.PISTON)
                .define('O', Blocks.OBSIDIAN)
                .define('S', ModBlocks.SOAP_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CONDENSED_SOAP_BLOCK.get()), has(ModBlocks.CONDENSED_SOAP_BLOCK.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SIGMA_BLOCK.get())
                .pattern("NDN")
                .pattern("DSD")
                .pattern("NDN")
                .define('N', ModItems.NEON.get())
                .define('D', ModItems.SHARPIE.get())
                .define('S', ModItems.CONDENSED_SOAP.get())
                .unlockedBy(getHasName(ModItems.CONDENSED_SOAP.get()), has(ModItems.CONDENSED_SOAP.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.RAINBOW_CANDY.get())
                .pattern("#S#")
                .pattern("#*#")
                .pattern("SIS")
                .define('#', ModItems.RAINBOW_SHARD.get())
                .define('S', Items.SUGAR)
                .define('*', Items.NETHER_STAR)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(ModItems.RAINBOW_SHARD.get()), has(ModItems.RAINBOW_SHARD.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.BANK_CARD.get(),1)
                .pattern("##")
                .pattern("IR")
                .define('#', Items.LAPIS_LAZULI)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy(getHasName(ModBlocks.ATM.get()), has(ModBlocks.ATM.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ATM.get(),1)
                .pattern("III")
                .pattern("RPC")
                .pattern("IEG")
                .define('C', Items.COMPARATOR)
                .define('R', Items.REPEATER)
                .define('I', Items.IRON_INGOT)
                .define('G', Items.GOLD_INGOT)
                .define('E', Items.EMERALD_BLOCK)
                .define('P', Items.GLASS_PANE)
                .unlockedBy(getHasName(Items.EMERALD_BLOCK), has(Items.EMERALD_BLOCK))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SOAP.get())
                .requires(Items.PORKCHOP)
                .requires(Items.WATER_BUCKET)
                .requires(ItemTags.PLANKS)
                .unlockedBy(getHasName(Items.PORKCHOP), has(Items.PORKCHOP))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SHARPIE.get())
                .requires(Items.INK_SAC)
                .requires(Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.SCRUB_DADDY.get())
                .requires(Blocks.WET_SPONGE)
                .requires(ModItems.SOAP.get())
                .unlockedBy(getHasName(ModItems.SOAP.get()), has (ModItems.SOAP.get()))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.ADVANCED_SCRUB_DADDY.get())
                .requires(Blocks.WET_SPONGE)
                .requires(ModItems.CONDENSED_SOAP.get())
                .unlockedBy(getHasName(ModItems.CONDENSED_SOAP.get()), has (ModItems.CONDENSED_SOAP.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.CHISEL.get())
                .requires(Items.DIAMOND)
                .requires(ModTags.Items.DEEPSLATE_SOLID)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RAINBOW_SHARD.get())
                .requires(Items.DIAMOND)
                .requires(Items.NETHERITE_INGOT)
                .requires(Items.AMETHYST_SHARD)
                .requires(Items.EMERALD)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHINY_NEON_BLOCK.get())
                .requires(ModBlocks.NEON_BLOCK.get())
                .requires(Items.GLOWSTONE_DUST, 3)
                .unlockedBy(getHasName(ModBlocks.NEON_BLOCK.get()), has(ModBlocks.NEON_BLOCK.get()))
                .save(pRecipeOutput);


        ninePacker(pRecipeOutput, ModItems.SOAP.get(), ModBlocks.SOAP_BLOCK.get(), "soap");

        ninePacker(pRecipeOutput, ModItems.CONDENSED_SOAP.get(), ModBlocks.CONDENSED_SOAP_BLOCK.get(),"condensed_soap");

        ninePacker(pRecipeOutput, ModItems.URANIUM.get(), ModBlocks.URANIUM_BLOCK.get(), "uranium");

        //NEON /NEON BRICK
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NEON_BRICKS.get(),4)
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.NEON_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.NEON_BLOCK.get()), has(ModBlocks.NEON_BLOCK.get()))
                .save(pRecipeOutput);
        stairBuilder(ModBlocks.NEON_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.NEON_BRICKS.get())).group("neon")
                .unlockedBy(getHasName(ModBlocks.NEON_BRICKS.get()), has(ModBlocks.NEON_BRICKS.get())).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NEON_BRICK_SLAB.get(), ModBlocks.NEON_BRICKS.get());

        //THROUGH STONECUTTER
        final Map<ItemLike,Integer>NEON_BLOCK_CUTTABLES = Map.of(
                ModBlocks.NEON_BRICKS.get(), 1
        );
        final Map<ItemLike,Integer>NEON_BRICK_CUTTABLES = Map.of(
                ModBlocks.NEON_BRICK_STAIRS.get(),1,
                ModBlocks.NEON_BRICK_SLAB.get(), 2,
                ModBlocks.NEON_WALL.get(), 1
        );
        stonecutterResultFromMap(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NEON_BLOCK.get(), NEON_BLOCK_CUTTABLES);
        stonecutterResultFromMap(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NEON_BLOCK.get(), NEON_BRICK_CUTTABLES);
        stonecutterResultFromMap(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NEON_BRICKS.get(), NEON_BRICK_CUTTABLES);

        buttonBuilder(ModBlocks.NEON_BUTTON.get(), Ingredient.of(ModBlocks.NEON_BLOCK.get())).group("neon")
                .unlockedBy(getHasName(ModBlocks.NEON_BLOCK.get()), has(ModBlocks.NEON_BLOCK.get())).save(pRecipeOutput);
        pressurePlate(pRecipeOutput, ModBlocks.NEON_PRESSURE_PLATE.get(), ModBlocks.NEON_BLOCK.get());

        fenceBuilder(ModBlocks.NEON_FENCE.get(), Ingredient.of(ModBlocks.NEON_BLOCK.get())).group("neon")
                .unlockedBy(getHasName(ModBlocks.NEON_BLOCK.get()), has(ModBlocks.NEON_BLOCK.get())).save(pRecipeOutput);
        fenceGateBuilder(ModBlocks.NEON_FENCE_GATE.get(), Ingredient.of(ModBlocks.NEON_BLOCK.get())).group("neon")
                .unlockedBy(getHasName(ModBlocks.NEON_BLOCK.get()), has(ModBlocks.NEON_BRICKS.get())).save(pRecipeOutput);
        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NEON_WALL.get(), ModBlocks.NEON_BRICKS.get());

        doorBuilder(ModBlocks.NEON_DOOR.get(), Ingredient.of(ModBlocks.NEON_BLOCK.get())).group("neon")
                .unlockedBy(getHasName(ModBlocks.NEON_BLOCK.get()), has(ModBlocks.NEON_BLOCK.get())).save(pRecipeOutput);
        trapdoorBuilder(ModBlocks.NEON_TRAPDOOR.get(), Ingredient.of(ModBlocks.NEON_BLOCK.get())).group("neon")
                .unlockedBy(getHasName(ModBlocks.NEON_BLOCK.get()), has(ModBlocks.NEON_BLOCK.get())).save(pRecipeOutput);

        planksFromLogs(pRecipeOutput, ModBlocks.FIR_PLANKS.get(), ModTags.Items.FIR_LOGS, 4);
        woodFromLogs(pRecipeOutput, ModBlocks.FIR_WOOD.get(), ModBlocks.FIR_LOG.get());
        woodFromLogs(pRecipeOutput, ModBlocks.STRIPPED_FIR_WOOD.get(), ModBlocks.STRIPPED_FIR_LOG.get());


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.REPEATER.get(),1)
                .pattern("INI")
                .pattern("I$I")
                .pattern(" U ")
                .define('I', Items.STICK)
                .define('N', Items.NETHERITE_INGOT)
                .define('$', Items.TRIPWIRE_HOOK)
                .define('U', ModItems.URANIUM.get())
                .unlockedBy(getHasName(ModItems.URANIUM.get()), has(ModItems.URANIUM.get()))
                .save(pRecipeOutput);
        swordRecipe(pRecipeOutput, ModItems.URANIUM.get(), ModItems.URANIUM_SWORD.get());
        shovelRecipe(pRecipeOutput, ModItems.URANIUM.get(), ModItems.URANIUM_SHOVEL.get());
        pickaxeRecipe(pRecipeOutput, ModItems.URANIUM.get(), ModItems.URANIUM_PICKAXE.get());
        axeRecipe(pRecipeOutput, ModItems.URANIUM.get(), ModItems.URANIUM_AXE.get());
        hoeRecipe(pRecipeOutput, ModItems.URANIUM.get(), ModItems.URANIUM_HOE.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.URANIUM_PLOUGH.get())
                .pattern("** ")
                .pattern("*S ")
                .pattern("  #")
                .define('*', ModItems.URANIUM.get())
                .define('S', ModItems.URANIUM_SHOVEL.get())
                .define('#', Items.IRON_INGOT)
                .unlockedBy(getHasName(ModItems.URANIUM_SHOVEL.get()), has(ModItems.URANIUM_SHOVEL.get()))
                .save(pRecipeOutput);

        helmetRecipe(pRecipeOutput, ModItems.URANIUM.get(), ModItems.URANIUM_HELMET.get());
        chestplateRecipe(pRecipeOutput, ModItems.URANIUM.get(), ModItems.URANIUM_CHESTPLATE.get());
        leggingsRecipe(pRecipeOutput, ModItems.URANIUM.get(), ModItems.URANIUM_LEGGINGS.get());
        bootsRecipe(pRecipeOutput, ModItems.URANIUM.get(), ModItems.URANIUM_BOOTS.get());

        netheriteSmithing(pRecipeOutput, Items.DIAMOND_HORSE_ARMOR, RecipeCategory.COMBAT, ModItems.NETHERITE_HORSE_ARMOR.get());
    }

    //HELPER METHODS FOR STANDARDIZED RECIPE TYPES

    //ninePacker with default RecipeCategory.MISC and default "item_block" name for packed block
    protected static void ninePacker(RecipeOutput pRecipeOutput,
                                   ItemLike pUnpacked, ItemLike pPacked,
                                   String pUnpackedName) {
        ninePacker(pRecipeOutput, RecipeCategory.MISC, pUnpacked, pPacked,
                pUnpackedName, pUnpackedName+"_block");
    }
    //ninePacker with specified RecipeCategory and default "item_block" name for packed block
    protected static void ninePacker(RecipeOutput pRecipeOutput, RecipeCategory recipeCategory,
                                   ItemLike pUnpacked, ItemLike pPacked,
                                   String pUnpackedName) {
        ninePacker(pRecipeOutput, recipeCategory, pUnpacked, pPacked,
                pUnpackedName, pUnpackedName+"_block");
    }
    //ninePacker with default RecipeCategory.MISC & specified ids for unpacked and packed items
    protected static void ninePacker(RecipeOutput pRecipeOutput,
                                   ItemLike pUnpacked, ItemLike pPacked,
                                   String pUnpackedName, String pPackedName){
        ninePacker( pRecipeOutput, RecipeCategory.MISC, pUnpacked, pPacked,
                pUnpackedName, pPackedName);
    }
    protected static void ninePacker(RecipeOutput pRecipeOutput, RecipeCategory recipeCategory,
                                   ItemLike pUnpacked, ItemLike pPacked,
                                   String pUnpackedName, String pPackedName){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, pPacked)
                .requires(pUnpacked, 9)
                .unlockedBy(getHasName(pUnpacked), has(pUnpacked))
                .save(pRecipeOutput, TestingMod.MOD_ID+":"+pPackedName+"_from_"+pUnpackedName);
        ShapelessRecipeBuilder.shapeless(recipeCategory, pUnpacked, 9)
                .requires(pPacked)
                .unlockedBy(getHasName(pPacked), has(pPacked))
                .save(pRecipeOutput, TestingMod.MOD_ID+":"+pUnpackedName+"_from_"+pPackedName);
    }

    //stonecutter recipes given a map of results(to quantity of result)
    protected static void stonecutterResultFromMap(RecipeOutput pRecipeOutput, RecipeCategory recipeCategory,
                                               ItemLike pMaterial, Map<ItemLike,Integer> pResultMap){
        for(Map.Entry<ItemLike, Integer> entry: pResultMap.entrySet()){
            ItemLike pResult=entry.getKey();
            int pResultCount=entry.getValue();
            stonecutterResultFromBase(pRecipeOutput, recipeCategory, pResult, pMaterial, pResultCount);
        }

    }

    //Standard Tool Recipes
    protected static void swordRecipe (RecipeOutput pRecipeOutput,
                                       ItemLike pMaterial, ItemLike tool){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, tool)
                .pattern("#")
                .pattern("#")
                .pattern("I")
                .define('#', pMaterial)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void shovelRecipe (RecipeOutput pRecipeOutput,
                                       ItemLike pMaterial, ItemLike tool){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                .pattern("#")
                .pattern("I")
                .pattern("I")
                .define('#', pMaterial)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void pickaxeRecipe (RecipeOutput pRecipeOutput,
                                       ItemLike pMaterial, ItemLike tool){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .define('#', pMaterial)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void axeRecipe (RecipeOutput pRecipeOutput,
                                      ItemLike pMaterial, ItemLike tool){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                .pattern("##")
                .pattern("I#")
                .pattern("I ")
                .define('#', pMaterial)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void hoeRecipe (RecipeOutput pRecipeOutput,
                                       ItemLike pMaterial, ItemLike tool){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                .pattern("##")
                .pattern(" I")
                .pattern(" I")
                .define('#', pMaterial)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void helmetRecipe(RecipeOutput pRecipeOutput,
                                       ItemLike pMaterial, ItemLike armor){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, armor)
                .pattern("###")
                .pattern("# #")
                .define('#', pMaterial)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void chestplateRecipe(RecipeOutput pRecipeOutput,
                                       ItemLike pMaterial, ItemLike armor){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, armor)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', pMaterial)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void leggingsRecipe(RecipeOutput pRecipeOutput,
                                       ItemLike pMaterial, ItemLike armor){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, armor)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', pMaterial)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }
    protected static void bootsRecipe(RecipeOutput pRecipeOutput,
                                       ItemLike pMaterial, ItemLike armor){
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, armor)
                .pattern("# #")
                .pattern("# #")
                .define('#', pMaterial)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }


    //thank you kaupenjoe <3
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, TestingMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
