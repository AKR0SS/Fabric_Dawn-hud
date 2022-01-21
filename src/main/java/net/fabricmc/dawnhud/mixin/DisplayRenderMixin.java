package net.fabricmc.dawnhud.mixin;

import io.github.cottonmc.cotton.gui.widget.WButton;
import net.fabricmc.dawnhud.DawnClient;
import net.fabricmc.dawnhud.gui.ConfigGUI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mixin(InGameHud.class)
public class DisplayRenderMixin {
	@Inject(at = @At("TAIL"), method = "render")
	private void render(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {
		MinecraftClient client = MinecraftClient.getInstance();

		if (!client.options.debugEnabled) {
			if (DawnClient.getInstance().config.EnableFPS) // Checks if enabled in Config
				displayFPS(client, matrixStack);

			if (DawnClient.getInstance().config.EnableCoords)
				displayCoords(client, matrixStack);

			if (DawnClient.getInstance().config.EnableTime)
				displayTime(client, matrixStack);
		}
	}

	/* Module Renderers */
	private void displayFPS(MinecraftClient client, MatrixStack matrixStack) {
		int currentFPS;
		String fpsDisplayString;

		currentFPS = getFPS(client);
		fpsDisplayString = currentFPS + " FPS";

		if (currentFPS <= 60)
			client.textRenderer.drawWithShadow(matrixStack, fpsDisplayString, 5, 10, 0xFF0000);
		else if (currentFPS < 100 && currentFPS > 60)
			client.textRenderer.drawWithShadow(matrixStack, fpsDisplayString, 5, 10, 0xE7E700);
		else if (currentFPS >= 100)
			client.textRenderer.drawWithShadow(matrixStack, fpsDisplayString, 5, 10, 0xFFFFFF);
	}
	private void displayCoords(MinecraftClient client, MatrixStack matrixStack) {
		int currentX;
		int currentY;
		int currentZ;

		String coordDisplayString;

		currentX = getX(client);
		currentY = getY(client);
		currentZ = getZ(client);

		coordDisplayString = currentX + ", " + currentY + ", " + currentZ;

		client.textRenderer.drawWithShadow(matrixStack, coordDisplayString, 5, 20, 0xFFFFFF);
	}
	private void displayTime(MinecraftClient client, MatrixStack matrixStack) {
		long worldTime;
		int hours;
		int minutes;
		String clock;

		worldTime = (client.player.world.getTimeOfDay() + 6000);
		hours = (int) (worldTime / 1000) % 24;
		minutes = (int) ((worldTime % 1000) * 60 / 1000);

		if (DawnClient.getInstance().config.Enable12Hours) {
			if (hours < 1)
				clock = (12 + ":" + (minutes < 10 ? ("0" + minutes) : minutes) + " " + ((hours >= 12) ? "PM" : "AM"));
			else
				clock = (hours > 12 ? ("0" + hours % 12) : hours) + ":" + (minutes < 10 ? ("0" + minutes) : minutes) + " " + ((hours >= 12) ? "PM" : "AM");
		}
		else
			clock = hours + ":" + (minutes < 10 ? ("0" + minutes) : minutes);

		client.textRenderer.drawWithShadow(matrixStack, clock, 5, 30, 0xFFFFFF);
	}

	/* Specific method calls to the client */
	private int getFPS(MinecraftClient client) { return ((FPSDisplayMixin) client).getCurrentFPS(); }

	private int getX(MinecraftClient client) { return (int)((client).player.getX()); }
	private int getY(MinecraftClient client) { return (int)((client).player.getY()); }
	private int getZ(MinecraftClient client) { return (int)((client).player.getZ()); }
}