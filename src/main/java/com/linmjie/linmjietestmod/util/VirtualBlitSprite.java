package com.linmjie.linmjietestmod.util;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class VirtualBlitSprite {
    ResourceLocation resourceLocation;
    int x;
    int y;
    int width;
    int height;
    int miny;
    int maxy;

    public VirtualBlitSprite(ResourceLocation resourceLocation, int x, int y){
        this.resourceLocation = resourceLocation;
        this.x = x;
        this.y = y;

        //Placeholder values
        this.width = 32;
        this.height = 32;
        this.miny = 10;
        this.maxy = 20;
    }

    public void drawIfInVerticalFrame(GuiGraphics pGuiGraphics){
        if(this.y >= miny && this.y <= maxy){
            pGuiGraphics.blitSprite(resourceLocation, x, y, width, height);
        }
    }

    public void changeYPos (int y){
        this.y = y;
    }
}
