package com.linmjie.linmjietestmod.screen.custom;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.block.entity.custom.SlotsMachineBlockEntity;
import com.linmjie.linmjietestmod.util.NumEditBox;
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

    private final SlotsMachineBlockEntity blockEntity = menu.blockEntity;
    private NumEditBox numberInput;

    public SlotsMachineScreen(SlotsMachineMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.numberInput = new NumEditBox(this.font, i + 46, j + 66, 82, 13, Component.translatable("container.gamble"));
        this.numberInput.setCanLoseFocus(false);
        this.numberInput.setTextColor(-1);
        this.numberInput.setBordered(false);
        this.numberInput.setMaxLength(50);
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

        pGuiGraphics.blitSprite(WITHDRAW_FIELD, x + 43+20, y + 7, 89-20, 17);
        pGuiGraphics.blitSprite(DEPOSIT_FIELD, x + 43+20, y + 61, 89-20, 17);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
        this.numberInput.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
}
