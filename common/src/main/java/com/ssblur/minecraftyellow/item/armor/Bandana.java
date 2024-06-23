package com.ssblur.minecraftyellow.item.armor;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;

public class Bandana extends DyeableArmorItem {
  public Bandana(ArmorMaterial armorMaterial, Type type, Properties properties) {
    super(armorMaterial, type, properties);
  }

  public int getColor(ItemStack itemStack) {
    CompoundTag compoundTag = itemStack.getTagElement("display");
    return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 0x212FA5;
  }
}
