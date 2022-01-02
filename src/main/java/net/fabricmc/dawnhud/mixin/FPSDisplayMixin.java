package net.fabricmc.dawnhud.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftClient.class)
public interface FPSDisplayMixin {
    @Accessor("currentFps")
    int getCurrentFPS();
}