package net.fabricmc.dawnhud.config;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindingProvider {
    KeyBinding screenKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.dawnhud.open.settings",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            "key.dawnhud.category"
    ));

    KeyBinding displayFPSKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.dawnhud.display.fps",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.dawnhud.category"
    ));

    KeyBinding displayCoordsKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.dawnhud.display.coords",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.dawnhud.category"
    ));

    public KeyBinding getScreenKeyBinding() {
        return screenKeyBinding;
    }

    public KeyBinding getDisplayCoordsKeyBinding() {
        return displayCoordsKeyBinding;
    }

    public KeyBinding getDisplayFPSKeyBinding() {
        return displayFPSKeyBinding;
    }
}
