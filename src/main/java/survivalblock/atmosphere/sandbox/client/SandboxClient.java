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
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
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
        final Coordinate center = new Coordinate(drawContext.guiWidth() / 2, drawContext.guiHeight() / 2);
        final int radius = (int) (Math.min(center.x, center.y) * 0.5);

        Coordinate prevOutside = null;
        Coordinate nextInside = null;
        PoseStack matrixStack = drawContext.pose();

        float angle = -Mth.HALF_PI;
        int max = 7;
        float prevAngle = 0;
        int color;
        boolean selected;

        for (int i = 0; i < max + 1; i++) {
            float nextAngle = (360F / max) * (i + 1) * Mth.DEG_TO_RAD - Mth.HALF_PI;
            Coordinate outside = center.add((int) (Mth.cos(angle) * radius), (int) (Mth.sin(angle) * radius));
            Coordinate inside = center.add((int) (Mth.cos(angle) * radius * 0.35), (int) (Mth.sin(angle) * radius * 0.35));

            if (i > 0) {
                selected = (Minecraft.getInstance().level.getGameTime() / 10) % max == i - 1;
                color = selected ? 0x889f9f9f : 0x88484A48;
                RenderSystem.disableCull();
                RenderSystem.enableBlend();
                RenderSystem.blendFuncSeparate(
                        GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO
                );

                Matrix4f matrix4f = matrixStack.last().pose();

                BufferBuilder bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);

                bufferBuilder.addVertex(matrix4f, prevOutside.x, prevOutside.y, 5).setColor(color);
                bufferBuilder.addVertex(matrix4f, outside.x, outside.y, 5).setColor(color);
                bufferBuilder.addVertex(matrix4f, inside.x, inside.y, 5).setColor(color);
                bufferBuilder.addVertex(matrix4f, nextInside.x, nextInside.y, 5).setColor(color);

                RenderSystem.setShader(GameRenderer::getPositionColorShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

                BufferUploader.drawWithShader(bufferBuilder.build());

                RenderSystem.defaultBlendFunc();
                RenderSystem.disableBlend();
            }

            prevOutside = outside;
            angle = nextAngle;
            nextInside = inside;
        }
    }

    public record Coordinate(int x, int y) {
        public Coordinate add(int x, int y) {
            return new Coordinate(this.x - x, this.y - y);
        }
    }
}
