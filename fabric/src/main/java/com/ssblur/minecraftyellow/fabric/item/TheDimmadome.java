package com.ssblur.minecraftyellow.fabric.item;

import com.ssblur.minecraftyellow.fabric.models.HatWithTheBrimRenderer;
import com.ssblur.minecraftyellow.fabric.models.TheDimmadomeRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TheDimmadome extends ArmorItem implements GeoItem {
  private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
  private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

  public TheDimmadome(ArmorMaterial armorMaterial, Type type, Properties properties) {
    super(armorMaterial, type, properties);
  }

  @Override
  public void createRenderer(Consumer<Object> consumer) {
    consumer.accept(new RenderProvider() {
      private GeoArmorRenderer<?> renderer;

      @Override
      public HumanoidModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<LivingEntity> original) {
        if(this.renderer == null)
          this.renderer = new TheDimmadomeRenderer();
        this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
        return this.renderer;
      }
    });
  }

  @Override
  public Supplier<Object> getRenderProvider() {
    return this.renderProvider;
  }

  @Override
  public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    controllers.add(new AnimationController<>(this, 20, state -> PlayState.CONTINUE));
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return this.cache;
  }
}
