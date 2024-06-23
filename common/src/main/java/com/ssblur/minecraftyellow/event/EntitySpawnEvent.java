package com.ssblur.minecraftyellow.event;

import com.ssblur.minecraftyellow.MinecraftYellowGameRules;
import com.ssblur.minecraftyellow.item.MinecraftYellowItems;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class EntitySpawnEvent implements EntityEvent.LivingCheckSpawn {
  Random random = new Random();
  @Override
  public EventResult canSpawn(LivingEntity entity, LevelAccessor accessor, double x, double y, double z, MobSpawnType type, @Nullable BaseSpawner spawner) {
    if(accessor instanceof Level world && world.getGameRules().getBoolean(MinecraftYellowGameRules.WILD_WASTELAND)) {
      if (entity instanceof Cat cat) {
        world.registryAccess().registry(Registries.CAT_VARIANT).ifPresent(registry -> {
          if (registry.get(CatVariant.CALICO) == cat.getVariant() && random.nextFloat() < 0.05)
            cat.setCustomName(Component.literal("Peeper"));
        });
      }

      if (entity instanceof Zombie zombie && random.nextFloat() < 0.01) {
        zombie.setCustomName(Component.literal("Doug"));
        zombie.setItemSlot(EquipmentSlot.HEAD, new ItemStack(MinecraftYellowItems.THE_DIMMADOME.get()));
      }
    }

    return EventResult.pass();
  }
}
