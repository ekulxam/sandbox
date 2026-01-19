package survivalblock.atmosphere.sandbox.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings({"unused", "UnusedMixin"})
@Mixin(Entity.class)
public class EntityMixin {

    // https://discord.com/channels/507304429255393322/1457555402425241630
    @Inject(method = "tick", at = @At("TAIL"))
    private void moveDatum(CallbackInfo ci) {
        if (!((Entity) (Object) this instanceof ItemEntity dynamic)) {
            return;
        }
        Player datum = dynamic.level().getNearestPlayer(dynamic, 100);
        if (datum == null || !datum.isShiftKeyDown()) {
            return;
        }
        double datumMass   = getMass(datum);
        double dynamicMass = getMass(dynamic);

        double gravitational_force; {
            double distance = dynamic.position().distanceTo( datum.position() );
            gravitational_force = getAttraction(datumMass, dynamicMass, distance );
        }

        double acceleration = getAcceleration(gravitational_force, dynamicMass);
        double seconds_per_tick = 1.0/datum.level().tickRateManager().tickrate();
        double speed = getSpeed(acceleration, seconds_per_tick);

        Vec3 datumPosition = datum.position();
        Vec3 dynamicPosition = dynamic.position();
        Vec3 direction = datumPosition.subtract(dynamicPosition);

        Vec3 velocity = getVelocity(speed, direction);
        dynamic.setDeltaMovement(velocity);
    }

    @Unique
    private static double getMass(Entity entity) {
        AABB box = entity.getBoundingBox();
        return box.getXsize() * box.getYsize() * box.getZsize();
    }

    @Unique
    private static double getAttraction(double massA, double massB, double distance) {
        return 193546 * massA * massB / (distance * distance);
    }

    @Unique
    private static double getAcceleration(double gravity, double mass) {
        return gravity * mass;
    }

    @Unique
    private static double getSpeed(double acceleration, double tickRate) {
        return acceleration * tickRate;
    }

    @Unique
    private static Vec3 getVelocity(double speed, Vec3 direction) {
        double distance = Math.abs( direction.distanceTo(Vec3.ZERO) );
        Vec3 normalized_direction = new Vec3(
                direction.x/distance,
                direction.y/distance,
                direction.z/distance
        );

        return new Vec3(
                speed * normalized_direction.x,
                speed * normalized_direction.y,
                speed * normalized_direction.z
        );
    }
}
