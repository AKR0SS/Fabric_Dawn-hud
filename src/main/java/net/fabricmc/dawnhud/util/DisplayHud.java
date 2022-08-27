package net.fabricmc.dawnhud.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class DisplayHud {
    public static MatrixStack matrixStack;
    MinecraftClient client;

    public DisplayHud(MatrixStack nMatrixStack, MinecraftClient nClient) {
        matrixStack = nMatrixStack;
        this.client = nClient;
    }

    public static MatrixStack getMatrixStack() {
        return matrixStack;
    }
}
