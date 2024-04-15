package com.ssblur.minecraftyellow.fabric;

import com.ssblur.minecraftyellow.MinecraftYellowExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class MinecraftYellowExpectPlatformImpl {
  /**
   * This is our actual method to {@link MinecraftYellowExpectPlatform#getConfigDirectory()}.
   */
  public static Path getConfigDirectory() {
    return FabricLoader.getInstance().getConfigDir();
  }
}
