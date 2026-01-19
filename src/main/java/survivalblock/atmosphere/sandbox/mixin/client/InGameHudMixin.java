package survivalblock.atmosphere.sandbox.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.sandbox.client.SandboxClient;

@SuppressWarnings({"unused", "UnusedMixin"})
@Mixin(Gui.class)
public class InGameHudMixin {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;getPlayerMode()Lnet/minecraft/world/level/GameType;", shift = At.Shift.BEFORE), cancellable = true)
    private void eee(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (Screen.hasAltDown() && this.minecraft.screen == null) {
            SandboxClient.rendering(context, tickCounter);
        }
    }
}