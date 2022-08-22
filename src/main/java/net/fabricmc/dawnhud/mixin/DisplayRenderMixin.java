package net.fabricmc.dawnhud.mixin;

import net.fabricmc.dawnhud.DawnClient;
import net.fabricmc.dawnhud.modules.Coordinates;
import net.fabricmc.dawnhud.modules.Fps;
import net.fabricmc.dawnhud.modules.Time;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class DisplayRenderMixin {
	private MatrixStack matrixStack;

	@Inject(at = @At("TAIL"), method = "render")
	private void render(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {
		MinecraftClient client = MinecraftClient.getInstance();
		this.matrixStack = matrixStack;

		if (!client.options.debugEnabled) { // Checks if enabled in Config
			if (DawnClient.getInstance().config.EnableFPS)
				renderFPS(client);
			if (DawnClient.getInstance().config.EnableCoords)
				renderCoords(client);
			if (DawnClient.getInstance().config.EnableTime)
				renderTime(client);
		}
	}

	/* Module Renderers */
	private void renderFPS(MinecraftClient client) { new Fps().renderFps(client, matrixStack); }
	private void renderTime(MinecraftClient client) { new Time().renderTime(client, matrixStack); }
	private void renderCoords(MinecraftClient client) { new Coordinates().renderCoordinates(client, matrixStack); }
}