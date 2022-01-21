package net.fabricmc.dawnhud.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Insets;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.dawnhud.DawnClient;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import javax.swing.*;

@Environment(EnvType.CLIENT)
public class ConfigGUI extends LightweightGuiDescription  {
    /* Root Panel */
    Text basicTabText = new TranslatableText("gui.dawnhud.basic_options");
    Text advancedTabText = new TranslatableText("gui.dawnhud.advanced_options");

    /* Panel A */
    static final Text fpsToggleButtonText = new TranslatableText("dawnhud.config.basic.enable_fps");
    static final Text coordsToggleButtonText = new TranslatableText("dawnhud.config.basic.enable_coords");
    static final Text worldTimeToggleButtonText = new TranslatableText("dawnhud.config.basic.enable_time");

    /* Panel B */
    static Text positionHeaderText = new TranslatableText("gui.dawn.config.category.position");
    static Text colorHeaderText = new TranslatableText("gui.dawn.config.category.color");
    static final Text timeHeaderText = new TranslatableText("gui.dawn.config.category.time");

    // static final Text hours12Text = new TranslatableText("dawnhud.config.advanced.12hour");
    // static final Text hours24Text = new TranslatableText("dawnhud.config.advanced.24hour");

    public ConfigGUI() {
        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(300, 200);
        root.setInsets(Insets.ROOT_PANEL);
        root.setHost(this);
        addPainters();

        WTabPanel tabs = new WTabPanel();
        root.add(tabs, 0, 0, root.getWidth(), root.getHeight() - 20);

        WPlainPanel panelA = new WPlainPanel();
        WPlainPanel panelB = new WPlainPanel();

        tabs.add(panelA, tab -> tab.title(basicTabText));
        tabs.add(panelB, tab -> tab.title(advancedTabText));

        setPanelA(tabs, panelA);
        setPanelB(tabs, panelB);

        root.validate(this);
    }

    private static void setPanelA(WTabPanel tabs, WPlainPanel panelA) {
        /* Panel Settings */
        panelA.setSize(tabs.getWidth(), tabs.getHeight());
        panelA.setInsets(Insets.ROOT_PANEL);

        /* Module Definition */
        WToggleButton fpsToggleButton = new WToggleButton(fpsToggleButtonText);
        WToggleButton coordsToggleButton = new WToggleButton(coordsToggleButtonText);
        WToggleButton worldTimeToggleButton = new WToggleButton(worldTimeToggleButtonText);

        /* Modules */
        setFpsToggleButton(fpsToggleButton);
        setCoordsToggleButton(coordsToggleButton);
        setWorldTimeToggleButton(worldTimeToggleButton);

        /* Adding them to panel render */
        panelA.add(fpsToggleButton, 6, 6, panelA.getWidth() - 16, 20);
        panelA.add(coordsToggleButton, 6, 24, panelA.getWidth() - 16, 20);
        panelA.add(worldTimeToggleButton, 6, 42, panelA.getWidth() - 16, 20);
    }

    private static void setPanelB(WTabPanel tabs, WPlainPanel panelB) {
        /* Panel Settings */
        panelB.setSize(tabs.getWidth(), tabs.getHeight());
        panelB.setInsets(Insets.ROOT_PANEL);

        /* Module Definition */
        WText positionHeader = new WText(positionHeaderText);
        WText colorHeader = new WText(colorHeaderText);
        WText timeHeader = new WText(timeHeaderText);

        WButton editPosition = new WButton(new TranslatableText("dawnhud.config.advanced.edit_position"));
        WButton editColors = new WButton(new TranslatableText("dawnhud.config.advanced.edit_colors"));
        WButton hours12 = new WButton(new TranslatableText("dawnhud.config.advanced.12hour").append(": ").append(new TranslatableText("dawnhud.on").formatted(Formatting.GREEN)));
        WButton hours24 = new WButton(new TranslatableText("dawnhud.config.advanced.24hour").append(": ").append(new TranslatableText("dawnhud.off").formatted(Formatting.RED)));

        /* Modules */
        setClockPeriod(hours12, hours24);

        /* Adding Modules to Panel Render */
        panelB.add(positionHeader, panelB.getWidth() / 4, 6, panelB.getWidth() / 2, 20);
        panelB.add(colorHeader, panelB.getWidth() / 4, 46, panelB.getWidth() / 2, 20);
        panelB.add(timeHeader, panelB.getWidth() / 4, 86, panelB.getWidth() / 2, 20);

        panelB.add(editPosition, panelB.getWidth() / 4, 20, panelB.getWidth() / 2, 20);
        panelB.add(editColors, panelB.getWidth() / 4, 60, panelB.getWidth() / 2, 20);
        panelB.add(hours12, 6, 100, panelB.getWidth() / 2 - 6, 20);
        panelB.add(hours24, panelB.getWidth() / 2 + 6, 100, panelB.getWidth() / 2 - 12, 20);

        editPosition.setAlignment(HorizontalAlignment.CENTER);
        editColors.setAlignment(HorizontalAlignment.CENTER);

        positionHeader.setHorizontalAlignment(HorizontalAlignment.CENTER);
        colorHeader.setHorizontalAlignment(HorizontalAlignment.CENTER);
        timeHeader.setHorizontalAlignment(HorizontalAlignment.CENTER);
    }

    private static void displayClockPeriod(WButton hours12, WButton hours24) {
        if (DawnClient.getInstance().config.Enable12Hours) {
            hours12.setLabel(new TranslatableText("dawnhud.config.advanced.12hour").append(": ").append(new TranslatableText("dawnhud.off").formatted(Formatting.RED)));
            hours24.setLabel(new TranslatableText("dawnhud.config.advanced.24hour").append(": ").append(new TranslatableText("dawnhud.on").formatted(Formatting.GREEN)));
        }
        else {
            hours12.setLabel(new TranslatableText("dawnhud.config.advanced.12hour").append(": ").append(new TranslatableText("dawnhud.on").formatted(Formatting.GREEN)));
            hours24.setLabel(new TranslatableText("dawnhud.config.advanced.24hour").append(": ").append(new TranslatableText("dawnhud.off").formatted(Formatting.RED)));
        }
    }

    /* saves to config */
    private static void setFpsToggleButton(WToggleButton fpsToggleButton) {
        fpsToggleButton.setToggle(DawnClient.getInstance().config.EnableFPS);

        fpsToggleButton.setOnToggle(on -> {
            if (on) {
                DawnClient.getInstance().config.EnableFPS = true;
                DawnClient.getInstance().saveConfig();
            } else {
                DawnClient.getInstance().config.EnableFPS = false;
                DawnClient.getInstance().saveConfig();
            }
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
            if (on) {
                DawnClient.getInstance().config.EnableTime = true;
                DawnClient.getInstance().saveConfig();
            }
            else {
                DawnClient.getInstance().config.EnableTime = false;
                DawnClient.getInstance().saveConfig();
            }
        });
    }
    private static void setClockPeriod(WButton hours12, WButton hours24) {
        hours12.setOnClick(() -> {
            if (DawnClient.getInstance().config.Enable12Hours) {
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


    /**
    * Allows the Overriding of the GuiDescription.addPainters method which adds a default background to any null backgroundPainter calls.
    * To quote Juuz, "I need to make it not automatically apply to tab panels" bruh
    */
    @Override
    public void addPainters() {}
}