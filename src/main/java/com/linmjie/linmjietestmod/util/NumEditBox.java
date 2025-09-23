package com.linmjie.linmjietestmod.util;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringUtil;

import javax.annotation.Nullable;

public class NumEditBox extends EditBox {

    public NumEditBox(Font pFont, int pX, int pY, int pWidth, int pHeight, Component pMessage) {
        super(pFont, pX, pY, pWidth, pHeight, null, pMessage);
        this.setFilter(NumEditBox::isIntegerOrEmpty);
    }

    private static boolean isIntegerOrEmpty(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return s.isEmpty();
        }
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        if (Character.isDigit(pCodePoint))
            return super.charTyped(pCodePoint, pModifiers);
        return false;
    }
}
