package com.ssblur.minecraftyellow.forge;

import dev.architectury.platform.forge.EventBuses;
import com.ssblur.minecraftyellow.MinecraftYellow;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MinecraftYellow.MOD_ID)
public class MinecraftYellowForge {
  public MinecraftYellowForge() {
    // Submit our event bus to let architectury register our content on the right time
    EventBuses.registerModEventBus(MinecraftYellow.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
    MinecraftYellow.init();
  }
}
