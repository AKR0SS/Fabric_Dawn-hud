package net.fabricmc.dawnhud.modules;

import net.fabricmc.dawnhud.DawnClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class mTime {
    public void renderTime(MinecraftClient client, MatrixStack matrixStack) {
        long worldTime = (client.player.world.getTimeOfDay() + 6000);

        int hours;
        int minutes;
        String clock = "";

        hours = (int) (worldTime / 1000) % 24;
        minutes = (int) ((worldTime % 1000) * 60 / 1000);

        if (DawnClient.getInstance().config.Enable12Hours) {// Now ik this looks bad, and it is, I'm sorry
            clock = (hours < 10) ? (hours < 1 ? "12" : "0" + hours) : (hours > 12 && hours < 22) ? "0" + hours % 12 : (hours == 12 ? "12" : String.valueOf(hours % 12));
            clock += ":";
            clock += (minutes < 10) ? ("0" + minutes) : (minutes);
            clock +=  (hours < 12) ? " AM" : " PM";

//            if (minutes < 10) {
//                clock += "0" + minutes;
//            }
//            else
//                clock += minutes;
//
//            if (hours <= 12)
//                clock += " AM";
//            else
//                clock += " PM";
        }
        else
            clock = hours + ":" + (minutes < 10 ? ("0" + minutes) : minutes);

        client.textRenderer.drawWithShadow(matrixStack, clock, 5, 30, 0xFFFFFF);
    }
}
