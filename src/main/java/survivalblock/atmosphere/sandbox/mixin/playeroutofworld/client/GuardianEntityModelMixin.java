package survivalblock.atmosphere.sandbox.mixin.playeroutofworld.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.model.GuardianModel;
import net.minecraft.client.model.geom.ModelPart;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@SuppressWarnings({"unused", "UnusedMixin"})
@Debug(export = true)
@Mixin(GuardianModel.class)
public class GuardianEntityModelMixin {

    @WrapOperation(
            method = "setupAnim(Lnet/minecraft/world/entity/monster/Guardian;FFFFF)V",
            at = {
                    @At(value = "FIELD", target = "Lnet/minecraft/client/model/geom/ModelPart;yRot:F", opcode = Opcodes.PUTFIELD, ordinal = 0),
                    @At(value = "FIELD", target = "Lnet/minecraft/client/model/geom/ModelPart;xRot:F", opcode = Opcodes.PUTFIELD, ordinal = 0)
            }
    )
    private void doNotChangeHeadPitchOrYaw(ModelPart instance, float value, Operation<Void> original) {
        original.call(instance, 0.0F);
    }
}
