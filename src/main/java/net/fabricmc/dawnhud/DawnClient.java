package net.fabricmc.dawnhud;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.api.SyntaxError;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.dawnhud.screens.CottonScreenHandler;
import net.fabricmc.dawnhud.config.KeyBindingProvider;
import net.fabricmc.dawnhud.gui.ConfigGUI;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;

import net.fabricmc.dawnhud.config.ConfigSettings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Environment(EnvType.CLIENT)
public class DawnClient implements ClientModInitializer {
	public static DawnClient instance;

	public Jankson jankson;

	public ConfigSettings config;
	private File configFile;

	KeyBindingProvider keyBindingProvider = new KeyBindingProvider();

	public static DawnClient getInstance() {
		return instance;
	}

	@Override
	public void onInitializeClient() {
		LogManager.getLogger().info("Successful Initialization of Client");

		ClientTickEvents.END_CLIENT_TICK.register(client -> keyBindings());

		instance = this;
		jankson = new Jankson.Builder().build();
		File configDir = FabricLoader.getInstance().getConfigDir().toFile();
		configFile = new File(configDir, "DawnSettings.json5");
		if (configFile.exists()) {
			try {
				config = jankson.fromJson(jankson.load(configFile), ConfigSettings.class);
			} catch (IOException | SyntaxError e) {
				config = generateDefaultConfig();
			}
		} else {
			config = generateDefaultConfig();
			saveConfig();
		}
	}

	public void saveConfig() {
		try {
			FileWriter f = new FileWriter(configFile);
			f.write(jankson.toJson(config).toJson(true, true));
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ConfigSettings generateDefaultConfig() {
		ConfigSettings config = new ConfigSettings();
		config.EnableFPS = true;
		config.EnableCoords = true;
		return config;
	}

	private void keyBindings() {
		while (keyBindingProvider.getScreenKeyBinding().wasPressed()) {
			MinecraftClient.getInstance().setScreen(new CottonScreenHandler(new ConfigGUI()));
		}
	}
}

