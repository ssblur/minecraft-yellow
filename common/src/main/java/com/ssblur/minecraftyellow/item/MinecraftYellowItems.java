package com.ssblur.minecraftyellow.item;

import com.ssblur.minecraftyellow.MinecraftYellow;
import com.ssblur.minecraftyellow.MinecraftYellowExpectPlatform;
import com.ssblur.minecraftyellow.block.MinecraftYellowBlocks;
import com.ssblur.minecraftyellow.item.armor.Bandana;
import com.ssblur.minecraftyellow.item.armor.HatWithTheBrim;
import com.ssblur.minecraftyellow.item.armor.YellowArmorColors;
import com.ssblur.minecraftyellow.item.armor.YellowArmorMaterials;
import com.ssblur.minecraftyellow.item.gun.BaseGun;
import com.ssblur.minecraftyellow.sound.MinecraftYellowSounds;
import dev.architectury.platform.Platform;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

@SuppressWarnings("ALL")
public class MinecraftYellowItems {
  public static final String MOD_ID = MinecraftYellow.MOD_ID;
  public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);
  public static final RegistrySupplier<CreativeModeTab> TAB = TABS.register("items", () ->
    CreativeTabRegistry.create(Component.translatable("itemGroup." + MOD_ID + ".items"),
      () -> new ItemStack(MinecraftYellowItems.COWBOY_HAT.get())));

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
  public static final Item.Properties TAB_PROPERTIES = new Item.Properties().arch$tab(MinecraftYellowItems.TAB);

  public static final RegistrySupplier<Item> TOY_GUN = ITEMS.register("toy_gun", () ->
    new BaseGun(new Item.Properties().arch$tab(MinecraftYellowItems.TAB), 1, 6)
      .withSounds(MinecraftYellowSounds.GUN_CAP_POP.get()));
  public static final RegistrySupplier<Item> BULLET = ITEMS.register("bullet", () ->
    new Item(new Item.Properties().arch$tab(MinecraftYellowItems.TAB)));
  public static final RegistrySupplier<Item> WILD_REVOLVER = ITEMS.register("wild_revolver", () ->
    new BaseGun(new Item.Properties().arch$tab(MinecraftYellowItems.TAB), 8, 6)
      .withSounds(MinecraftYellowSounds.GUN_BLAST.get())
      .withAmmo(BULLET));
  public static final RegistrySupplier<Item> GOLDEN_GUN = ITEMS.register("golden_gun", () ->
    new BaseGun(new Item.Properties().arch$tab(MinecraftYellowItems.TAB), 20, 6)
      .withSounds(MinecraftYellowSounds.GUN_BLAST.get())
      .withAmmo(BULLET));

  public static final RegistrySupplier<Item> BANDANA = ITEMS.register("bandana", () ->
    new Bandana(YellowArmorMaterials.CLOTH, ArmorItem.Type.CHESTPLATE, new Item.Properties().arch$tab(MinecraftYellowItems.TAB)));

  public static RegistrySupplier<HatWithTheBrim> COWBOY_HAT;
  public static RegistrySupplier<Item> THE_DIMMADOME;


  public static final RegistrySupplier<Item> OAK_SALOON_DOOR = ITEMS.register("oak_saloon_door", () ->
    new BlockItem(MinecraftYellowBlocks.OAK_SALOON_DOOR.get(), new Item.Properties().arch$tab(MinecraftYellowItems.TAB)));
  public static final RegistrySupplier<Item> SPRUCE_SALOON_DOOR = ITEMS.register("spruce_saloon_door", () ->
    new BlockItem(MinecraftYellowBlocks.SPRUCE_SALOON_DOOR.get(), new Item.Properties().arch$tab(MinecraftYellowItems.TAB)));
  public static final RegistrySupplier<Item> BIRCH_SALOON_DOOR = ITEMS.register("birch_saloon_door", () ->
    new BlockItem(MinecraftYellowBlocks.BIRCH_SALOON_DOOR.get(), new Item.Properties().arch$tab(MinecraftYellowItems.TAB)));
  public static final RegistrySupplier<Item> JUNGLE_SALOON_DOOR = ITEMS.register("jungle_saloon_door", () ->
    new BlockItem(MinecraftYellowBlocks.JUNGLE_SALOON_DOOR.get(), new Item.Properties().arch$tab(MinecraftYellowItems.TAB)));
  public static final RegistrySupplier<Item> ACACIA_SALOON_DOOR = ITEMS.register("acacia_saloon_door", () ->
    new BlockItem(MinecraftYellowBlocks.ACACIA_SALOON_DOOR.get(), new Item.Properties().arch$tab(MinecraftYellowItems.TAB)));
  public static final RegistrySupplier<Item> DARK_OAK_SALOON_DOOR = ITEMS.register("dark_oak_saloon_door", () ->
    new BlockItem(MinecraftYellowBlocks.DARK_OAK_SALOON_DOOR.get(), new Item.Properties().arch$tab(MinecraftYellowItems.TAB)));
  public static final RegistrySupplier<Item> MANGROVE_SALOON_DOOR = ITEMS.register("mangrove_saloon_door", () ->
    new BlockItem(MinecraftYellowBlocks.MANGROVE_SALOON_DOOR.get(), new Item.Properties().arch$tab(MinecraftYellowItems.TAB)));
  public static final RegistrySupplier<Item> CHERRY_SALOON_DOOR = ITEMS.register("cherry_saloon_door", () ->
    new BlockItem(MinecraftYellowBlocks.CHERRY_SALOON_DOOR.get(), new Item.Properties().arch$tab(MinecraftYellowItems.TAB)));

  public static final RegistrySupplier<Item> CRIMSON_SALOON_DOOR = ITEMS.register("crimson_saloon_door", () ->
    new BlockItem(MinecraftYellowBlocks.CRIMSON_SALOON_DOOR.get(), new Item.Properties().arch$tab(MinecraftYellowItems.TAB)));
  public static final RegistrySupplier<Item> WARPED_SALOON_DOOR = ITEMS.register("warped_saloon_door", () ->
    new BlockItem(MinecraftYellowBlocks.WARPED_SALOON_DOOR.get(), new Item.Properties().arch$tab(MinecraftYellowItems.TAB)));

  public static void register() {
    ITEMS.register();
    TABS.register();


    MinecraftYellowExpectPlatform.registerItems();
    if(Platform.getEnv() == EnvType.CLIENT) {
      ColorHandlerRegistry.registerItemColors(new YellowArmorColors(), BANDANA);
      ColorHandlerRegistry.registerItemColors(new YellowArmorColors(), COWBOY_HAT);
    }
  }
}
