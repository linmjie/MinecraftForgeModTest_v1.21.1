package com.linmjie.linmjietestmod.block.entity.renderer;

import com.linmjie.linmjietestmod.block.entity.custom.ATMBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class ATMBlockEntityRenderer implements BlockEntityRenderer<ATMBlockEntity>{
    public ATMBlockEntityRenderer(BlockEntityRendererProvider.Context context){

    }

    @Override
    public void render(ATMBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        float[] values = makeItemRenderingValues(pBlockEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING));

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack card = pBlockEntity.inventory.getStackInSlot(0);

        pPoseStack.pushPose();

        pPoseStack.translate(values[0], values[1], values[2]);
        pPoseStack.scale(0.15f, 0.15f, 0.15F);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(values[3]));
        pPoseStack.mulPose(Axis.XN.rotationDegrees(values[4]));
        pPoseStack.mulPose(Axis.YN.rotationDegrees(values[5]));

        itemRenderer.renderStatic(card, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(),
                pBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();


        ItemStack withdrawal = pBlockEntity.inventory.getStackInSlot(2);

        pPoseStack.pushPose();
        pPoseStack.translate(values[6], values[7], values[8]);
        pPoseStack.scale(0.4F, 0.4F, 0.4F);
        pPoseStack.mulPose(Axis.XN.rotationDegrees(values[9]));
        pPoseStack.mulPose(Axis.YN.rotationDegrees(values[10]));
        pPoseStack.mulPose(Axis.ZN.rotationDegrees(values[11]));

        itemRenderer.renderStatic(withdrawal, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(),
                pBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();


        ItemStack deposit = pBlockEntity.inventory.getStackInSlot(1);

        pPoseStack.pushPose();
        pPoseStack.translate(values[6], values[7], values[8]);
        pPoseStack.scale(0.4F, 0.4F, 0.4F);
        pPoseStack.mulPose(Axis.XN.rotationDegrees(values[9]));
        pPoseStack.mulPose(Axis.YN.rotationDegrees(values[10]));
        pPoseStack.mulPose(Axis.ZN.rotationDegrees(values[11]));

        itemRenderer.renderStatic(deposit, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(),
                pBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();

    }

    public static float[] makeItemRenderingValues(Direction direction){
        float x = 0.275F;
        float y = 0.21F;
        float z = 0.05F;
        float i = x+0.675F;
        float j = z+0.2275F;

        float x1 = 0.5F;
        float y1 = -0.15F;
        float z1 = 0.05F;

        return switch (direction){
            case SOUTH -> new float[]{1-x,  y,  1-z,  270F, 0F,   270F,
                                      1-x1, y1, 1-z1, 90F,  180F, 0F};

            case EAST -> new float[] {i,    y,  j,    90F,  90F,  90F,
                                      1-z1, y1, 1-x1, 270F, 0F,   270F};

            case WEST -> new float[] {1-i,  y,  1-j,  90F,  270F, 90F,
                                      z1 ,  y1, x1,   270F, 0F,   90F};

            default -> new float[]   {x,    y,  z,    90F,  0F,   90F,
                                      x1,   y1, z1,   270F, 0F,   0F};
        };
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
