package survivalblock.atmosphere.sandbox.client.mock;

import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.lighting.LevelLightEngine;
import org.jetbrains.annotations.Nullable;

import java.util.function.BooleanSupplier;

public class EmptyChunkSource extends ChunkSource {

    private final Level level;
    private final LevelLightEngine lightEngine;

    public EmptyChunkSource(Level level) {
        this.level = level;
        this.lightEngine = new LevelLightEngine(this, true, level.dimensionType().hasSkyLight());
    }

    @Override
    public @Nullable ChunkAccess getChunk(int i, int j, ChunkStatus chunkStatus, boolean bl) {
        return null;
    }

    @Override
    public void tick(BooleanSupplier booleanSupplier, boolean bl) {
    }

    @Override
    public String gatherStats() {
        return "";
    }

    @Override
    public int getLoadedChunksCount() {
        return 0;
    }

    @Override
    public LevelLightEngine getLightEngine() {
        return this.lightEngine;
    }

    @Override
    public BlockGetter getLevel() {
        return this.level;
    }
}
