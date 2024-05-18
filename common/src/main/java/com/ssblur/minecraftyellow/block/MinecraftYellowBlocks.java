package com.ssblur.minecraftyellow.block;

import com.ssblur.minecraftyellow.MinecraftYellow;
import com.ssblur.minecraftyellow.item.MinecraftYellowItems;
import com.ssblur.minecraftyellow.item.armor.Bandana;
import com.ssblur.minecraftyellow.item.armor.YellowArmorMaterials;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class MinecraftYellowBlocks {
  public static final String MOD_ID = MinecraftYellow.MOD_ID;
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);

  public static final RegistrySupplier<Block> OAK_SALOON_DOOR = BLOCKS.register("oak_saloon_door", () ->
    new SaloonDoor(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
  public static final RegistrySupplier<Block> SPRUCE_SALOON_DOOR = BLOCKS.register("spruce_saloon_door", () ->
    new SaloonDoor(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
  public static final RegistrySupplier<Block> BIRCH_SALOON_DOOR = BLOCKS.register("birch_saloon_door", () ->
    new SaloonDoor(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
  public static final RegistrySupplier<Block> JUNGLE_SALOON_DOOR = BLOCKS.register("jungle_saloon_door", () ->
    new SaloonDoor(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
  public static final RegistrySupplier<Block> ACACIA_SALOON_DOOR = BLOCKS.register("acacia_saloon_door", () ->
    new SaloonDoor(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
  public static final RegistrySupplier<Block> DARK_OAK_SALOON_DOOR = BLOCKS.register("dark_oak_saloon_door", () ->
    new SaloonDoor(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
  public static final RegistrySupplier<Block> MANGROVE_SALOON_DOOR = BLOCKS.register("mangrove_saloon_door", () ->
    new SaloonDoor(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
  public static final RegistrySupplier<Block> CHERRY_SALOON_DOOR = BLOCKS.register("cherry_saloon_door", () ->
    new SaloonDoor(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
  public static final RegistrySupplier<Block> CRIMSON_SALOON_DOOR = BLOCKS.register("crimson_saloon_door", () ->
    new SaloonDoor(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));
  public static final RegistrySupplier<Block> WARPED_SALOON_DOOR = BLOCKS.register("warped_saloon_door", () ->
    new SaloonDoor(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));

  public static void register() {
    BLOCKS.register();
  }
}
