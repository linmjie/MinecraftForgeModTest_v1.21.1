package com.linmjie.linmjietestmod.block;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.custom.NeonTransposerBlock;
import com.linmjie.linmjietestmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TestingMod.MOD_ID);

    //BLOCK REGISTRIES START
    public static final RegistryObject<Block> SOAP_BLOCK = registerBlock("soap_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.5F).sound(SoundType.HONEY_BLOCK)));
    public static final RegistryObject<Block> SIGMA_BLOCK = registerBlock("sigma_block",
            () -> new DropExperienceBlock(UniformInt.of(1237,2476),(BlockBehaviour.Properties.of()
                    .strength(69.420F).requiresCorrectToolForDrops())));
    public static final RegistryObject<Block> NEON_BLOCK = registerBlock("neon_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(10F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> NEON_HOLE_BLOCK = registerBlock("neon_hole_block",
            () -> new NeonTransposerBlock(BlockBehaviour.Properties.of()
                    .strength(2F).noOcclusion().requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> CONDENSED_SOAP_BLOCK = registerBlock("condensed_soap_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_URANIUM_ORE = registerBlock("deepslate_uranium_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(7.5F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> URANIUM_BLOCK = registerBlock("uranium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5F).requiresCorrectToolForDrops()));
    //BLOCK REGISTRIES END

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register (IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
