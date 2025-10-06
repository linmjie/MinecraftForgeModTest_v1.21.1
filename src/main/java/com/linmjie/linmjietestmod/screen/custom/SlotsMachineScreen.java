package com.linmjie.linmjietestmod.screen.custom;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.entity.custom.SlotsMachineBlockEntity;
import com.linmjie.linmjietestmod.util.NumEditBox;
import com.linmjie.linmjietestmod.util.SlotSymbol;
import com.linmjie.linmjietestmod.util.VirtualBlitSprite;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlotsMachineScreen extends AbstractContainerScreen<SlotsMachineMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "textures/gui/slots_machine/slots_machine_gui.png");
    private static final ResourceLocation WITHDRAW_FIELD =
            ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "slots_machine/withdraw_field");
    private static final ResourceLocation DEPOSIT_FIELD =
            ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "slots_machine/deposit_field");

    private final VirtualBlitSprite[] virtualBlitSprites1;
    private final VirtualBlitSprite[] virtualBlitSprites2;
    private final VirtualBlitSprite[] virtualBlitSprites3;

    private final SlotsMachineBlockEntity blockEntity = menu.blockEntity;
    private NumEditBox numberInput;

    public SlotsMachineScreen(SlotsMachineMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageHeight = 222;
        this.inventoryLabelY = this.imageHeight - 94;

        SlotSymbol[] slotSymbols = this.menu.blockEntity.getSlotSymbols();
        virtualBlitSprites1 = new VirtualBlitSprite[slotSymbols.length];
        virtualBlitSprites2 = new VirtualBlitSprite[slotSymbols.length];
        virtualBlitSprites3 = new VirtualBlitSprite[slotSymbols.length];

        for (int i = 0; i < slotSymbols.length; i++) {
            //placeholder values
            virtualBlitSprites1[i] = new VirtualBlitSprite(slotSymbols[i].getResourceLocation(), 12, 50 + i*48);
            virtualBlitSprites2[i] = new VirtualBlitSprite(slotSymbols[i].getResourceLocation(), 24, 50 + i*48);
            virtualBlitSprites3[i] = new VirtualBlitSprite(slotSymbols[i].getResourceLocation(), 36, 50 + i*48);
        }
    }

    @Override
    protected void init() {
        super.init();
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.numberInput = new NumEditBox(this.font, i + 45, j + 113, 82, 13, Component.translatable("container.gamble"));
        this.numberInput.setCanLoseFocus(false);
        this.numberInput.setTextColor(-1);
        this.numberInput.setBordered(false);
        this.numberInput.setMaxLength(80);
        this.numberInput.setResponder(this::onNumberInput);
        this.numberInput.setValue("");
        this.numberInput.setVisible(true);
        this.addWidget(numberInput);
    }

    private void onNumberInput(String s){
        try{
            Integer.parseInt(s);
        } catch (NumberFormatException ignored){}
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        RenderSystem.setShaderTexture(0, WITHDRAW_FIELD);
        RenderSystem.setShaderTexture(0, DEPOSIT_FIELD);

        pGuiGraphics.blitSprite(WITHDRAW_FIELD, x + 42, y + 18, 89, 17);
        String withdrawString = this.blockEntity.getWithdrawStr();
        int textWidth = font.width(withdrawString);
        int textX = x + 42 + (89 - textWidth) / 2;
        int textY = y + 18 + (17 - font.lineHeight) / 2 + 2;

        pGuiGraphics.drawString(font, withdrawString, textX, textY, 0x000000, false);

        pGuiGraphics.blitSprite(DEPOSIT_FIELD, x + 42, y + 107, 89, 17);

        //Slots visuals
        for (int i = 0; i < virtualBlitSprites1.length; i++) {
            virtualBlitSprites1[i].drawIfInVerticalFrame(pGuiGraphics);
            virtualBlitSprites2[i].drawIfInVerticalFrame(pGuiGraphics);
            virtualBlitSprites3[i].drawIfInVerticalFrame(pGuiGraphics);
        }
    }



    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
        this.numberInput.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
}
