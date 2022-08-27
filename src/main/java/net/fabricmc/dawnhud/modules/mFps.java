package net.fabricmc.dawnhud.modules;

import net.fabricmc.dawnhud.mixin.FPSDisplayMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class mFps {
    public void renderFps(MinecraftClient client, MatrixStack matrixStack) {
        int currentFPS = getFPS(client);
        String fpsDisplayString;

        fpsDisplayString = currentFPS + " FPS";

        if (currentFPS <= 60)
            client.textRenderer.drawWithShadow(matrixStack, fpsDisplayString, 5, 10, 0xFF0000);
        else if (currentFPS < 100)
            client.textRenderer.drawWithShadow(matrixStack, fpsDisplayString, 5, 10, 0xE7E700);
        else
            client.textRenderer.drawWithShadow(matrixStack, fpsDisplayString, 5, 10, 0xFFFFFF);

    }

    private int getFPS(MinecraftClient client) { return ((FPSDisplayMixin) client).getCurrentFPS(); }
}
