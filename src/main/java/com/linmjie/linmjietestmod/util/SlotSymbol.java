package com.linmjie.linmjietestmod.util;

import net.minecraft.resources.ResourceLocation;

public class SlotSymbol {
    public final ResourceLocation resourceLocation;
    public float odds;
    public final int reward;


    SlotSymbol(ResourceLocation resourceLocation, float odds, int reward){
        this.resourceLocation = resourceLocation;
        this.odds = odds;
        this.reward = reward;
    }
}
