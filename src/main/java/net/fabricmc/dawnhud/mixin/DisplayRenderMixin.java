package net.fabricmc.dawnhud.mixin;

import net.fabricmc.dawnhud.DawnClient;
import net.fabricmc.dawnhud.modules.mCoordinates;
import net.fabricmc.dawnhud.modules.mFps;
import net.fabricmc.dawnhud.modules.mTime;
import net.fabricmc.dawnhud.modules.mBiome;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
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
			if (DawnClient.getInstance().config.EnableBiome)
				renderBiome(client);
		}
	}

	/* Module Renderers */
	private void renderFPS(MinecraftClient client) { new mFps().renderFps(client, matrixStack); }
	private void renderTime(MinecraftClient client) { new mTime().renderTime(client, matrixStack); }
	private void renderCoords(MinecraftClient client) { new mCoordinates().renderCoordinates(client, matrixStack); }
	private void renderBiome(MinecraftClient client) { new mBiome().renderBiome(client, matrixStack); }
}