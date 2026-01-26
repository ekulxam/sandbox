package survivalblock.atmosphere.sandbox.common.init.registrant.delayed;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import survivalblock.atmosphere.sandbox.common.init.registrant.Registrant;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DelayedRegistrant<T> extends Registrant<T> {

    protected final Map<String, T> delayed = new HashMap<>();

    public DelayedRegistrant(String modId, Registry<T> registry) {
        super(modId, registry);
    }

    public DelayedRegistrant(Function<String, ResourceLocation> idFunction, Registry<T> registry) {
        super(idFunction, registry);
    }

    @Override
    protected <U extends T> U register(String name, U obj) {
        this.delayed.put(name, obj);
        return obj;
    }

    public void registerAll() {
        this.delayed.forEach(super::register);
    }
}
