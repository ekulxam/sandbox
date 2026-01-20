package survivalblock.atmosphere.sandbox.client.mock;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.PlayerSkin;
import org.jetbrains.annotations.Nullable;

public class ScreenPlayer extends RemotePlayer {

    public final Camera camera = new Camera();
    @Nullable
    protected PlayerSkin skin;

    public ScreenPlayer(ClientLevel level, GameProfile gameProfile) {
        super(level, gameProfile);
        Minecraft.getInstance().getSkinManager().getOrLoad(gameProfile).whenComplete((foundSkin, throwable) -> this.skin = foundSkin);
    }

    @Override
    public boolean isCreative() {
        return true;
    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public PlayerSkin getSkin() {
        return this.skin == null ? DefaultPlayerSkin.get(this.getUUID()) : this.skin;
    }

    @Override
    protected @Nullable PlayerInfo getPlayerInfo() {
        return null;
    }
}
