package survivalblock.atmosphere.sandbox.common.init.registrant;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Function;

@SuppressWarnings("unused")
public class ItemGroupRegistrant extends Registrant<CreativeModeTab> {
    protected ItemGroupRegistrant(String modId, Registry<CreativeModeTab> registry) {
        super(modId, registry);
    }

    protected ItemGroupRegistrant(Function<String, ResourceLocation> idFunction, Registry<CreativeModeTab> registry) {
        super(idFunction, registry);
    }

    public ItemGroupRegistrant(String modId) {
        this(modId, BuiltInRegistries.CREATIVE_MODE_TAB);
    }

    public ItemGroupRegistrant(Function<String, ResourceLocation> idFunction) {
        this(idFunction, BuiltInRegistries.CREATIVE_MODE_TAB);
    }

    public CreativeModeTab register(String name, CreativeModeTab.Builder builder) {
        return this.register(name, builder.build());
    }

    public <T extends CreativeModeTab> T register(String name, T itemGroup) {
        return super.register(name, itemGroup);
    }
}
