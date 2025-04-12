package com.linmjie.linmjietestmod.block;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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
                    .strength(0.2f).requiresCorrectToolForDrops().sound(SoundType.HONEY_BLOCK)));
    public static final RegistryObject<Block> SIGMA_BLOCK = registerBlock("sigma_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(100f).requiresCorrectToolForDrops()));
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
