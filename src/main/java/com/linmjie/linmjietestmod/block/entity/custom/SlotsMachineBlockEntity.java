package com.linmjie.linmjietestmod.block.entity.custom;

import com.linmjie.linmjietestmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class SlotsMachineBlockEntity extends BlockEntity {
    public static final ItemStackHandler inventory = new ItemStackHandler(1);

    public SlotsMachineBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SLOTS_MACHINE_BE.get(), pPos, pBlockState);
    }
}
