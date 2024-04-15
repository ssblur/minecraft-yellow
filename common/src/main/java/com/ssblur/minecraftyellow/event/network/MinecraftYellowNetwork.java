package com.ssblur.minecraftyellow.event.network;

import com.ssblur.minecraftyellow.MinecraftYellow;
import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import net.minecraft.resources.ResourceLocation;

public class MinecraftYellowNetwork {
  public static final String MOD_ID = MinecraftYellow.MOD_ID;

  public static final ResourceLocation CLIENT_GET_TRACE_DATA = new ResourceLocation(MOD_ID, "client_get_touch_data");
  public static final ResourceLocation SERVER_RETURN_TRACE_DATA = new ResourceLocation(MOD_ID, "server_return_touch_data");

  public static void register() {
    NetworkManager.registerReceiver(NetworkManager.Side.C2S, SERVER_RETURN_TRACE_DATA, TraceNetwork::returnTraceData);

    if(Platform.getEnv() == EnvType.CLIENT) {
      NetworkManager.registerReceiver(NetworkManager.Side.S2C, CLIENT_GET_TRACE_DATA, TraceNetwork::getTraceData);
    }
  }
}
