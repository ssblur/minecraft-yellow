package com.ssblur.minecraftyellow.blockentity;

import com.ssblur.minecraftyellow.MinecraftYellow;
import com.ssblur.minecraftyellow.block.MinecraftYellowBlocks;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MinecraftYellowBlockEntities {
  static String MOD_ID = MinecraftYellow.MOD_ID;
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(MOD_ID, Registries.BLOCK_ENTITY_TYPE);

  public static final RegistrySupplier<BlockEntityType<SaloonDoorBlockEntity>> SALOON_DOOR = BLOCK_ENTITIES.register(
    "saloon_door",
    () -> BlockEntityType.Builder.of(SaloonDoorBlockEntity::new, MinecraftYellowBlocks.SALOON_DOOR.get()).build(null)
  );

  public static void register() {
    BLOCK_ENTITIES.register();
  }
}
