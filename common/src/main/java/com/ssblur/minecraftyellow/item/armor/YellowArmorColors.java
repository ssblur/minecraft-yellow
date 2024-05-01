package com.ssblur.minecraftyellow.item.armor;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class YellowArmorColors implements ItemColor
{
  @Override
  public int getColor(@NotNull ItemStack stack, int tintIndex)
  {
    if (tintIndex != 1) return 0xFFFFFFFF;
    if(stack.getItem() instanceof DyeableArmorItem item)
      return item.getColor(stack);
    return 0xFFFFFFFF;

  }
}
