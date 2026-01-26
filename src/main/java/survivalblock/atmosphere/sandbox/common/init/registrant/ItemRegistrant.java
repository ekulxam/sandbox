package survivalblock.atmosphere.sandbox.common.init.registrant;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ItemRegistrant extends Registrant<Item> {
    protected ItemRegistrant(String modId, Registry<Item> registry) {
        super(modId, registry);
    }

    protected ItemRegistrant(Function<String, ResourceLocation> idFunction, Registry<Item> registry) {
        super(idFunction, registry);
    }

    public ItemRegistrant(String modId) {
        this(modId, BuiltInRegistries.ITEM);
    }

    public ItemRegistrant(Function<String, ResourceLocation> idFunction) {
        this(idFunction, BuiltInRegistries.ITEM);
    }

    public <T extends Item, S extends Item.Settings> Item register(String name, Function<S, T> itemFunction, S settings) {
        Item item = itemFunction.apply(settings);
        return this.register(name, item);
    }
}
