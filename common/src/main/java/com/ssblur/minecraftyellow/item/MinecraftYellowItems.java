package com.ssblur.minecraftyellow.item;

import com.ssblur.minecraftyellow.MinecraftYellow;
import com.ssblur.minecraftyellow.item.gun.BaseGun;
import com.ssblur.minecraftyellow.sound.MinecraftYellowSounds;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MinecraftYellowItems {
  public static final String MOD_ID = MinecraftYellow.MOD_ID;
  public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);
  public static final RegistrySupplier<CreativeModeTab> TAB = TABS.register("items", () ->
    CreativeTabRegistry.create(Component.translatable("itemGroup." + MOD_ID + ".items"),
      () -> new ItemStack(MinecraftYellowItems.TOY_GUN.get())));

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
  public static final RegistrySupplier<Item> TOY_GUN = ITEMS.register("toy_gun", () ->
    new BaseGun(new Item.Properties().arch$tab(MinecraftYellowItems.TAB), 1, 6)
      .withSounds(MinecraftYellowSounds.GUN_CAP_POP.get()));
  public static final RegistrySupplier<Item> WILD_REVOLVER = ITEMS.register("wild_revolver", () ->
    new BaseGun(new Item.Properties().arch$tab(MinecraftYellowItems.TAB), 8, 6)
      .withSounds(MinecraftYellowSounds.GUN_BLAST.get())
      .withAmmo(Items.ARROW));
  public static final RegistrySupplier<Item> GOLDEN_GUN = ITEMS.register("golden_gun", () ->
    new BaseGun(new Item.Properties().arch$tab(MinecraftYellowItems.TAB), 20, 6)
      .withSounds(MinecraftYellowSounds.GUN_BLAST.get())
      .withAmmo(Items.ARROW));

  public static void register() {
    ITEMS.register();
    TABS.register();
  }
}
