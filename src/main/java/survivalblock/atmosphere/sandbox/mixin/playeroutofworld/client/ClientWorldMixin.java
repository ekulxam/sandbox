package survivalblock.atmosphere.sandbox.mixin.playeroutofworld.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.RegistryAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.atmosphere.sandbox.client.mock.FakeClientWorld;

@Mixin(ClientLevel.class)
public class ClientWorldMixin {

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;registryAccess()Lnet/minecraft/core/RegistryAccess$Frozen;"))
    private static RegistryAccess.Frozen connectionIsNull(ClientPacketListener instance, Operation<RegistryAccess.Frozen> original, @Local(argsOnly = true)ClientLevel.ClientLevelData clientLevelData) {
        if (instance == null && clientLevelData instanceof FakeClientWorld.LevelDataWithRegistries levelDataWithRegistries) {
            return levelDataWithRegistries.registryAccess.freeze();
        }
        return original.call(instance);
    }
}
