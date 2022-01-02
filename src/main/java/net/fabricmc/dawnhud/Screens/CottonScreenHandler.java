package net.fabricmc.dawnhud.Screens;

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

    @Override
    public void onClose() {
        if (this.client != null)
            this.client.setScreen(parent);
    }
}