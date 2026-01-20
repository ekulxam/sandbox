package survivalblock.atmosphere.sandbox.mixin.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.atmosphere.sandbox.client.SandboxClient;
import survivalblock.atmosphere.sandbox.client.mock.ScreenPlayer;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Component component) {
        super(component);
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void renderPlayer(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci) {
        EntityRenderDispatcher dispatcher = this.minecraft.getEntityRenderDispatcher();
        ScreenPlayer sandbox$player = SandboxClient.getSandboxPlayer();
        //noinspection ConstantValue
        if (sandbox$player == null || dispatcher.getRenderer(sandbox$player) == null) {
            return;
        }
        dispatcher.prepare(sandbox$player.level(), sandbox$player.camera, null);
        int playerWidth = 75 - 26;
        int playerHeight = 78 - 8;
        int x = this.width / 2 + 104 + 20;
        int y = (this.height / 4 + 48);
        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, x, y, x + playerWidth, y + playerHeight, 30, 0.0625F, i, j, sandbox$player);
    }
}
