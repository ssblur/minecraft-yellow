package com.ssblur.minecraftyellow.fabric.models;

import com.ssblur.minecraftyellow.MinecraftYellow;
import com.ssblur.minecraftyellow.fabric.item.HatWithTheBrim;
import com.ssblur.minecraftyellow.item.MinecraftYellowItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.DyeableGeoArmorRenderer;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import java.util.function.Supplier;

public class HatWithTheBrimRenderer extends DyeableGeoArmorRenderer<HatWithTheBrim> {
  public HatWithTheBrimRenderer() {
    super(
      new DefaultedItemGeoModel<>(
        new ResourceLocation(MinecraftYellow.MOD_ID, "armor/cowboy_hat")
      )
    );
  }

  @Override
  public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

  }

  @Override
  protected boolean isBoneDyeable(GeoBone bone) {
    return bone.getName().contains("dyeable");
  }

  @Override
  protected @NotNull Color getColorForBone(GeoBone bone) {
    var color = MinecraftYellowItems.COWBOY_HAT.get().getColor(this.currentStack);
    return Color.ofOpaque(color);
  }
}
