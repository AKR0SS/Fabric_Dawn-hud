package net.fabricmc.dawnhud.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import io.github.cottonmc.cotton.gui.widget.data.Insets;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.dawnhud.DawnClient;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ConfigGUI extends LightweightGuiDescription  {
    public ConfigGUI() {
        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(100, 50);
        root.setInsets(Insets.ROOT_PANEL);
        root.setBackgroundPainter(BackgroundPainter.VANILLA);
        root.setHost(this);

        WToggleButton fpsToggleButton = new WToggleButton(new TranslatableText("dawnhud.config.enable_fps"));
        WToggleButton coordsToggleButton = new WToggleButton(new TranslatableText("dawnhud.config.enable_coords"));

        if (DawnClient.getInstance().config.EnableFPS)
            fpsToggleButton.setToggle(true);
        else
            fpsToggleButton.setToggle(false);

        fpsToggleButton.setOnToggle(on -> {
            if (on) {
                DawnClient.getInstance().config.EnableFPS = true;
                DawnClient.getInstance().saveConfig();
            }
            else {
                DawnClient.getInstance().config.EnableFPS = false;
                DawnClient.getInstance().saveConfig();
            }
        });

        if (DawnClient.getInstance().config.EnableCoords)
            coordsToggleButton.setToggle(true);
        else
            coordsToggleButton.setToggle(false);

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

        root.add(fpsToggleButton, 20, 5, 200, 20);
        root.add(coordsToggleButton, 20, 25, 200, 20);

        root.validate(this);
    }
}