package survivalblock.atmosphere.sandbox.client.mock;

import net.minecraft.util.AbortableIterationConsumer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class EmptyEntityGetter implements LevelEntityGetter<Entity> {
    @Override
    public @Nullable Entity get(int i) {
        return null;
    }

    @Override
    public @Nullable Entity get(UUID uUID) {
        return null;
    }

    @Override
    public Iterable<Entity> getAll() {
        return List.of();
    }

    @Override
    public <U extends Entity> void get(EntityTypeTest<Entity, U> entityTypeTest, AbortableIterationConsumer<U> abortableIterationConsumer) {
    }

    @Override
    public void get(AABB aABB, Consumer<Entity> consumer) {
    }

    @Override
    public <U extends Entity> void get(EntityTypeTest<Entity, U> entityTypeTest, AABB aABB, AbortableIterationConsumer<U> abortableIterationConsumer) {
    }
}
