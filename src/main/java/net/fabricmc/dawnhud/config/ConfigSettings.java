package net.fabricmc.dawnhud.config;

import com.google.gson.annotations.SerializedName;

public class ConfigSettings {
    @SerializedName("enable_fps")
    public Boolean EnableFPS;

    @SerializedName("enable_coords")
    public Boolean EnableCoords;
}
