package net.fabricmc.dawnhud.config;

import com.google.gson.annotations.SerializedName;

public class ConfigSettings {
    @SerializedName("enable_overlay")
    public Boolean EnableOverlay;

    @SerializedName("enable_fps")
    public Boolean EnableFPS;

    @SerializedName("enable_coords")
    public Boolean EnableCoords;

    @SerializedName("enable_time")
    public Boolean EnableTime;

    @SerializedName("enable_hour_format")
    public Boolean Enable12Hours;
    public Boolean Enable24Hours;

    @SerializedName("enable_biome")
    public Boolean EnableBiome;
}
