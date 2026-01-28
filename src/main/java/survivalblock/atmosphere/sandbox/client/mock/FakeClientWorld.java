package survivalblock.atmosphere.sandbox.client.mock;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.ticks.BlackholeTickAccess;
import net.minecraft.world.ticks.LevelTickAccess;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public final class FakeClientWorld extends ClientLevel {

    public static final FeatureFlagSet DEFAULT_ENABLED_FEATURES = FeatureFlags.DEFAULT_FLAGS;

    private final Scoreboard scoreboard = new Scoreboard();
    private final PotionBrewing potionBrewing = PotionBrewing.bootstrap(this.enabledFeatures());
    private final RecipeManager recipeManager = new RecipeManager(this.registryAccess());
    private final LevelEntityGetter<Entity> emptyEntityGetter = new EmptyEntityGetter();

    public FakeClientWorld(ClientPacketListener clientPacketListener, ClientLevelData clientLevelData, ResourceKey<Level> resourceKey, Holder<DimensionType> holder, int serverChunkRadius, int serverSimulationDistance, Supplier<ProfilerFiller> supplier, LevelRenderer levelRenderer, boolean isDebug, long biomeZoomSeed) {
        super(clientPacketListener, clientLevelData, resourceKey, holder, serverChunkRadius, serverSimulationDistance, supplier, levelRenderer, isDebug, biomeZoomSeed);
    }

    public FakeClientWorld(RegistryAccess registryAccess) {
        this(null, new LevelDataWithRegistries(Difficulty.NORMAL, false, false, registryAccess), Level.OVERWORLD, registryAccess.lookupOrThrow(Registries.DIMENSION_TYPE).getOrThrow(BuiltinDimensionTypes.OVERWORLD), 4, 4, Minecraft.getInstance()::getProfiler, Minecraft.getInstance().levelRenderer, false, 0);
    }

    /*
    private FakeClientWorld(WritableLevelData writableLevelData, ResourceKey<Level> resourceKey, RegistryAccess registryAccess, Holder<DimensionType> holder, Supplier<ProfilerFiller> supplier, boolean isClientSide, boolean isDebug, long biomeZoomSeed, int maxChainedNeighborUpdates) {
        super(writableLevelData, resourceKey, registryAccess, holder, supplier, isClientSide, isDebug, biomeZoomSeed, maxChainedNeighborUpdates);
    }

    public FakeClientWorld(RegistryAccess registryAccess) {
        this(new FakeLevelData(), Level.OVERWORLD, registryAccess, registryAccess.lookupOrThrow(Registries.DIMENSION_TYPE).getOrThrow(BuiltinDimensionTypes.OVERWORLD), Minecraft.getInstance()::getProfiler, true, false, 0, 0);
    }*/

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
    public void sendPacketToServer(Packet<?> packet) {
    }

    @Override
    public void disconnect() {
    }

    @Override
    public FeatureFlagSet enabledFeatures() {
        return DEFAULT_ENABLED_FEATURES;
    }

    public static class LevelDataWithRegistries extends ClientLevelData {
        public final RegistryAccess registryAccess;

        public LevelDataWithRegistries(Difficulty difficulty, boolean bl, boolean bl2, RegistryAccess registryAccess) {
            super(difficulty, bl, bl2);
            this.registryAccess = registryAccess;
        }
    }
}
