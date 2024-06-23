package com.ssblur.minecraftyellow.item.armor;

import com.ssblur.minecraftyellow.MinecraftYellow;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class HatWithTheBrim extends DyeableArmorItem {
  public HatWithTheBrim(ArmorMaterial armorMaterial, Type type, Properties properties) {
    super(armorMaterial, type, properties);
  }

  public int getColor(ItemStack itemStack) {
    CompoundTag compoundTag = itemStack.getTagElement("display");
    return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 0x3F1900;
  }
}
