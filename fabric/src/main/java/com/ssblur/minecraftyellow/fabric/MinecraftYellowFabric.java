package com.ssblur.minecraftyellow.fabric;

import com.ssblur.minecraftyellow.MinecraftYellow;
import net.fabricmc.api.ModInitializer;

public class MinecraftYellowFabric implements ModInitializer {
  @Override
  public void onInitialize() {
    MinecraftYellow.init();
  }
}
