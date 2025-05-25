package com.linmjie.linmjietestmod.entity.client;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.entity.custom.JackBlackEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class JackBlackRenderer extends MobRenderer<JackBlackEntity, JackBlackModel<JackBlackEntity>> {
    public JackBlackRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new JackBlackModel<>(pContext.bakeLayer(JackBlackModel.LAYER_LOCATION)), 0.125f);
    }

    @Override
    public ResourceLocation getTextureLocation(JackBlackEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "textures/entity/jack_black/jack_black.png");
    }

    @Override
    public void render(JackBlackEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
            pPoseStack.scale(4.5f, 4.5f, 4.5f);

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
