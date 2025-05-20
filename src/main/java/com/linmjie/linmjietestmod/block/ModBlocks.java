package com.linmjie.linmjietestmod.block;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.custom.ModFlammableRotatedPillarBlock;
import com.linmjie.linmjietestmod.block.custom.NeonTransposerBlock;
import com.linmjie.linmjietestmod.block.custom.ShinyNeonBlock;
import com.linmjie.linmjietestmod.item.ModItems;
import com.linmjie.linmjietestmod.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
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

    //NEON BLOCK CATEGORY

    public static final RegistryObject<Block> NEON_BRICKS = registerBlock("neon_bricks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(12F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<SlabBlock> NEON_BRICK_SLAB = registerBlock("neon_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(12F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<StairBlock> NEON_BRICK_STAIRS = registerBlock("neon_stairs",
            () -> new StairBlock(ModBlocks.NEON_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(12F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));

    public static final RegistryObject<PressurePlateBlock> NEON_PRESSURE_PLATE = registerBlock("neon_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(12F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<ButtonBlock> NEON_BUTTON = registerBlock("neon_button",
            () -> new ButtonBlock(BlockSetType.IRON,50, BlockBehaviour.Properties.of().strength(12F)
                    .sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops().noCollission()));

    public static final RegistryObject<FenceBlock> NEON_FENCE = registerBlock("neon_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(12F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<FenceGateBlock> NEON_FENCE_GATE = registerBlock("neon_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of().strength(12F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<WallBlock> NEON_WALL = registerBlock("neon_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(12F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));

    public static final RegistryObject<DoorBlock> NEON_DOOR = registerBlock("neon_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(12F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> NEON_TRAPDOOR = registerBlock("neon_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(12F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops().noOcclusion()));

    //NEON BLOCK CATEGORY END

    public static final RegistryObject<Block> SHINY_NEON_BLOCK = registerBlock("shiny_neon_block",
            () -> new ShinyNeonBlock(BlockBehaviour.Properties.of()
                    .strength(5F)
                    .lightLevel(state -> state.getValue(ShinyNeonBlock.CLICKED)? 15 : 0).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));

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
    public static final RegistryObject<Block> END_URANIUM_ORE = registerBlock("end_uranium_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(7.5F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> URANIUM_BLOCK = registerBlock("uranium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5F).requiresCorrectToolForDrops()));

    public static final RegistryObject<RotatedPillarBlock> FIR_LOG = registerBlock("fir_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> FIR_WOOD = registerBlock("fir_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_FIR_LOG = registerBlock("stripped_fir_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_FIR_WOOD = registerBlock("stripped_fir_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> FIR_PLANKS = registerBlock("fir_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> FIR_LEAVES = registerBlock("fir_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> FIR_SAPLING = registerBlock("fir_sapling",
            () -> new SaplingBlock(ModTreeGrowers.FIR, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));


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
