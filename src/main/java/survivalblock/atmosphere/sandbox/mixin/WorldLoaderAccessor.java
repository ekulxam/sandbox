package survivalblock.atmosphere.sandbox.mixin;

import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(WorldLoader.class)
public interface WorldLoaderAccessor {
    @Invoker("loadAndReplaceLayer")
    static LayeredRegistryAccess<RegistryLayer> sandbox$invokeLoadAndReplaceLayer(
            ResourceManager resourceManager,
            LayeredRegistryAccess<RegistryLayer> layeredRegistryAccess,
            RegistryLayer registryLayer,
            List<RegistryDataLoader.RegistryData<?>> list
    ) {
        throw new UnsupportedOperationException();
    }
}
