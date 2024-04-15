package com.ssblur.minecraftyellow.sound;

import com.ssblur.minecraftyellow.MinecraftYellow;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class MinecraftYellowSounds {
  public static final String MOD_ID = MinecraftYellow.MOD_ID;

  public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);

  public static final RegistrySupplier<SoundEvent> NO_AMMO_CLICK = SOUNDS.register("no_ammo_click", () ->
    SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "no_ammo_click")));
  public static final RegistrySupplier<SoundEvent> GUN_CAP_POP = SOUNDS.register("gun_cap_pop", () ->
    SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "gun_cap_pop")));
  public static final RegistrySupplier<SoundEvent> GUN_BLAST = SOUNDS.register("gun_blast", () ->
    SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "gun_blast")));
  public static final RegistrySupplier<SoundEvent> GUN_RELOAD = SOUNDS.register("gun_reload", () ->
    SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "gun_reload")));
  public static void register() {
    SOUNDS.register();
  }
}
