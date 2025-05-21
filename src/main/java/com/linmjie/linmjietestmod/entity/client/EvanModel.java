package com.linmjie.linmjietestmod.entity.client;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.entity.custom.EvanEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.common.Mod;


// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class EvanModel<T extends EvanEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation
            (ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "evan"),"main");
    private final ModelPart evan;
    private final ModelPart head;
    private final ModelPart body;

    public EvanModel(ModelPart root) {
        this.evan = root.getChild("evan");
        this.head = this.evan.getChild("head");
        this.body = this.evan.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition evan = partdefinition.addOrReplaceChild("evan", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -1.0F));

        PartDefinition head = evan.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 26).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 3.0F));

        PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -3.0F));

        PartDefinition cube_r1 = jaw.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 18).addBox(-3.0F, 0.0F, -5.0F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 3.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition main = head.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 1.0F));

        PartDefinition cube_r2 = main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 18).addBox(-3.0F, -5.0F, -6.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

        PartDefinition body = evan.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition upper_body = body.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 5.0F));

        PartDefinition right_arm = upper_body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 30).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -7.0F, -4.0F));

        PartDefinition left_arm = upper_body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 30).addBox(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -7.0F, -4.0F));

        PartDefinition torso = upper_body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -7.0F, -8.0F, 14.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(10, 9).addBox(-5.0F, -5.0F, -9.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-4.0F, -4.0F, -10.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

        PartDefinition lower_body = body.addOrReplaceChild("lower_body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_leg = lower_body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(8, 29).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -7.0F, 1.0F));

        PartDefinition right_leg = lower_body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(8, 29).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -7.0F, 1.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public void setupAnim(EvanEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
            this.root().getAllParts().forEach(ModelPart::resetPose);
            this.applyHeadRotation(pNetHeadYaw, pHeadPitch);

            this.animateWalk(EvanAnimations.walk, pLimbSwing, pLimbSwingAmount, 2f, 2.5f);
            this.animate(pEntity.idleAnimationState, EvanAnimations.idle, pAgeInTicks, 1f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * (float) (Math.PI / 180.0);
        this.head.xRot = pHeadPitch * (float) (Math.PI / 180.0);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        evan.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return evan;
    }
}
