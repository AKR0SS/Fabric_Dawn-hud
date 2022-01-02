package net.fabricmc.dawnhud.mixin;

import net.fabricmc.dawnhud.DawnClient;
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

		int currentFPS;
		int currentX;
		int currentY;
		int currentZ;

		String fpsDisplayString;
		String coordDisplayString;

		if (!client.options.debugEnabled) {
			if (!DawnClient.getInstance().config.EnableFPS) {} // Checks if enabled in Config
			else {
				currentFPS = getFPS(client);
				fpsDisplayString = currentFPS + " FPS";

				if (currentFPS <= 60)
					client.textRenderer.drawWithShadow(matrixStack, fpsDisplayString, 5, 10, 0xFF0000);
				else if (currentFPS < 100 && currentFPS > 60)
					client.textRenderer.drawWithShadow(matrixStack, fpsDisplayString, 5, 10, 0xE7E700);
				else if (currentFPS >= 100)
					client.textRenderer.drawWithShadow(matrixStack, fpsDisplayString, 5, 10, 0xFFFFFF);
			}

			if (!DawnClient.getInstance().config.EnableCoords) {} // Checks if enabled in Config
			else {
				currentX = getX(client);
				currentY = getY(client);
				currentZ = getZ(client);

				coordDisplayString = currentX + ", " + currentY + ", " + currentZ;

				client.textRenderer.drawWithShadow(matrixStack, coordDisplayString, 5, 20, 0xFFFFFF);
			}
		}
	}

	private int getFPS(MinecraftClient client) { return ((FPSDisplayMixin) client).getCurrentFPS(); }

	private int getX(MinecraftClient client) { return (int)((client).player.getX()); }
	private int getY(MinecraftClient client) { return (int)((client).player.getY()); }
	private int getZ(MinecraftClient client) { return (int)((client).player.getZ()); }
}
