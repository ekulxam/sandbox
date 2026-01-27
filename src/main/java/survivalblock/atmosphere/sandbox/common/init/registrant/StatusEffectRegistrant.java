package survivalblock.atmosphere.sandbox.common.init.registrant;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.util.function.Function;

@SuppressWarnings("unused")
public class StatusEffectRegistrant extends Registrant<MobEffect> {
    protected StatusEffectRegistrant(String modId, Registry<MobEffect> registry) {
        super(modId, registry);
    }

    protected StatusEffectRegistrant(Function<String, ResourceLocation> idFunction, Registry<MobEffect> registry) {
        super(idFunction, registry);
    }

    public StatusEffectRegistrant(String modId) {
        this(modId, BuiltInRegistries.MOB_EFFECT);
    }

    public StatusEffectRegistrant(Function<String, ResourceLocation> idFunction) {
        this(idFunction, BuiltInRegistries.MOB_EFFECT);
    }

    public <T extends MobEffect> T register(String name, T effect) {
        return this.registerReference(name, effect).value();
    }

    public <T extends MobEffect> Holder.Reference<T> registerReference(String name, T effect) {
        //noinspection unchecked
        return (Holder.Reference<T>) Registry.registerForHolder(this.registry, this.idFunction.apply(name), effect);
    }
}
