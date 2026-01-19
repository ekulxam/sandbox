package survivalblock.atmosphere.sandbox.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;

public class SandboxClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            Coordinate center = new Coordinate(drawContext.guiWidth() / 2, drawContext.guiHeight() / 2);
            int radius = 20;
            Coordinate previous = center.add(radius, 0);
            Tesselator tessellator = Tesselator.getInstance();
            PoseStack matrixStack = drawContext.pose();
            Matrix4f matrix4f = matrixStack.last().pose();

            drawContext.fill(center.x - 1, center.y - 1, center.x + 1, center.y + 1, 0xFF0000FF);
            drawContext.fill(previous.x - 1, previous.y - 1, previous.x + 1, previous.y + 1, 0xFFFF0000);
            int max = 5;

            for (int i = 1; i < max; i++) {
                float angle = (360F / max) * i * Mth.DEG_TO_RAD;
                Coordinate updated = center.add((int) (Mth.cos(angle) * radius), (int) (Mth.sin(angle) * radius));

                BufferBuilder bufferBuilder = tessellator.begin(VertexFormat.Mode.TRIANGLE_STRIP, DefaultVertexFormat.POSITION);

                bufferBuilder.addVertex(matrix4f, center.x, center.y, 5);
                bufferBuilder.addVertex(matrix4f, previous.x, previous.y, 5);
                bufferBuilder.addVertex(matrix4f, updated.x, updated.y, 5);

                RenderSystem.setShader(GameRenderer::getPositionColorShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

                drawContext.fill(center.x - 1, center.y - 1, center.x + 1, center.y + 1, 0xFF0000FF);
                drawContext.fill(previous.x - 1, previous.y - 1, previous.x + 1, previous.y + 1, 0xFFFF0000);
                drawContext.fill(updated.x - 1, updated.y - 1, updated.x + 1, updated.y + 1, 0xFFFF0000);

                MeshData built = bufferBuilder.build();
                BufferUploader.drawWithShader(built);
                built.close();

                drawContext.fill(updated.x - 1, updated.y - 1, updated.x + 1, updated.y + 1, FastColor.ARGB32.lerp((float) i / max, 0xFFFF0000, 0xFF00FF00));
                previous = updated;
            }
        });
    }

    public record Coordinate(int x, int y) {
        public Coordinate add(int x, int y) {
            return new Coordinate(this.x - x, this.y - y);
        }
    }
}
