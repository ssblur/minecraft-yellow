package com.ssblur.minecraftyellow;

import net.minecraft.world.level.GameRules;

public class MinecraftYellowGameRules {
  public static GameRules.Key<GameRules.BooleanValue> WILD_WASTELAND;

  public static void register() {
    WILD_WASTELAND = GameRules.register(MinecraftYellow.MOD_ID + ":wild_wasteland", GameRules.Category.MISC, GameRules.BooleanValue.create(false));
  }
}
