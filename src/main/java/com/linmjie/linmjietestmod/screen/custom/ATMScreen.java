package com.linmjie.linmjietestmod.screen.custom;

import com.linmjie.linmjietestmod.TestingMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ATMScreen extends AbstractContainerScreen<ATMMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "textures/gui/atm/atm_gui.png");

    private static final ResourceLocation WITHDRAWAL_BUTTON =
            ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "atm/withdrawal_button");
    private static final ResourceLocation WITHDRAWAL_BUTTON_HIGHLIGHTED =
            ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "atm/withdrawal_button_highlighted");

    public ATMScreen(ATMMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        for (int i = 0; i < 4; i++) {
            double d0 = pMouseX - (double)(x + 144);
            double d1 = pMouseY - (double)(y + 17 + i * 20);
            if (d0 >= 0.0 && d1 >= 0.0 && d0 < 24.0 && d1 < 16.0 && this.menu.clickMenuButton(this.minecraft.player, i)) {
                this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, i);
                return true;
            }
        }
        System.out.println(pButton);

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        RenderSystem.setShaderTexture(0, WITHDRAWAL_BUTTON);

        int buttonWidth = 24;
        int buttonHeight = 12;
        for (int i = 0; i < 4; i++) {
            int buttonX = x + 144;
            int buttonY = y + 17 + i * (buttonHeight + 4);

            int mX = pMouseX - buttonX;
            int mY = pMouseY - buttonY;
            RenderSystem.enableBlend();
            if (mX >= 0 && mY >= 0 && mX < buttonWidth && mY < buttonHeight) {
                pGuiGraphics.blitSprite(WITHDRAWAL_BUTTON_HIGHLIGHTED, buttonX, buttonY, buttonWidth, buttonHeight);
            } else {
                pGuiGraphics.blitSprite(WITHDRAWAL_BUTTON, buttonX, buttonY, buttonWidth, buttonHeight);
            }
            String buttonString = i == 0 ? "1x":
                                  i == 1 ? "8x":
                                  i == 2 ? "32x":
                                           "64x";

            int textWidth = font.width(buttonString);
            int textX = buttonX + (buttonWidth - textWidth) / 2;
            int textY = buttonY + (buttonHeight - font.lineHeight) / 2 + 2;

            pGuiGraphics.drawString(font, buttonString, textX, textY, 0x000000, false);
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
