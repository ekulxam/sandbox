package survivalblock.atmosphere.sandbox.common.init;

import net.minecraft.world.item.Item;
import survivalblock.atmosphere.sandbox.common.Sandbox;
import survivalblock.atmosphere.sandbox.common.init.registrant.delayed.DelayedItemRegistrant;
import survivalblock.atmosphere.sandbox.common.item.ComponentUpdateTestItem;

public sealed interface SandboxItems permits SandboxItems.Dummy {
    DelayedItemRegistrant REGISTRANT = new DelayedItemRegistrant(Sandbox::id);

    Item COMPONENT_UPDATE_TEST = REGISTRANT.register("component_update_test", ComponentUpdateTestItem::new, new Item.Properties().stacksTo(1));

    static void init() {
        REGISTRANT.consumeAll();
    }

    final class Dummy implements SandboxItems {
    }
}
