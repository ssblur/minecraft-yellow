package com.ssblur.minecraftyellow.event;

import com.ssblur.minecraftyellow.event.network.MinecraftYellowNetwork;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.platform.Platform;
import dev.architectury.registry.ReloadListenerRegistry;
import net.fabricmc.api.EnvType;
import net.minecraft.server.packs.PackType;

public class MinecraftYellowEvents {
  public static ParticleQueueWorldTick particleQueueWorldTick = new ParticleQueueWorldTick();

  public static void register() {
    MinecraftYellowNetwork.register();

    EntityEvent.LIVING_CHECK_SPAWN.register(new EntitySpawnEvent());

    if(Platform.getEnv() == EnvType.CLIENT) {
      ClientTickEvent.CLIENT_LEVEL_PRE.register(particleQueueWorldTick);
    }
  }
}
