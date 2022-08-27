package net.fabricmc.dawnhud.modules;

import net.fabricmc.dawnhud.DawnClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class mTime {
    public void renderTime(MinecraftClient client, MatrixStack matrixStack) {
        long worldTime = (client.player.world.getTimeOfDay() + 6000);

        int hours;
        int minutes;
        String clock;

        hours = (int) (worldTime / 1000) % 24;
        minutes = (int) ((worldTime % 1000) * 60 / 1000);

        if (DawnClient.getInstance().config.Enable12Hours) {
            if (hours < 1)
                clock = 12 + ":" + (minutes < 10 ? "0" + minutes : minutes) + " " + "AM";
            else
                clock = (hours > 12 ? ("0" + hours % 12) : hours) + ":" + (minutes < 10 ? ("0" + minutes) : minutes) + " " + ((hours >= 12) ? "PM" : "AM");
        }
        else
            clock = hours + ":" + (minutes < 10 ? ("0" + minutes) : minutes);

        client.textRenderer.drawWithShadow(matrixStack, clock, 5, 30, 0xFFFFFF);
    }
}
