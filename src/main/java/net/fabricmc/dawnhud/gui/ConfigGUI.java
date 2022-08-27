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
    Text basicTabText = Text.translatable("gui.dawnhud.basic_options");
    Text advancedTabText = Text.translatable("gui.dawnhud.advanced_options");

    /* Panel A */
    static final Text toggleModButtonText = Text.literal("HUD Overlay: ");

    static final Text clientPlayerHeader = Text.translatable("gui.dawnhud.config.basic.category.client_player");
    static final Text placeHolderHeader = Text.translatable("gui.dawnhud.config.place_holder");

    static final Text fpsToggleButtonText = Text.translatable("dawnhud.config.basic.enable_fps");
    static final Text coordsToggleButtonText = Text.translatable("dawnhud.config.basic.enable_coords");
    static final Text worldTimeToggleButtonText = Text.translatable("dawnhud.config.basic.enable_time");
    static final Text clientBiomeToggleButtonText = Text.translatable("dawnhud.config.basic.enable_biomes");

    /* Panel B */
    static Text positionHeader = Text.translatable("gui.dawnhud.config.category.advanced.position");
    static Text colorHeader = Text.translatable("gui.dawnhud.config.category.advanced.color");
    static final Text timeHeader = Text.translatable("gui.dawnhud.config.category.advanced.time");

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

        PanelA.setPanelA(tabs, panelA);
        PanelB.setPanelB(tabs, panelB);

        root.validate(this);
    }

    /**
    * Allows the Overriding of the GuiDescription.addPainters method which adds a default background to any null backgroundPainter calls.
    * To quote Juuz, "I need to make it not automatically apply to tab panels" bruh
    */
    @Override
    public void addPainters() {}
}