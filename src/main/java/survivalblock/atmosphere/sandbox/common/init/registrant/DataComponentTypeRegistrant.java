package survivalblock.atmosphere.sandbox.common.init.registrant;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

@SuppressWarnings("unused")
public class DataComponentTypeRegistrant extends Registrant<DataComponentType<?>> {
    protected DataComponentTypeRegistrant(String modId, Registry<DataComponentType<?>> registry) {
        super(modId, registry);
    }

    protected DataComponentTypeRegistrant(Function<String, ResourceLocation> idFunction, Registry<DataComponentType<?>> registry) {
        super(idFunction, registry);
    }

    public DataComponentTypeRegistrant(String modId) {
        this(modId, BuiltInRegistries.DATA_COMPONENT_TYPE);
    }

    public DataComponentTypeRegistrant(Function<String, ResourceLocation> idFunction) {
        this(idFunction, BuiltInRegistries.DATA_COMPONENT_TYPE);
    }

    public <T> DataComponentType<T> register(String name, DataComponentType.Builder<T> builder) {
        return this.register(name, builder.build());
    }

    public <T> DataComponentType<T> register(String name, DataComponentType<T> entityType) {
        return super.register(name, entityType);
    }
}
