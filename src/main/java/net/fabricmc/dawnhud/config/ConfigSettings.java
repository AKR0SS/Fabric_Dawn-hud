package net.fabricmc.dawnhud.config;

import com.google.gson.annotations.SerializedName;

public class ConfigSettings {
    @SerializedName("enable_fps")
    public Boolean EnableFPS;

    @SerializedName("enable_coords")
    public Boolean EnableCoords;

    @SerializedName("enable_time")
    public boolean EnableTime;

    @SerializedName("enable_hour_format")
    public boolean Enable12Hours;
    public boolean Enable24Hours;

    @SerializedName("enable_biome")
    public boolean EnableBiome;
}
