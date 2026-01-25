package survivalblock.atmosphere.sandbox.mixin.playeroutofworld.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.sandbox.client.SandboxClient;

@SuppressWarnings({"unused", "UnusedMixin"})
@Mixin(MouseHandler.class)
public class MouseMixin {

    @Shadow
    @Final
    private Minecraft minecraft;

    @WrapOperation(method = "onScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;swapPaint(D)V"))
    private void eeeeeeaa(Inventory instance, double scrollAmount, Operation<Void> original) {
        if (Screen.hasAltDown() && this.minecraft.screen == null) {
            SandboxClient.selectedOnWheel = Math.floorMod((SandboxClient.selectedOnWheel + (int) Math.signum(scrollAmount)), SandboxClient.wheelMax);
        }
    }
}