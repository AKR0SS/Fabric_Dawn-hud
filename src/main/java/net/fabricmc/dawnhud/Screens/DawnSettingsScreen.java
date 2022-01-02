package net.fabricmc.dawnhud.Screens;

import io.github.cottonmc.cotton.gui.GuiDescription;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class DawnSettingsScreen extends CottonScreenHandler {
    public DawnSettingsScreen(GuiDescription description, Screen parent) {
        super(description, parent);
    }
}
