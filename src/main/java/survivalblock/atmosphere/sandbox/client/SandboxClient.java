package survivalblock.atmosphere.sandbox.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;

public class SandboxClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
    }

    @SuppressWarnings("unused")
    public static void rendering(GuiGraphics drawContext, DeltaTracker tickCounter) {
        if (!Screen.hasAltDown()) {
            return;
        }
        Coordinate center = new Coordinate(drawContext.guiWidth() / 2, drawContext.guiHeight() / 2);
        int radius = (int) (Math.min(center.x, center.y) * 0.5);
        Coordinate previous = center.add(0, -radius);
        PoseStack matrixStack = drawContext.pose();
        //drawContext.fill(center.x - 1, center.y - 1, center.x + 1, center.y + 1, 0xFF0000FF);
        //drawContext.fill(previous.x - 1, previous.y - 1, previous.x + 1, previous.y + 1, 0xFFFF0000);
        int max = 7;
        float prevAngle = 0;

        for (int i = 1; i < max + 1; i++) {
            float angle = (360F / max) * i * Mth.DEG_TO_RAD - Mth.HALF_PI;
            float averageAngle = (prevAngle + angle) / 2;
            Coordinate updated = center.add((int) (Mth.cos(angle) * radius), (int) (Mth.sin(angle) * radius));
            Coordinate newCenter = //center.add((int) (Mth.cos(prevAngle) * radius * 0.5), (int) (Mth.sin(prevAngle) * radius * 0.5));
                    center;

            //drawContext.fill(updated.x - 1, updated.y - 1, updated.x + 1, updated.y + 1, Masonry.ColorHelper.lerp((float) i / max, 0xFFFF0000, 0xFF00FF00));

            BufferBuilder bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);

            Matrix4f matrix4f = matrixStack.last().pose();
            RenderSystem.disableCull();
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(
                    GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO
            );
            bufferBuilder.addVertex(matrix4f, previous.x, previous.y, 5).setColor(0x88484A48);
            bufferBuilder.addVertex(matrix4f, updated.x, updated.y, 5).setColor(0x88484A48);
            bufferBuilder.addVertex(matrix4f, newCenter.x, newCenter.y, 5).setColor(0x88484A48);

            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            BufferUploader.drawWithShader(bufferBuilder.build());

            previous = updated;
            prevAngle = angle;
        }
    }

    public record Coordinate(int x, int y) {
        public Coordinate add(int x, int y) {
            return new Coordinate(this.x - x, this.y - y);
        }
    }
}
