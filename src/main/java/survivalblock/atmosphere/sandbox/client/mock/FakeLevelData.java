package survivalblock.atmosphere.sandbox.client.mock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.storage.WritableLevelData;

public final class FakeLevelData implements WritableLevelData {
    private final GameRules gameRules = new GameRules();

    @Override
    public void setSpawn(BlockPos blockPos, float f) {
    }

    @Override
    public BlockPos getSpawnPos() {
        return BlockPos.ZERO;
    }

    @Override
    public float getSpawnAngle() {
        return 0;
    }

    @Override
    public long getGameTime() {
        return 0;
    }

    @Override
    public long getDayTime() {
        return 0;
    }

    @Override
    public boolean isThundering() {
        return false;
    }

    @Override
    public boolean isRaining() {
        return false;
    }

    @Override
    public void setRaining(boolean bl) {
    }

    @Override
    public boolean isHardcore() {
        return false;
    }

    @Override
    public GameRules getGameRules() {
        return this.gameRules;
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.NORMAL;
    }

    @Override
    public boolean isDifficultyLocked() {
        return true;
    }
}
