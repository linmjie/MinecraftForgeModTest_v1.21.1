package com.linmjie.linmjietestmod.block.entity;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.ModBlocks;
import com.linmjie.linmjietestmod.block.entity.custom.ATMBlockEntity;
import com.linmjie.linmjietestmod.block.entity.custom.SlotsMachineBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TestingMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ATMBlockEntity>> ATM_BE =
            BLOCK_ENTITIES.register("atm_be", () -> BlockEntityType.Builder.of(
                    ATMBlockEntity::new, ModBlocks.ATM.get()).build(null));

    public static final RegistryObject<BlockEntityType<SlotsMachineBlockEntity>> SLOTS_MACHINE_BE =
            BLOCK_ENTITIES.register("slots_machine_be", () -> BlockEntityType.Builder.of(
                    SlotsMachineBlockEntity::new, ModBlocks.SLOTS_MACHINE.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
