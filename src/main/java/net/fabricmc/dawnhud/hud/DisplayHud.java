package net.fabricmc.dawnhud.hud;

import net.fabricmc.dawnhud.DawnClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class DisplayHud {
    static MatrixStack matrixStack;
    static MinecraftClient client;

    public DisplayHud(MatrixStack nMatrixStack, MinecraftClient nClient) {
        matrixStack = nMatrixStack;
        client = nClient;
    }

    public static void displayFPS(int currentFPS) {
        String fpsDisplayString;

        fpsDisplayString = currentFPS + " FPS";

        if (currentFPS <= 60)
            client.textRenderer.drawWithShadow(matrixStack, fpsDisplayString, 5, 10, 0xFF0000);
        else if (currentFPS < 100)
            client.textRenderer.drawWithShadow(matrixStack, fpsDisplayString, 5, 10, 0xE7E700);
        else
            client.textRenderer.drawWithShadow(matrixStack, fpsDisplayString, 5, 10, 0xFFFFFF);
    }
    public static void displayCoords(String coordDisplayString) {
        client.textRenderer.drawWithShadow(matrixStack, coordDisplayString, 5, 20, 0xFFFFFF);
    }
    public static void displayTime(long worldTime) {
        int hours;
        int minutes;
        String clock;

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

    public static MatrixStack getMatrixStack() {
        return matrixStack;
    }
}
