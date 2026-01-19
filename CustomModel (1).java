// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class CustomModel extends EntityModel<Entity> {
    private final ModelPart body;
    private final ModelPart body_flipped;
    private final ModelPart body_sub_1;
    private final ModelPart eye;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart spine1;
    private final ModelPart spine1_rotation;
    private final ModelPart spine2;
    private final ModelPart spine2_rotation;
    private final ModelPart spine3;
    private final ModelPart spine3_rotation;
    private final ModelPart spine4;
    private final ModelPart spine4_rotation;
    private final ModelPart spine5;
    private final ModelPart spine5_rotation;
    private final ModelPart spine6;
    private final ModelPart spine6_rotation;
    private final ModelPart spine7;
    private final ModelPart spine7_rotation;
    private final ModelPart spine8;
    private final ModelPart spine8_rotation;
    private final ModelPart spine9;
    private final ModelPart spine9_rotation;
    private final ModelPart spine10;
    private final ModelPart spine10_rotation;
    private final ModelPart spine11;
    private final ModelPart spine11_rotation;
    private final ModelPart spine12;
    private final ModelPart spine12_rotation;
    public CustomModel(ModelPart root) {
        this.body = root.getChild("body");
        this.body_flipped = this.body.getChild("body_flipped");
        this.body_sub_1 = this.body_flipped.getChild("body_sub_1");
        this.eye = root.getChild("eye");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.tail3 = root.getChild("tail3");
        this.spine1 = root.getChild("spine1");
        this.spine1_rotation = this.spine1.getChild("spine1_rotation");
        this.spine2 = root.getChild("spine2");
        this.spine2_rotation = this.spine2.getChild("spine2_rotation");
        this.spine3 = root.getChild("spine3");
        this.spine3_rotation = this.spine3.getChild("spine3_rotation");
        this.spine4 = root.getChild("spine4");
        this.spine4_rotation = this.spine4.getChild("spine4_rotation");
        this.spine5 = root.getChild("spine5");
        this.spine5_rotation = this.spine5.getChild("spine5_rotation");
        this.spine6 = root.getChild("spine6");
        this.spine6_rotation = this.spine6.getChild("spine6_rotation");
        this.spine7 = root.getChild("spine7");
        this.spine7_rotation = this.spine7.getChild("spine7_rotation");
        this.spine8 = root.getChild("spine8");
        this.spine8_rotation = this.spine8.getChild("spine8_rotation");
        this.spine9 = root.getChild("spine9");
        this.spine9_rotation = this.spine9.getChild("spine9_rotation");
        this.spine10 = root.getChild("spine10");
        this.spine10_rotation = this.spine10.getChild("spine10_rotation");
        this.spine11 = root.getChild("spine11");
        this.spine11_rotation = this.spine11.getChild("spine11_rotation");
        this.spine12 = root.getChild("spine12");
        this.spine12_rotation = this.spine12.getChild("spine12_rotation");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, 10.0F, -8.0F, 12.0F, 12.0F, 16.0F, new Dilation(0.0F))
                .uv(16, 40).cuboid(-6.0F, 8.0F, -6.0F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F))
                .uv(16, 40).cuboid(-6.0F, 22.0F, -6.0F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F))
                .uv(0, 28).cuboid(-8.0F, 10.0F, -6.0F, 2.0F, 12.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData body_flipped = body.addChild("body_flipped", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body_sub_1 = body_flipped.addChild("body_sub_1", ModelPartBuilder.create().uv(0, 28).mirrored().cuboid(6.0F, -14.0F, -6.0F, 2.0F, 12.0F, 12.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData eye = modelPartData.addChild("eye", ModelPartBuilder.create().uv(8, 0).cuboid(-1.0F, 15.0F, 0.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.5F, -8.25F));

        ModelPartData tail1 = modelPartData.addChild("tail1", ModelPartBuilder.create().uv(40, 0).cuboid(-2.0F, 14.0F, 7.0F, 4.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData tail2 = modelPartData.addChild("tail2", ModelPartBuilder.create().uv(0, 54).cuboid(0.0F, 14.0F, 0.0F, 3.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, 0.5F, 14.0F));

        ModelPartData tail3 = modelPartData.addChild("tail3", ModelPartBuilder.create().uv(41, 32).cuboid(0.0F, 14.0F, 0.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(25, 19).cuboid(1.0F, 10.5F, 3.0F, 1.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 1.0F, 20.0F));

        ModelPartData spine1 = modelPartData.addChild("spine1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 11.5F, 7.0F));

        ModelPartData spine1_rotation = spine1.addChild("spine1_rotation", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, -0.7854F, 0.0F, 0.0F));

        ModelPartData spine2 = modelPartData.addChild("spine2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 11.5F, -7.0F));

        ModelPartData spine2_rotation = spine2.addChild("spine2_rotation", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, 0.7854F, 0.0F, 0.0F));

        ModelPartData spine3 = modelPartData.addChild("spine3", ModelPartBuilder.create(), ModelTransform.pivot(7.0F, 11.5F, 0.0F));

        ModelPartData spine3_rotation = spine3.addChild("spine3_rotation", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData spine4 = modelPartData.addChild("spine4", ModelPartBuilder.create(), ModelTransform.pivot(-7.0F, 11.5F, 0.0F));

        ModelPartData spine4_rotation = spine4.addChild("spine4_rotation", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData spine5 = modelPartData.addChild("spine5", ModelPartBuilder.create(), ModelTransform.pivot(-7.0F, 18.5F, -7.0F));

        ModelPartData spine5_rotation = spine5.addChild("spine5_rotation", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, 1.5708F, 0.7854F, 0.0F));

        ModelPartData spine6 = modelPartData.addChild("spine6", ModelPartBuilder.create(), ModelTransform.pivot(7.0F, 18.5F, -7.0F));

        ModelPartData spine6_rotation = spine6.addChild("spine6_rotation", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, 1.5708F, -0.7854F, 0.0F));

        ModelPartData spine7 = modelPartData.addChild("spine7", ModelPartBuilder.create(), ModelTransform.pivot(7.0F, 18.5F, 7.0F));

        ModelPartData spine7_rotation = spine7.addChild("spine7_rotation", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, -1.5708F, 0.7854F, 0.0F));

        ModelPartData spine8 = modelPartData.addChild("spine8", ModelPartBuilder.create(), ModelTransform.pivot(-7.0F, 18.5F, 7.0F));

        ModelPartData spine8_rotation = spine8.addChild("spine8_rotation", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, -1.5708F, -0.7854F, 0.0F));

        ModelPartData spine9 = modelPartData.addChild("spine9", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 25.5F, 7.0F));

        ModelPartData spine9_rotation = spine9.addChild("spine9_rotation", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, -2.3562F, 0.0F, 0.0F));

        ModelPartData spine10 = modelPartData.addChild("spine10", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 25.5F, -7.0F));

        ModelPartData spine10_rotation = spine10.addChild("spine10_rotation", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, 2.3562F, 0.0F, 0.0F));

        ModelPartData spine11 = modelPartData.addChild("spine11", ModelPartBuilder.create(), ModelTransform.pivot(7.0F, 25.5F, 0.0F));

        ModelPartData spine11_rotation = spine11.addChild("spine11_rotation", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, 0.0F, 0.0F, 2.3562F));

        ModelPartData spine12 = modelPartData.addChild("spine12", ModelPartBuilder.create(), ModelTransform.pivot(-7.0F, 25.5F, 0.0F));

        ModelPartData spine12_rotation = spine12.addChild("spine12_rotation", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, 0.0F, 0.0F, -2.3562F));
        return TexturedModelData.of(modelData, 16, 16);
    }
    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        eye.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        tail1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        tail2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        tail3.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        spine1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        spine2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        spine3.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        spine4.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        spine5.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        spine6.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        spine7.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        spine8.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        spine9.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        spine10.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        spine11.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        spine12.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}