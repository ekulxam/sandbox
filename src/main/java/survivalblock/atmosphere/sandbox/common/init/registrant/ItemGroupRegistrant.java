package survivalblock.atmosphere.sandbox.common.init.registrant;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
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

    @Override
    public String getTranslationKey(CreativeModeTab obj) {
        if (obj.getDisplayName().getContents() instanceof TranslatableContents translatable) {
            return translatable.getKey();
        }
        return "itemGroup." + this.registry.getKey(obj).getPath();
    }
}
