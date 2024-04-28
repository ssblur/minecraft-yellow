package com.ssblur.minecraftyellow.event;

import dev.architectury.event.events.client.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;

import java.util.ArrayList;
import java.util.List;

public class ParticleQueueWorldTick implements ClientTickEvent.ClientLevel {
  record ParticleQueue(ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i){}


  List<ParticleQueue> queue = new ArrayList<>();

  public ParticleQueueWorldTick() {}

  public void queue(ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
    queue.add(new ParticleQueue(particleOptions, d, e, f, g, h, i));
  }

  public void queue(ParticleOptions particleOptions, double d, double e, double f) {
    queue(particleOptions, d, e, f, 0, 0, 0);
  }

  @Override
  public void tick(net.minecraft.client.multiplayer.ClientLevel instance) {
    var level = Minecraft.getInstance().level;
    while(level != null && !queue.isEmpty()) {
      var item = queue.remove(0);
      level.addParticle(item.particleOptions, item.d, item.e, item.f, item.g, item.h, item.i);
    }

  }
}
