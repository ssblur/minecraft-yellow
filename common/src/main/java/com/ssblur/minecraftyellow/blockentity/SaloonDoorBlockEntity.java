package com.ssblur.minecraftyellow.blockentity;

import com.ssblur.minecraftyellow.block.MinecraftYellowBlocks;
import com.ssblur.minecraftyellow.block.SaloonDoor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class SaloonDoorBlockEntity extends BlockEntity {
  public long delay;
  public SaloonDoorBlockEntity(BlockPos blockPos, BlockState blockState) {
    super(MinecraftYellowBlockEntities.SALOON_DOOR.get(), blockPos, blockState);
    delay = 0;
  }

  public void tick() {
    var pos = this.getBlockPos();
    var state = this.getBlockState();

    List<BlockPos> targets = new ArrayList<>();
    targets.add(pos);
    if(state.getValue(SaloonDoor.HALF))
      targets.add(pos.below());
    else
      targets.add(pos.above());

    var facing = state.getValue(SaloonDoor.FACING);
    boolean xAxis = facing.getAxis() == Direction.Axis.X;
    int swing = 0;

    for(var i: level.getEntitiesOfClass(Entity.class, AABB.unitCubeFromLowerCorner(Vec3.atLowerCornerOf(pos)))) {
      double v;
      if(xAxis)
        v = i.getDeltaMovement().z;
      else
        v = i.getDeltaMovement().x;

      if(Math.abs(v) > 0.1d && !i.isCrouching())
        swing = v > 0 ? 2 : 1;

      if(i instanceof LivingEntity living) {
        float rot = (living.yBodyRot % 360 + 360) % 360;
        if(!xAxis) {
          if (rot > 180)
            swing = 2;
          else
            swing = 1;
        } else {
          if(rot < 90 || rot > 270)
            swing = 2;
          else
            swing = 1;
        }
      }
    }

    if(swing > 0) {
      if (level.getBlockState(pos.offset(facing.getNormal())).getBlock() instanceof SaloonDoor) {
        var otherDoorPos = pos.offset(facing.getNormal());
        targets.add(otherDoorPos);
        if (level.getBlockState(otherDoorPos).getValue(SaloonDoor.HALF))
          targets.add(otherDoorPos.below());
        else
          targets.add(otherDoorPos.above());
      }

      if (level.getBlockState(pos).getValue(SaloonDoor.SWING) == 0)
        level.playSound(null, pos, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundSource.BLOCKS);
    }

    if(swing > 0 || delay < level.getGameTime())
      for (var i : targets) {
        var iState = level.getBlockState(i);
        if(iState.getBlock() instanceof SaloonDoor) level.setBlockAndUpdate(i, iState.setValue(SaloonDoor.SWING, swing));
        if(level.getBlockEntity(i) instanceof SaloonDoorBlockEntity blockEntity) blockEntity.delay = level.getGameTime() + 5;
      }
  }

  public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T entity) {
    if(level.isClientSide) return;
    if(entity instanceof SaloonDoorBlockEntity blockEntity) blockEntity.tick();
  }
}
