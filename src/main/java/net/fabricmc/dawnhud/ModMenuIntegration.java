package net.fabricmc.dawnhud;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.dawnhud.Screens.DawnSettingsScreen;
import net.fabricmc.dawnhud.gui.ConfigGUI;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new DawnSettingsScreen(new ConfigGUI(), parent);
    }
}
