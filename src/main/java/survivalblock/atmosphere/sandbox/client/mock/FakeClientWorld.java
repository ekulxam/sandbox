package survivalblock.atmosphere.sandbox.client.mock;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.storage.WritableLevelData;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.ticks.BlackholeTickAccess;
import net.minecraft.world.ticks.LevelTickAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public final class FakeClientWorld extends Level {

    public static final FeatureFlagSet DEFAULT_ENABLED_FEATURES = FeatureFlags.DEFAULT_FLAGS;

    private final TickRateManager tickRateManager = new TickRateManager();
    private final Scoreboard scoreboard = new Scoreboard();
    private final PotionBrewing potionBrewing = PotionBrewing.bootstrap(this.enabledFeatures());
    private final RecipeManager recipeManager = new RecipeManager(this.registryAccess());
    private final LevelEntityGetter<Entity> emptyEntityGetter = new EmptyEntityGetter();
    private final ChunkSource emptyChunkSource = new EmptyChunkSource(this);

    private FakeClientWorld(WritableLevelData writableLevelData, ResourceKey<Level> resourceKey, RegistryAccess registryAccess, Holder<DimensionType> holder, Supplier<ProfilerFiller> supplier, boolean isClientSide, boolean isDebug, long biomeZoomSeed, int maxChainedNeighborUpdates) {
        super(writableLevelData, resourceKey, registryAccess, holder, supplier, isClientSide, isDebug, biomeZoomSeed, maxChainedNeighborUpdates);
    }

    public FakeClientWorld(RegistryAccess registryAccess) {
        this(new FakeLevelData(), Level.OVERWORLD, registryAccess, registryAccess.lookupOrThrow(Registries.DIMENSION_TYPE).getOrThrow(BuiltinDimensionTypes.OVERWORLD), Minecraft.getInstance()::getProfiler, true, false, 0, 0);
    }

    @Override
    public void sendBlockUpdated(BlockPos blockPos, BlockState blockState, BlockState blockState2, int i) {
    }

    @Override
    public void playSeededSound(@Nullable Player player, double d, double e, double f, Holder<SoundEvent> holder, SoundSource soundSource, float g, float h, long l) {
    }

    @Override
    public void playSeededSound(@Nullable Player player, Entity entity, Holder<SoundEvent> holder, SoundSource soundSource, float f, float g, long l) {
    }

    @Override
    public String gatherChunkSourceStats() {
        return "";
    }

    @Override
    public @Nullable Entity getEntity(int i) {
        return null;
    }

    @Override
    public TickRateManager tickRateManager() {
        return this.tickRateManager;
    }

    @Override
    public @Nullable MapItemSavedData getMapData(MapId mapId) {
        return null;
    }

    @Override
    public void setMapData(MapId mapId, MapItemSavedData mapItemSavedData) {
    }

    @Override
    public MapId getFreeMapId() {
        return new MapId(0);
    }

    @Override
    public void destroyBlockProgress(int i, BlockPos blockPos, int j) {
    }

    @Override
    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }

    @Override
    public RecipeManager getRecipeManager() {
        return this.recipeManager;
    }

    @Override
    protected LevelEntityGetter<Entity> getEntities() {
        return this.emptyEntityGetter;
    }

    @Override
    public PotionBrewing potionBrewing() {
        return this.potionBrewing;
    }

    @Override
    public LevelTickAccess<Block> getBlockTicks() {
        return BlackholeTickAccess.emptyLevelList();
    }

    @Override
    public LevelTickAccess<Fluid> getFluidTicks() {
        return BlackholeTickAccess.emptyLevelList();
    }

    @Override
    public ChunkSource getChunkSource() {
        return this.emptyChunkSource;
    }

    @Override
    public void levelEvent(@Nullable Player player, int i, BlockPos blockPos, int j) {
    }

    @Override
    public void gameEvent(Holder<GameEvent> holder, Vec3 vec3, GameEvent.Context context) {
    }

    @Override
    public float getShade(Direction direction, boolean bl) {
        return 0;
    }

    @Override
    public List<? extends Player> players() {
        return List.of();
    }

    @Override
    public Holder<Biome> getUncachedNoiseBiome(int i, int j, int k) {
        return this.registryAccess().registryOrThrow(Registries.BIOME).getHolderOrThrow(Biomes.PLAINS);
    }

    @Override
    public FeatureFlagSet enabledFeatures() {
        return DEFAULT_ENABLED_FEATURES;
    }
}
