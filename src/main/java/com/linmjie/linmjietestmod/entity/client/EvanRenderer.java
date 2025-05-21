package com.linmjie.linmjietestmod.entity.client;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.entity.custom.EvanEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EvanRenderer extends MobRenderer<EvanEntity, EvanModel<EvanEntity>> {
    public EvanRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new EvanModel<>(pContext.bakeLayer(EvanModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(EvanEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "textures/entity/evan/evan.png");
    }

    @Override
    public void render(EvanEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            pPoseStack.scale(1f, 1f, 1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
