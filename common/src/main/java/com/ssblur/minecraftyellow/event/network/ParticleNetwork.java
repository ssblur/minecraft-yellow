package com.ssblur.minecraftyellow.event.network;

import com.ssblur.minecraftyellow.event.MinecraftYellowEvents;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ParticleNetwork {
  static final Random RANDOM = new Random();

  public static void receiveSmokeParticles(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
    double x = buf.readDouble();
    double y = buf.readDouble();
    double z = buf.readDouble();

    for(int i = 0; i < 3; i++)
      MinecraftYellowEvents.particleQueueWorldTick.queue(
        ParticleTypes.SMOKE,
        x + RANDOM.nextDouble(0.05d),
        y + RANDOM.nextDouble(0.05d),
        z + RANDOM.nextDouble(0.05d)
      );
  }

  public static void sendSmokeParticles(ServerLevel level, Vec3 pos) {
    FriendlyByteBuf out = new FriendlyByteBuf(Unpooled.buffer());
    out.writeDouble(pos.x);
    out.writeDouble(pos.y);
    out.writeDouble(pos.z);
    NetworkManager.sendToPlayers(level.players(), MinecraftYellowNetwork.CLIENT_RECEIVE_SMOKE_PARTICLES, out);
  }
}
