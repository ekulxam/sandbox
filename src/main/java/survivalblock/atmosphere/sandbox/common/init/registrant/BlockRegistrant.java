package survivalblock.atmosphere.sandbox.common.init.registrant;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

@SuppressWarnings("unused")
public class BlockRegistrant extends Registrant<Block> {
    protected BlockRegistrant(String modId, Registry<Block> registry) {
        super(modId, registry);
    }

    protected BlockRegistrant(Function<String, ResourceLocation> idFunction, Registry<Block> registry) {
        super(idFunction, registry);
    }

    public BlockRegistrant(String modId) {
        this(modId, BuiltInRegistries.BLOCK);
    }

    public BlockRegistrant(Function<String, ResourceLocation> idFunction) {
        this(idFunction, BuiltInRegistries.BLOCK);
    }

    public <T extends Block, S extends BlockBehaviour.Properties> T register(String name, Function<S, T> blockFunction, S settings) {
        T block = blockFunction.apply(settings);
        return this.register(name, block);
    }
}
