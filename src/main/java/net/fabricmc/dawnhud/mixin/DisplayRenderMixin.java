package net.fabricmc.dawnhud.mixin;

import net.fabricmc.dawnhud.DawnClient;
import net.fabricmc.dawnhud.hud.DisplayHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class DisplayRenderMixin {
	@Inject(at = @At("TAIL"), method = "render")
	private void render(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {
		MinecraftClient client = MinecraftClient.getInstance();

		new DisplayHud(matrixStack, client);

		if (!client.options.debugEnabled) {
			if (DawnClient.getInstance().config.EnableFPS) // Checks if enabled in Config
				renderFPS(client);
			if (DawnClient.getInstance().config.EnableCoords)
				renderCoords(client);
			if (DawnClient.getInstance().config.EnableTime)
				renderTime(client);
		}
	}

	/* Module Renderers */
	private void renderFPS(MinecraftClient client) { DisplayHud.displayFPS(getFPS(client)); }
	private void renderTime(MinecraftClient client) { DisplayHud.displayTime((client.player.world.getTimeOfDay() + 6000)); }
	private void renderCoords(MinecraftClient client) {
		int currentX;
		int currentY;
		int currentZ;

		currentX = getX(client);
		currentY = getY(client);
		currentZ = getZ(client);

		DisplayHud.displayCoords(currentX + ", " + currentY + ", " + currentZ);
	}

	/* Method calls to the client */
	private int getFPS(MinecraftClient client) { return ((FPSDisplayMixin) client).getCurrentFPS(); }

	private int getX(MinecraftClient client) { return (int)((client).player.getX()); }
	private int getY(MinecraftClient client) { return (int)((client).player.getY()); }
	private int getZ(MinecraftClient client) { return (int)((client).player.getZ()); }
}