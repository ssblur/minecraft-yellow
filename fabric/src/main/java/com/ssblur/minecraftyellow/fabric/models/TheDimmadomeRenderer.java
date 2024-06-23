package com.ssblur.minecraftyellow.fabric.models;

import com.ssblur.minecraftyellow.MinecraftYellow;
import com.ssblur.minecraftyellow.fabric.item.HatWithTheBrim;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class TheDimmadomeRenderer extends GeoArmorRenderer<HatWithTheBrim> {
  public TheDimmadomeRenderer() {
    super(
      new DefaultedItemGeoModel<>(
        new ResourceLocation(MinecraftYellow.MOD_ID, "armor/the_dimmadome")
      )
    );
  }
}
