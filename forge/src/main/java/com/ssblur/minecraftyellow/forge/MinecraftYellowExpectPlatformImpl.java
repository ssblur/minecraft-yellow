package com.ssblur.minecraftyellow.forge;

import com.ssblur.minecraftyellow.MinecraftYellowExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class MinecraftYellowExpectPlatformImpl {
  /**
   * This is our actual method to {@link MinecraftYellowExpectPlatform#getConfigDirectory()}.
   */
  public static Path getConfigDirectory() {
    return FMLPaths.CONFIGDIR.get();
  }
}
