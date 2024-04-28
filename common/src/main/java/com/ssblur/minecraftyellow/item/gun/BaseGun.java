package com.ssblur.minecraftyellow.item.gun;

import com.ssblur.minecraftyellow.event.network.MinecraftYellowNetwork;
import com.ssblur.minecraftyellow.event.network.ParticleNetwork;
import com.ssblur.minecraftyellow.event.network.TraceNetwork;
import com.ssblur.minecraftyellow.sound.MinecraftYellowSounds;
import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BaseGun extends Item {
  public static final Random RANDOM = new Random();
  List<SoundEvent> sounds;
  int damage;
  int ammo;
  Predicate<ItemStack> ammoPredicate;
  public BaseGun(Properties properties, int damage, int ammo) {
    super(properties.durability(ammo));

    this.sounds = new ArrayList<>();
    this.damage = damage;
    this.ammo = ammo;
    ammoPredicate = null;
  }

  public BaseGun withSounds(SoundEvent... sound) {
    sounds = Arrays.stream(sound).toList();
    return this;
  }

  public BaseGun withAmmo(Item item) {
    this.ammoPredicate = i -> i.getItem() == item;

    return this;
  }

  public BaseGun withAmmo(Supplier<Item> itemProvider) {
    this.ammoPredicate = i -> i.getItem() == itemProvider.get();

    return this;
  }

  public BaseGun withAmmo(Predicate<ItemStack> itemPredicate) {
    this.ammoPredicate = itemPredicate;
    return this;
  }

  public boolean isBarVisible(ItemStack itemStack) {
    return true;
  }

  @Override
  public int getBarWidth(ItemStack itemStack) {
    if(itemStack.getTag() != null) {
      int ammo = itemStack.getTag().getInt("ammo");
      return Math.round(13.0F * (((float) ammo)/((float) this.ammo)));
    }
    return 0;
  }

  public boolean consumesAmmo(ItemStack item) {
    return this.ammoPredicate != null && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, item) <= 0;
  }

  void reload(Level level, Player player, ItemStack itemStack) {
    var tag = itemStack.getOrCreateTag();
    if(!consumesAmmo(itemStack)) {
      level.playSound(null, player.blockPosition().above(), MinecraftYellowSounds.GUN_RELOAD.get(), SoundSource.PLAYERS);
      tag.putInt("ammo", ammo);
      player.getCooldowns().addCooldown(this, 60);
      return;
    }

    ItemStack match = null;
    for(var item: player.getInventory().items) {
      if(ammoPredicate.test(item)) {
        match = item;
        break;
      }
    }

    if(match == null) {
      level.playSound(null, player.blockPosition().above(), MinecraftYellowSounds.NO_AMMO_CLICK.get(), SoundSource.PLAYERS);
      player.getCooldowns().addCooldown(this, 5);
    } else {
      level.playSound(null, player.blockPosition().above(), MinecraftYellowSounds.GUN_RELOAD.get(), SoundSource.PLAYERS);
      int availableAmmo = Math.min(this.ammo, match.getCount());
      match.shrink(availableAmmo);
      tag.putInt("ammo", availableAmmo);
      player.getCooldowns().addCooldown(this, 60);
    }
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
    if(!level.isClientSide()) {
      if(damage > 0) {
        var itemStack = player.getItemInHand(interactionHand);
        var tag = itemStack.getOrCreateTag();

        if(tag.getInt("ammo") > 0) {
          TraceNetwork.requestTraceData(player, (type, result) -> {
            if (type == TraceNetwork.TYPE.ENTITY && result != null && result.right().isPresent()) {
              var entity = result.right().get();
              entity.hurt(level.damageSources().playerAttack(player), damage);
            }
          });

          if (sounds.size() > 0)
            level.playSound(null, player.blockPosition().above(), sounds.get(RANDOM.nextInt(sounds.size())), SoundSource.PLAYERS);

          var ang = player.getLookAngle();
          var pos = ang.normalize().scale(0.6d).add(player.blockPosition().above().getCenter());
          ParticleNetwork.sendSmokeParticles((ServerLevel) level, pos);

          tag.putInt("ammo", tag.getInt("ammo") - 1);
          player.getCooldowns().addCooldown(this, 15);
          if(tag.getInt("ammo") <= 0)
            reload(level, player, itemStack);
        } else {
          reload(level, player, itemStack);
        }
      }
    }
    return super.use(level, player, interactionHand);
  }
}
