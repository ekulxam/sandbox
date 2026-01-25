package survivalblock.atmosphere.sandbox.common.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import survivalblock.atmosphere.sandbox.common.Sandbox;
import survivalblock.atmosphere.sandbox.common.item.ComponentUpdateTestItem;

import java.util.function.Function;

public class SandboxItems {
    public static final Item COMPONENT_UPDATE_TEST = registerItem("component_update_test", ComponentUpdateTestItem::new, new Item.Properties().stacksTo(1));

    private static <T extends Item, S extends Item.Settings> Item registerItem(String name, Function<S, T> itemFunction, S settings) {
        Item item = itemFunction.apply(settings);
        return Registry.register(BuiltInRegistries.ITEM, Sandbox.id(name), item);
    }

    public static void init() {
        // NO-OP
    }
}
