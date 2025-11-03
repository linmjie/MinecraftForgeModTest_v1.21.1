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

    public VirtualBlitSprite(ResourceLocation resourceLocation, int x, int y, int miny, int maxy){
        this.resourceLocation = resourceLocation;
        this.x = x;
        this.y = y;

        //Placeholder values
        this.width = 16;
        this.height = 16;
        this.miny = miny;
        this.maxy = maxy;
    }

    public void drawIfInVerticalFrame(GuiGraphics pGuiGraphics, int x_shift, int y_shift){
        if(this.y >= miny+y_shift && this.y <= maxy+y_shift){
            pGuiGraphics.blitSprite(resourceLocation, x+x_shift, y+y_shift, width, height);
        }
    }

    public void changeYPos (int y){
        this.y += y;
    }

    public void setY (int y){
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getMiny() {
        return miny;
    }

    public int getMaxy() {
        return maxy;
    }
}
