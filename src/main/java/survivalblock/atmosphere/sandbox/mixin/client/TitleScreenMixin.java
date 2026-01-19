package survivalblock.atmosphere.sandbox.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.sandbox.client.SandboxClient;
import survivalblock.atmosphere.sandbox.client.mock.ScreenPlayer;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

    @Unique
    @Nullable
    private Player sandbox$player;

    protected TitleScreenMixin(Component component) {
        super(component);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void loadPlayer(CallbackInfo ci) {
        this.sandbox$player = null;
        SandboxClient.createFakeWorld().whenComplete(
                (level, throwable) ->
                        this.sandbox$player = new ScreenPlayer(level, Minecraft.getInstance().getGameProfile())
                );
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void renderPlayer(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci) {
        if (sandbox$player == null) {
            return;
        }
        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, 26, 8, 75, 78, 30, 0.0625F, i, j, sandbox$player);
    }
}
