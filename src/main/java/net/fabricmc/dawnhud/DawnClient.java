package net.fabricmc.dawnhud;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.api.SyntaxError;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
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

	public static DawnClient getInstance() {
		return instance;
	}

	@Override
	public void onInitializeClient() {
		LogManager.getLogger().info("Successful Initialization of Client");

		instance = this;
		jankson = new Jankson.Builder().build();
		File configDir = FabricLoader.getInstance().getConfigDir().toFile();
		configFile = new File(configDir, "DawnSettings.json5");
		if (configFile.exists()) {
			try {
				config = jankson.fromJson(jankson.load(configFile), ConfigSettings.class);
			} catch (IOException | SyntaxError e) {
				LogManager.getLogger().info("Failed to load config so using default config");
				config = generateDefaultConfig();
			}
		} else {
			config = generateDefaultConfig();
			saveConfig();
		}
	}

	public void saveConfig() {
		try {
			LogManager.getLogger().info("Attempting to save config");
			FileWriter f = new FileWriter(configFile);
			f.write(jankson.toJson(config).toJson(true, true));
			f.close();
			LogManager.getLogger().info("Successfully saved to config");
		} catch (IOException e) {
			LogManager.getLogger().info("Failed to save config");
			e.printStackTrace();
		}
	}

	private ConfigSettings generateDefaultConfig() {
		LogManager.getLogger().info("Successfully loaded default config");
		ConfigSettings config = new ConfigSettings();
		config.EnableFPS = true;
		config.EnableCoords = true;
		return config;
	}
}