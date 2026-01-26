package survivalblock.atmosphere.sandbox.common.init;

import net.minecraft.world.item.Item;
import survivalblock.atmosphere.sandbox.common.Sandbox;
import survivalblock.atmosphere.sandbox.common.init.registrant.ItemRegistrant;
import survivalblock.atmosphere.sandbox.common.item.ComponentUpdateTestItem;

public sealed interface SandboxItems permits SandboxItems.Dummy {
    ItemRegistrant REGISTRANT = new ItemRegistrant(Sandbox::id);

    Item COMPONENT_UPDATE_TEST = REGISTRANT.register("component_update_test", ComponentUpdateTestItem::new, new Item.Properties().stacksTo(1));

    static void init() {
        // NO-OP
    }

    final class Dummy implements SandboxItems {
    }
}
