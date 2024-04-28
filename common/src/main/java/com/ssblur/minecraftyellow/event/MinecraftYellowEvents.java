package com.ssblur.minecraftyellow.event;

import com.ssblur.minecraftyellow.event.network.MinecraftYellowNetwork;
import dev.architectury.event.events.client.ClientTickEvent;

public class MinecraftYellowEvents {
  public static ParticleQueueWorldTick particleQueueWorldTick = new ParticleQueueWorldTick();

  public static void register() {
    MinecraftYellowNetwork.register();

    ClientTickEvent.CLIENT_LEVEL_PRE.register(particleQueueWorldTick);
  }
}
