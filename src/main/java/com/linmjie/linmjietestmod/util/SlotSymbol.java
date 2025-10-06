package com.linmjie.linmjietestmod.util;

import com.linmjie.linmjietestmod.TestingMod;
import net.minecraft.resources.ResourceLocation;

public class SlotSymbol {
    private final ResourceLocation resourceLocation;
    private final int instances;
    private final int multiplier;


    SlotSymbol(String resourceLocation, int instances, int multiplier){
        this.resourceLocation = ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "slots_machine/" + resourceLocation);
        this.instances = instances;
        this.multiplier = multiplier;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public int getInstances() {
        return instances;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public static final SlotSymbol IRON = new SlotSymbol("iron", 7, 2);
    public static final SlotSymbol GOLD = new SlotSymbol("gold", 5, 10);
    public static final SlotSymbol DIAMOND = new SlotSymbol("diamond", 2, 50);
    public static final SlotSymbol NETHERITE = new SlotSymbol("netherite", 1, 1000);
}
