package net.fabricmc.dawnhud.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class mCoordinates {
    public void renderCoordinates(MinecraftClient client, MatrixStack matrixStack) {
        int currentX = getX(client);
        int currentY = getY(client);
        int currentZ = getZ(client);

        client.textRenderer.drawWithShadow(matrixStack, currentX + ", " + currentY + ", " + currentZ, 5, 20, 0xFFFFFF);
    }

    private int getX(MinecraftClient client) { return (int)((client).player.getX()); }
    private int getY(MinecraftClient client) { return (int)((client).player.getY()); }
    private int getZ(MinecraftClient client) { return (int)((client).player.getZ()); }

}
