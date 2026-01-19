package survivalblock.atmosphere.sandbox.client.mock;

import com.mojang.authlib.GameProfile;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ScreenPlayer extends Player {
    public ScreenPlayer(Level level, GameProfile gameProfile) {
        super(level, level.getSharedSpawnPos(), level.getSharedSpawnAngle(), gameProfile);
    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return true;
    }
}
