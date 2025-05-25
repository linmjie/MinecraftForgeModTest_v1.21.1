package com.linmjie.linmjietestmod.entity.client;

import com.linmjie.linmjietestmod.TestingMod;
import com.linmjie.linmjietestmod.entity.custom.EvanEntity;
import com.linmjie.linmjietestmod.entity.custom.JackBlackEntity;
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

public class JackBlackModel<T extends JackBlackEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation
            (ResourceLocation.fromNamespaceAndPath(TestingMod.MOD_ID, "jack_black"),"main");
    private final ModelPart body;
    private final ModelPart head;

    public JackBlackModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition upper = body.addOrReplaceChild("upper", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -2.0F, 1.0F, -0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r1 = upper.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(4, 4).addBox(-1.0F, 0.0F, -1.6F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.5F, -4.0F, -1.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition torso = upper.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

        PartDefinition cube_r2 = torso.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, 0.0F, 0.05F, 3.0F, 2.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.5F, -3.0F, -3.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition cube_r3 = torso.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, -0.8F, 0.2F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.5F, -1.0F, -2.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0563F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 0).addBox(-1.0F, -2.0563F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.07F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.4966F, 0.0436F, 0.0F, 0.0F));

        PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 4).addBox(-1.1F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, -3.0F, 0.5F));

        PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(12, 8).addBox(0.1F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, -3.0F, 0.5F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(12, 4).addBox(-0.4F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -6.0F, 0.5F, 0.0F, 0.0F, 0.0436F));

        PartDefinition right_sleve_roll = right_arm.addOrReplaceChild("right_sleve_roll", CubeListBuilder.create().texOffs(6, -1).addBox(0.602F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.06F))
                .texOffs(8, -1).addBox(-0.4F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.06F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r4 = right_sleve_roll.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(7, 13).addBox(1.002F, -1.0F, -1.6F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.06F)), PartPose.offsetAndRotation(-1.0F, 1.0F, -0.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r5 = right_sleve_roll.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(7, -1).addBox(1.998F, 0.0F, -1.6F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.06F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -2.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(12, 12).addBox(0.4F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -6.0F, 0.5F, 0.0F, 0.0F, -0.0436F));

        PartDefinition left_sleve_roll = left_arm.addOrReplaceChild("left_sleve_roll", CubeListBuilder.create().texOffs(9, 13).addBox(1.402F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.06F))
                .texOffs(7, 13).addBox(0.4F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.06F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r6 = left_sleve_roll.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(8, 13).addBox(2.0F, -1.0F, -0.4F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.06F)), PartPose.offsetAndRotation(1.0F, 1.0F, -2.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r7 = left_sleve_roll.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(6, -1).addBox(1.002F, -1.0F, -0.4F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.06F)), PartPose.offsetAndRotation(1.0F, 1.0F, -0.5F, 0.0F, -1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(JackBlackEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(JackBlackAnimations.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, JackBlackAnimations.idle, ageInTicks, 1f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * (float) (Math.PI / 180.0);
        this.head.xRot = pHeadPitch * (float) (Math.PI / 180.0);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}
