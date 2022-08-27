package net.fabricmc.dawnhud.gui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WTabPanel;
import io.github.cottonmc.cotton.gui.widget.WText;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.fabricmc.dawnhud.DawnClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class PanelB extends ConfigGUI {
    static void setPanelB(WTabPanel tabs, WPlainPanel panelB) {
        /* Panel Settings */
        panelB.setSize(tabs.getWidth(), tabs.getHeight());
        panelB.setInsets(Insets.ROOT_PANEL);

        /* Module Definition */
        /* headers */
        WText positionHeaderText = new WText(positionHeader);
        WText colorHeaderText = new WText(colorHeader);
        WText timeHeaderText = new WText(timeHeader);

        /* buttons */
        WButton editPosition = new WButton(Text.translatable("dawnhud.config.advanced.edit_position"));
        WButton editColors = new WButton(Text.translatable("dawnhud.config.advanced.edit_colors"));
        WButton hours12 = new WButton(Text.translatable("dawnhud.config.advanced.12hour"));
        WButton hours24 = new WButton(Text.translatable("dawnhud.config.advanced.24hour"));

        /* Modules */
        setClockPeriod(hours12, hours24);
        //createPositionScreen(editPosition);

        editColors.setOnClick(() -> {
            MinecraftClient.getInstance().setScreen(null);
            //MinecraftClient.getInstance().setScreen(new CottonClientScreen(new ColorEditor()));
        });

        /* Adding Modules to Panel Render */
            /* Panel Add */
            panelB.add(positionHeaderText, panelB.getWidth() / 4, 6, panelB.getWidth() / 2, 20);
            panelB.add(colorHeaderText, panelB.getWidth() / 4, 46, panelB.getWidth() / 2, 20);
            panelB.add(timeHeaderText, panelB.getWidth() / 4, 86, panelB.getWidth() / 2, 20);

            panelB.add(editPosition, panelB.getWidth() / 4 + 6, 20, panelB.getWidth() / 2, 20);
            panelB.add(editColors, panelB.getWidth() / 4 + 6, 60, panelB.getWidth() / 2, 20);
            panelB.add(hours12, 6, 100, panelB.getWidth() / 2 - 6, 20);
            panelB.add(hours24, panelB.getWidth() / 2 + 12, 100, panelB.getWidth() / 2 - 12, 20);

            /* Alignment */
            editPosition.setAlignment(HorizontalAlignment.CENTER);
            editColors.setAlignment(HorizontalAlignment.CENTER);

            positionHeaderText.setHorizontalAlignment(HorizontalAlignment.CENTER);
            colorHeaderText.setHorizontalAlignment(HorizontalAlignment.CENTER);
            timeHeaderText.setHorizontalAlignment(HorizontalAlignment.CENTER);
    }

    /* saves to config */
    private static void setClockPeriod(WButton hours12, WButton hours24) {
        /* Reads and sets INITIAL value upon opening settings gui */
        if (DawnClient.getInstance().config.Enable12Hours) {
            hours12.setLabel(Text.translatable("dawnhud.config.advanced.12hour").append(": ").append(Text.translatable("dawnhud.on").formatted(Formatting.GREEN)));
            hours24.setLabel(Text.translatable("dawnhud.config.advanced.24hour").append(": ").append(Text.translatable("dawnhud.off").formatted(Formatting.RED)));
        }
        else {
            hours12.setLabel(Text.translatable("dawnhud.config.advanced.12hour").append(": ").append(Text.translatable("dawnhud.off").formatted(Formatting.RED)));
            hours24.setLabel(Text.translatable("dawnhud.config.advanced.24hour").append(": ").append(Text.translatable("dawnhud.on").formatted(Formatting.GREEN)));
        }

        hours12.setOnClick(() -> {
            if (DawnClient.getInstance().config.Enable12Hours) {
                /* -s displayClockPeriod(WButton, WButton):void Reads and sets a CHANGED value */
                displayClockPeriod(hours12, hours24);
                DawnClient.getInstance().config.Enable24Hours = true;
                DawnClient.getInstance().config.Enable12Hours = false;
            }
            else {
                displayClockPeriod(hours12, hours24);
                DawnClient.getInstance().config.Enable24Hours = false;
                DawnClient.getInstance().config.Enable12Hours = true;
            }

            DawnClient.getInstance().saveConfig();
        });
        hours24.setOnClick(() -> {
            if (DawnClient.getInstance().config.Enable24Hours) {
                displayClockPeriod(hours12, hours24);
                DawnClient.getInstance().config.Enable24Hours = false;
                DawnClient.getInstance().config.Enable12Hours = true;
            }
            else {
                displayClockPeriod(hours12, hours24);
                DawnClient.getInstance().config.Enable24Hours = true;
                DawnClient.getInstance().config.Enable12Hours = false;
            }

            DawnClient.getInstance().saveConfig();
        });
    }
    private static void displayClockPeriod(WButton hours12, WButton hours24) {
        if (DawnClient.getInstance().config.Enable12Hours) {
            hours12.setLabel(Text.translatable("dawnhud.config.advanced.12hour").append(": ").append(Text.translatable("dawnhud.off").formatted(Formatting.RED)));
            hours24.setLabel(Text.translatable("dawnhud.config.advanced.24hour").append(": ").append(Text.translatable("dawnhud.on").formatted(Formatting.GREEN)));
        }
        else {
            hours12.setLabel(Text.translatable("dawnhud.config.advanced.12hour").append(": ").append(Text.translatable("dawnhud.on").formatted(Formatting.GREEN)));
            hours24.setLabel(Text.translatable("dawnhud.config.advanced.24hour").append(": ").append(Text.translatable("dawnhud.off").formatted(Formatting.RED)));
        }
    }
}
