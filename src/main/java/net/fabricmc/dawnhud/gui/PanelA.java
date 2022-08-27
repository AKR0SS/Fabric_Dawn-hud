package net.fabricmc.dawnhud.gui;

import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.fabricmc.dawnhud.DawnClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class PanelA extends ConfigGUI {
    static void setPanelA(WTabPanel tabs, WPlainPanel panelA) {
        /* Panel Settings */
        panelA.setSize(tabs.getWidth(), tabs.getHeight());
        panelA.setInsets(Insets.ROOT_PANEL);

        /* Module Definition */
        WButton toggleModButton = new WButton(toggleModButtonText);

        /* headers */
        WText clientPlayerHeaderText = new WText(clientPlayerHeader);
        WText placeHolderHeaderText = new WText(placeHolderHeader);

        /* toggle buttons */
        WToggleButton fpsToggleButton = new WToggleButton(fpsToggleButtonText);
        WToggleButton coordsToggleButton = new WToggleButton(coordsToggleButtonText);
        WToggleButton worldTimeToggleButton = new WToggleButton(worldTimeToggleButtonText);
        WToggleButton biomeToggleButton = new WToggleButton(clientBiomeToggleButtonText);

        /* Modules */
        setOverlayDisplay(toggleModButton);
        setFpsToggleButton(fpsToggleButton);
        setCoordsToggleButton(coordsToggleButton);
        setWorldTimeToggleButton(worldTimeToggleButton);
        setBiomeToggleButton(biomeToggleButton);

        /* Adding them to panel render */
            /* Panel Add */
            panelA.add(toggleModButton, panelA.getWidth() / 4 + 6, 6, panelA.getWidth() / 2, 20);

            panelA.add(clientPlayerHeaderText, panelA.getWidth() / 4 + 6, 32, panelA.getWidth() / 2, 20);
            panelA.add(placeHolderHeaderText, panelA.getWidth() / 4 + 6, 116, panelA.getWidth() / 2, 20);

            panelA.add(fpsToggleButton, 6, 44, panelA.getWidth() - 12, 20);
            panelA.add(coordsToggleButton, 6, 62, panelA.getWidth() - 12, 20);
            panelA.add(worldTimeToggleButton, 6, 80, panelA.getWidth() - 12, 20);
            panelA.add(biomeToggleButton, 6, 98, panelA.getWidth() - 12, 20);

            /* Alignment */
            clientPlayerHeaderText.setHorizontalAlignment(HorizontalAlignment.CENTER);
            placeHolderHeaderText.setHorizontalAlignment(HorizontalAlignment.CENTER);
    }

    /* saves to config */
    private static void setOverlayDisplay(WButton toggleModButton)  {
        if (DawnClient.getInstance().config.EnableOverlay)
            toggleModButton.setLabel(Text.translatable("gui.dawnhud.config.enable_overlay").append(": ").append(Text.literal("Enabled").formatted(Formatting.GREEN)));
        else
            toggleModButton.setLabel(Text.translatable("gui.dawnhud.config.enable_overlay").append(": ").append(Text.literal("Disabled").formatted(Formatting.RED)));

        toggleModButton.setOnClick(() -> {
            if (DawnClient.getInstance().config.EnableOverlay) {
                toggleModButton.setLabel(Text.translatable("gui.dawnhud.config.enable_overlay").append(": ").append(Text.literal("Disabled").formatted(Formatting.RED)));
                DawnClient.getInstance().config.EnableOverlay = false;
            }
            else {
                toggleModButton.setLabel(Text.translatable("gui.dawnhud.config.enable_overlay").append(": ").append(Text.literal("Enabled").formatted(Formatting.GREEN)));
                DawnClient.getInstance().config.EnableOverlay = true;
            }
        });
    }
    private static void setFpsToggleButton(WToggleButton fpsToggleButton) {
        fpsToggleButton.setToggle(DawnClient.getInstance().config.EnableFPS);

        fpsToggleButton.setOnToggle(on -> {
            if (on)
                DawnClient.getInstance().config.EnableFPS = true;
            else
                DawnClient.getInstance().config.EnableFPS = false;

            DawnClient.getInstance().saveConfig();
        });
    }
    private static void setCoordsToggleButton(WToggleButton coordsToggleButton) {
        coordsToggleButton.setToggle(DawnClient.getInstance().config.EnableCoords);

        coordsToggleButton.setOnToggle(on -> {
            if (on) {
                DawnClient.getInstance().config.EnableCoords = true;
                DawnClient.getInstance().saveConfig();
            }
            else {
                DawnClient.getInstance().config.EnableCoords = false;
                DawnClient.getInstance().saveConfig();
            }
        });
    }
    private static void setWorldTimeToggleButton(WToggleButton worldTimeToggleButton) {
        worldTimeToggleButton.setToggle(DawnClient.getInstance().config.EnableTime);

        worldTimeToggleButton.setOnToggle(on -> {
            if (on)
                DawnClient.getInstance().config.EnableTime = true;
            else
                DawnClient.getInstance().config.EnableTime = false;

            DawnClient.getInstance().saveConfig();
        });
    }
    private static void setBiomeToggleButton(WToggleButton biomeToggleButton) {
        biomeToggleButton.setToggle(DawnClient.getInstance().config.EnableBiome);

        biomeToggleButton.setOnToggle(on -> {
            if (on)
                DawnClient.getInstance().config.EnableBiome = true;
            else
                DawnClient.getInstance().config.EnableBiome = false;

            DawnClient.getInstance().saveConfig();
        });
    }
}
