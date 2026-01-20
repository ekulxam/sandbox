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
import net.minecraft.Util;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.commands.Commands;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import survivalblock.atmosphere.sandbox.client.mock.FakeClientWorld;
import survivalblock.atmosphere.sandbox.client.mock.ScreenPlayer;
import survivalblock.atmosphere.sandbox.common.Sandbox;
import survivalblock.atmosphere.sandbox.mixin.WorldLoaderAccessor;

import java.util.concurrent.CompletableFuture;

public class SandboxClient implements ClientModInitializer {
    public static int selectedOnWheel = 1;
    public static int wheelMax = 7;

    private static ScreenPlayer sandboxPlayer = null;
    private static boolean tryingToInit = false;

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
        float prevAngle = 0;
        int color;
        boolean selected;

        for (int i = 0; i < wheelMax + 1; i++) {
            float nextAngle = (360F / wheelMax) * (i + 1) * Mth.DEG_TO_RAD - Mth.HALF_PI;
            Coordinate outside = center.add((int) (Mth.cos(angle) * radius), (int) (Mth.sin(angle) * radius));
            Coordinate inside = center.add((int) (Mth.cos(angle) * radius * 0.35), (int) (Mth.sin(angle) * radius * 0.35));

            if (i > 0) {
                selected = selectedOnWheel == i - 1;
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

    public static CompletableFuture<ClientLevel> createFakeWorld() {
        Minecraft client = Minecraft.getInstance();
        LayeredRegistryAccess<RegistryLayer> layeredRegistryAccess = RegistryLayer.createRegistryAccess();
        CloseableResourceManager closeableResourceManager = new MultiPackResourceManager(PackType.SERVER_DATA, client.getResourcePackRepository().openAllSelected());
        LayeredRegistryAccess<RegistryLayer> layeredRegistryAccess2 = WorldLoaderAccessor.sandbox$invokeLoadAndReplaceLayer(
                closeableResourceManager, layeredRegistryAccess, RegistryLayer.WORLDGEN, RegistryDataLoader.WORLDGEN_REGISTRIES
        );
        RegistryAccess.Frozen frozen = layeredRegistryAccess2.getAccessForLoading(RegistryLayer.DIMENSIONS);
        RegistryAccess.Frozen frozen2 = RegistryDataLoader.load(closeableResourceManager, frozen, RegistryDataLoader.DIMENSION_REGISTRIES);

        return ReloadableServerResources.loadResources(
                        closeableResourceManager,
                        layeredRegistryAccess2,
                        FakeClientWorld.DEFAULT_ENABLED_FEATURES,
                        Commands.CommandSelection.INTEGRATED,
                        2,
                        Util.backgroundExecutor(),
                        client
                )
                .whenComplete((reloadableServerResources, throwable) -> {
                    if (throwable != null) {
                        closeableResourceManager.close();
                    }
                }).thenApply(reloadableServerResources -> new FakeClientWorld(layeredRegistryAccess2.compositeAccess()));
    }

    public static void maybeResetScreenPlayer() {
        if (sandboxPlayer == null) {
            tryingToInit = true;
        }
    }

    @Nullable
    public static ScreenPlayer getSandboxPlayer() {
        if (!tryingToInit) {
            tryingToInit = true;
            SandboxClient.createFakeWorld().whenComplete(
                    (level, throwable) -> {
                        if (throwable != null) {
                            Sandbox.LOGGER.error("An error occurred when creating the ScreenPlayer!", throwable);
                        } else {
                            sandboxPlayer = new ScreenPlayer(level, Minecraft.getInstance().getGameProfile());
                        }
                    }
            );
        }
        return sandboxPlayer;
    }

    public record Coordinate(int x, int y) {
        public Coordinate add(int x, int y) {
            return new Coordinate(this.x - x, this.y - y);
        }
    }
}
