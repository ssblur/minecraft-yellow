package com.ssblur.minecraftyellow.item.armor;

import com.ssblur.minecraftyellow.MinecraftYellow;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum YellowArmorMaterials implements ArmorMaterial {
  CLOTH(
    "cloth", 9, 15, 0.0f, 0.0f,
    () -> Ingredient.of(ItemTags.WOOL), 1, 1, 1, 1
  );

  final String name;
  final int durability;
  final int enchantability;
  final float toughness;
  final float knockbackResistance;
  final Supplier<Ingredient> repairSupplier;
  final int helmetDurability, chesplateDurability,leggingsDurability, bootsDurability;
  YellowArmorMaterials(
    String name, int durability, int enchantability, float toughness,
    float knockbackResistance, Supplier<Ingredient> repairSupplier,
    int helmetDurability, int chestplateDurability, int leggingsDurability, int bootsDurability
  ) {
    this.name = name;
    this.durability = durability;
    this.enchantability = enchantability;
    this.toughness = toughness;
    this.knockbackResistance = knockbackResistance;
    this.repairSupplier = repairSupplier;
    this.helmetDurability = helmetDurability;
    this.chesplateDurability = chestplateDurability;
    this.leggingsDurability = leggingsDurability;
    this.bootsDurability = bootsDurability;
  }

  @Override
  public int getDurabilityForType(ArmorItem.Type type) {
    switch (type) {
      case HELMET -> {
        return 11 * durability;
      }
      case CHESTPLATE -> {
        return 16 * durability;
      }
      case LEGGINGS -> {
        return 15 * durability;
      }
      case BOOTS -> {
        return 13 * durability;
      }
    }
    return 0;
  }

  @Override
  public int getDefenseForType(ArmorItem.Type type) {
    switch (type) {
      case HELMET -> {
        return helmetDurability;
      }
      case CHESTPLATE -> {
        return chesplateDurability;
      }
      case LEGGINGS -> {
        return leggingsDurability;
      }
      case BOOTS -> {
        return bootsDurability;
      }
    }
    return 0;
  }

  @Override
  public int getEnchantmentValue() {
    return enchantability;
  }

  @Override
  public SoundEvent getEquipSound() {
    return SoundEvents.ARMOR_EQUIP_LEATHER;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return repairSupplier.get();
  }

  @Override
  public String getName() {
    return MinecraftYellow.MOD_ID + ":" + name;
  }

  @Override
  public float getToughness() {
    return toughness;
  }

  @Override
  public float getKnockbackResistance() {
    return knockbackResistance;
  }
}
