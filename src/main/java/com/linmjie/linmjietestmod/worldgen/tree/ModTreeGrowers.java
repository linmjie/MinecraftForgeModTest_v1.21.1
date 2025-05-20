package com.linmjie.linmjietestmod.worldgen.tree;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower FIR = new TreeGrower(TestingMod.MOD_ID + ":fir",
            Optional.empty(), Optional.of(ModConfiguredFeatures.FIR_KEY), Optional.empty());
}
