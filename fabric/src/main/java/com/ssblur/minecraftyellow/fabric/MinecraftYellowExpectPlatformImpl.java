package com.ssblur.minecraftyellow.fabric;

import com.ssblur.minecraftyellow.MinecraftYellowExpectPlatform;
import com.ssblur.minecraftyellow.fabric.item.HatWithTheBrim;
import com.ssblur.minecraftyellow.fabric.item.TheDimmadome;
import com.ssblur.minecraftyellow.item.MinecraftYellowItems;
import com.ssblur.minecraftyellow.item.armor.YellowArmorMaterials;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

import java.nio.file.Path;

public class MinecraftYellowExpectPlatformImpl {
  /**
   * This is our actual method to {@link MinecraftYellowExpectPlatform#getConfigDirectory()}.
   */
  public static Path getConfigDirectory() {
    return FabricLoader.getInstance().getConfigDir();
  }

  public static void registerItems() {
    MinecraftYellowItems.COWBOY_HAT = MinecraftYellowItems.ITEMS.register("cowboy_hat", () ->
      new HatWithTheBrim(YellowArmorMaterials.COWBOY, ArmorItem.Type.HELMET, MinecraftYellowItems.TAB_PROPERTIES));
    MinecraftYellowItems.THE_DIMMADOME = MinecraftYellowItems.ITEMS.register("the_dimmadome", () ->
      new TheDimmadome(YellowArmorMaterials.THE_DIMMADOME, ArmorItem.Type.HELMET, MinecraftYellowItems.TAB_PROPERTIES));
  }
}
