package survivalblock.atmosphere.sandbox.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.model.GuardianModel;
import net.minecraft.client.model.geom.PartPose;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@SuppressWarnings({"unused", "UnusedMixin"})
@Debug(export = true)
@Mixin(GuardianModel.class)
public class GuardianEntityModelMixin {

    @ModifyExpressionValue(method = "createBodyLayer", at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/geom/PartPose;ZERO:Lnet/minecraft/client/model/geom/PartPose;", ordinal = 0, opcode = Opcodes.GETSTATIC))
    private static PartPose fixPivotPoint(PartPose original) {
        return PartPose.offset(0.0F, 24.0F, 0.0F);
    }
}