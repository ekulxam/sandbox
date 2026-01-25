package survivalblock.atmosphere.sandbox.mixin.exhaustion;

import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@SuppressWarnings({"unused", "UnusedMixin"})
@Debug(export = true)
@Mixin(ServerPlayer.class)
public class ServerPlayerEntityMixin {

    @ModifyArg(method = "checkMovementStatistics", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;causeFoodExhaustion(F)V"))
    private float test(float par1) {
        return par1;
    }
}