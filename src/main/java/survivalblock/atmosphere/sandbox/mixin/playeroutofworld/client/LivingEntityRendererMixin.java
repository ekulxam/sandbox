package survivalblock.atmosphere.sandbox.mixin.playeroutofworld.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.GuardianRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings({"unused", "UnusedMixin"})
@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    @Inject(method = "setupRotations", at = @At("RETURN"))
    private void pitchGuardian(LivingEntity entity, PoseStack matrices, float f, float g, float h, float i, CallbackInfo ci) {
        if (!((LivingEntityRenderer) (Object) this instanceof GuardianRenderer)) {
            return;
        }
        float halfHeight = entity.getType().getHeight() / 2;
        matrices.translate(0, halfHeight, 0);
        matrices.mulPose(Axis.XP.rotationDegrees(-entity.getXRot()));
        matrices.translate(0, -halfHeight, 0);
    }
}
