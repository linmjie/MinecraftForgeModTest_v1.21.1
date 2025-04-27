package com.linmjie.linmjietestmod.item;

import com.linmjie.linmjietestmod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier URANIUM = new ForgeTier(1400, 7, 2F, 12,
            ModTags.Blocks.NEEDS_URANIUM_TOOL, () -> Ingredient.of(ModItems.URANIUM.get()),
            ModTags.Blocks.INCORRECT_FOR_URANIUM_TOOL);
}
