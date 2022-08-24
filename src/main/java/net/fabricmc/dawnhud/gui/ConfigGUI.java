package net.fabricmc.dawnhud.gui;

import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Insets;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.dawnhud.DawnClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

@Environment(EnvType.CLIENT)
public class ConfigGUI extends LightweightGuiDescription  {
    /* Root Panel */
    Text basicTabText = new TranslatableText("gui.dawnhud.basic_options");
    Text advancedTabText = new TranslatableText("gui.dawnhud.advanced_options");

    /* Panel A */
    static final Text clientPlayerHeader = new TranslatableText("gui.dawnhud.config.basic.category.client_player");
    static final Text placeHolderHeader = new TranslatableText("gui.dawnhud.config.place_holder");

    static final Text fpsToggleButtonText = new TranslatableText("dawnhud.config.basic.enable_fps");
    static final Text coordsToggleButtonText = new TranslatableText("dawnhud.config.basic.enable_coords");
    static final Text worldTimeToggleButtonText = new TranslatableText("dawnhud.config.basic.enable_time");
    static final Text clientBiomeToggleButtonText = new TranslatableText("dawnhud.config.basic.enable_biomes");

    /* Panel B */
    static Text positionHeader = new TranslatableText("gui.dawnhud.config.category.advanced.position");
    static Text colorHeader = new TranslatableText("gui.dawnhud.config.category.advanced.color");
    static final Text timeHeader = new TranslatableText("gui.dawnhud.config.category.advanced.time");

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
            /* headers */
            WText clientPlayerHeaderText = new WText(clientPlayerHeader);
            WText placeHolderHeaderText = new WText(placeHolderHeader);

            /* toggle buttons */
            WToggleButton fpsToggleButton = new WToggleButton(fpsToggleButtonText);
            WToggleButton coordsToggleButton = new WToggleButton(coordsToggleButtonText);
            WToggleButton worldTimeToggleButton = new WToggleButton(worldTimeToggleButtonText);
            WToggleButton biomeToggleButton = new WToggleButton(clientBiomeToggleButtonText);

        /* Modules */
        setFpsToggleButton(fpsToggleButton);
        setCoordsToggleButton(coordsToggleButton);
        setWorldTimeToggleButton(worldTimeToggleButton);
        setBiomeToggleButton(biomeToggleButton);

        /* Adding them to panel render */
            /* Panel Add */
            panelA.add(clientPlayerHeaderText, panelA.getWidth() / 4 + 6, 6, panelA.getWidth() / 2, 20);
            panelA.add(placeHolderHeaderText, panelA.getWidth() / 4 + 6, 88, panelA.getWidth() / 2, 20);

            panelA.add(fpsToggleButton, 6, 16, panelA.getWidth() - 12, 20);
            panelA.add(coordsToggleButton, 6, 34, panelA.getWidth() - 12, 20);
            panelA.add(worldTimeToggleButton, 6, 52, panelA.getWidth() - 12, 20);
            panelA.add(biomeToggleButton, 6, 70, panelA.getWidth() - 12, 20);

            /* Alignment */
            clientPlayerHeaderText.setHorizontalAlignment(HorizontalAlignment.CENTER);
            placeHolderHeaderText.setHorizontalAlignment(HorizontalAlignment.CENTER);
    }

    private static void setPanelB(WTabPanel tabs, WPlainPanel panelB) {
        /* Panel Settings */
        panelB.setSize(tabs.getWidth(), tabs.getHeight());
        panelB.setInsets(Insets.ROOT_PANEL);

        /* Module Definition */
            /* headers */
            WText positionHeaderText = new WText(positionHeader);
            WText colorHeaderText = new WText(colorHeader);
            WText timeHeaderText = new WText(timeHeader);

            /* buttons */
            WButton editPosition = new WButton(new TranslatableText("dawnhud.config.advanced.edit_position"));
            WButton editColors = new WButton(new TranslatableText("dawnhud.config.advanced.edit_colors"));
            WButton hours12 = new WButton(new TranslatableText("dawnhud.config.advanced.12hour"));
            WButton hours24 = new WButton(new TranslatableText("dawnhud.config.advanced.24hour"));

        /* Modules */
        setClockPeriod(hours12, hours24);
        //createPositionScreen(editPosition);

        editColors.setOnClick(() -> {
            MinecraftClient.getInstance().setScreen(null);
            MinecraftClient.getInstance().setScreen(new CottonClientScreen(new ColorEditor()));
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
    private static void setClockPeriod(WButton hours12, WButton hours24) {
        /* Reads and sets INITIAL value upon opening settings gui */
        if (DawnClient.getInstance().config.Enable12Hours) {
            hours12.setLabel(new TranslatableText("dawnhud.config.advanced.12hour").append(": ").append(new TranslatableText("dawnhud.on").formatted(Formatting.GREEN)));
            hours24.setLabel(new TranslatableText("dawnhud.config.advanced.24hour").append(": ").append(new TranslatableText("dawnhud.off").formatted(Formatting.RED)));
        }
        else {
            hours12.setLabel(new TranslatableText("dawnhud.config.advanced.12hour").append(": ").append(new TranslatableText("dawnhud.off").formatted(Formatting.RED)));
            hours24.setLabel(new TranslatableText("dawnhud.config.advanced.24hour").append(": ").append(new TranslatableText("dawnhud.on").formatted(Formatting.GREEN)));
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

    /**
    * Allows the Overriding of the GuiDescription.addPainters method which adds a default background to any null backgroundPainter calls.
    * To quote Juuz, "I need to make it not automatically apply to tab panels" bruh
    */
    @Override
    public void addPainters() {}
}