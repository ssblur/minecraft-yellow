package com.ssblur.minecraftyellow.mixin;

import com.ssblur.minecraftyellow.MinecraftYellowGameRules;
import com.ssblur.minecraftyellow.item.MinecraftYellowItems;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerGetDimensions {
  @Inject(
    method = "getDimensions",
    at = @At(value="RETURN"),
    cancellable = true
  )
  private void getDimensions(Pose pose, CallbackInfoReturnable<EntityDimensions> info) {
    Player self = (Player) (Object) this;
    if(self.level().getGameRules().getBoolean(MinecraftYellowGameRules.WILD_WASTELAND))
      if(self.getItemBySlot(EquipmentSlot.HEAD).getItem() == MinecraftYellowItems.THE_DIMMADOME.get())
        info.setReturnValue(info.getReturnValue().scale(1.0f, 2.0f));
  }
}
