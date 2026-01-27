package survivalblock.atmosphere.sandbox.common.init.registrant.delayed;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Function;

@SuppressWarnings("unused")
public class DelayedItemRegistrant extends DelayedRegistrant<Item> {
    protected DelayedItemRegistrant(String modId, Registry<Item> registry) {
        super(modId, registry);
    }

    protected DelayedItemRegistrant(Function<String, ResourceLocation> idFunction, Registry<Item> registry) {
        super(idFunction, registry);
    }

    public DelayedItemRegistrant(String modId) {
        this(modId, BuiltInRegistries.ITEM);
    }

    public DelayedItemRegistrant(Function<String, ResourceLocation> idFunction) {
        this(idFunction, BuiltInRegistries.ITEM);
    }

    public <T extends Item, S extends Item.Settings> Item register(String name, Function<S, T> itemFunction, S settings) {
        Item item = itemFunction.apply(settings);
        return this.register(name, item);
    }
}
