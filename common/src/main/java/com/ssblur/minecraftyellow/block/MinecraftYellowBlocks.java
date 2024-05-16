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

  public static final RegistrySupplier<Block> SALOON_DOOR = BLOCKS.register("saloon_door", () ->
    new SaloonDoor(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR)));

  public static void register() {
    BLOCKS.register();
  }
}
