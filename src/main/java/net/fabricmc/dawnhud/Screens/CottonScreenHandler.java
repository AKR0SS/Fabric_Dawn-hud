package net.fabricmc.dawnhud.screens;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class CottonScreenHandler extends CottonClientScreen {
    private Screen parent;

    public CottonScreenHandler(GuiDescription description, Screen parent) {
        super(description);
        this.parent = parent;
    }

    public CottonScreenHandler(GuiDescription description) {
        super(description);
    }
}