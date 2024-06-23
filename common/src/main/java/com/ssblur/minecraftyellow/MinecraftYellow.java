package com.ssblur.minecraftyellow;

import com.google.common.base.Suppliers;
import com.ssblur.minecraftyellow.block.MinecraftYellowBlocks;
import com.ssblur.minecraftyellow.blockentity.MinecraftYellowBlockEntities;
import com.ssblur.minecraftyellow.event.MinecraftYellowEvents;
import com.ssblur.minecraftyellow.event.network.MinecraftYellowNetwork;
import com.ssblur.minecraftyellow.item.MinecraftYellowItems;
import com.ssblur.minecraftyellow.item.gun.BaseGun;
import com.ssblur.minecraftyellow.sound.MinecraftYellowSounds;
import dev.architectury.platform.Platform;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class MinecraftYellow {
  public static final String MOD_ID = "minecraftyellow";

  public static void init() {
    MinecraftYellowGameRules.register();
    MinecraftYellowEvents.register();
    MinecraftYellowSounds.register();
    MinecraftYellowBlocks.register();
    MinecraftYellowItems.register();
    MinecraftYellowBlockEntities.register();

    if(Platform.getEnv() == EnvType.CLIENT) {

    }
  }
}
