package com.ssblur.minecraftyellow.block;

import com.ssblur.minecraftyellow.MinecraftYellow;
import com.ssblur.minecraftyellow.blockentity.SaloonDoorBlockEntity;
import com.ssblur.minecraftyellow.item.MinecraftYellowItems;
import dev.architectury.platform.Platform;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.fabricmc.api.EnvType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.CubeVoxelShape;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SaloonDoor extends Block implements EntityBlock {
  protected static final VoxelShape NS_AABB_BOTTOM = Block.box(6, 4, 0, 10, 16, 16);
  protected static final VoxelShape EW_AABB_BOTTOM = Block.box(0, 4, 6, 16, 16, 10);
  protected static final VoxelShape NS_AABB_TOP = Block.box(6, 0, 0, 10, 12, 16);
  protected static final VoxelShape EW_AABB_TOP = Block.box(0, 0, 6, 16, 12, 10);

  public static final BooleanProperty HALF = BooleanProperty.create("half");
  public static final IntegerProperty SWING = IntegerProperty.create("swing", 0, 2);
  public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);

  public SaloonDoor(Properties properties) {
    super(properties.noCollission().noOcclusion());

    if(!Platform.isForge() && Platform.getEnv() == EnvType.CLIENT)
      RenderTypeRegistry.register(RenderType.translucent(), this);
  }

  public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
    if(blockState.getValue(HALF))
      return blockState.getValue(FACING).getAxis() == Direction.Axis.Z ? NS_AABB_TOP : EW_AABB_TOP;
    return blockState.getValue(FACING).getAxis() == Direction.Axis.Z ? NS_AABB_BOTTOM : EW_AABB_BOTTOM;
  }

  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
    BlockPos pos = blockPlaceContext.getClickedPos();
    Level level = blockPlaceContext.getLevel();
    if(pos.getY() > level.getMaxBuildHeight() - 1 || !level.getBlockState(pos.above()).canBeReplaced(blockPlaceContext)) return null;

    var dir = blockPlaceContext.getClickedFace();
    if(dir.getAxis() == Direction.Axis.Y) return null;

    var blockState = level.getBlockState(pos.offset(dir.getOpposite().getNormal()));
    if(!blockState.isFaceSturdy(level, pos.offset(dir.getOpposite().getNormal()), dir)) return null;

    blockState = level.getBlockState(pos.offset(dir.getOpposite().getNormal()).above());
    if(!blockState.isFaceSturdy(level, pos.offset(dir.getOpposite().getNormal()).above(), dir)) return null;

    return defaultBlockState().setValue(FACING, dir).setValue(HALF, false).setValue(SWING, 0);
  }

  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(HALF, FACING, SWING);
  }

  @Override
  public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block block, BlockPos blockPos2, boolean bl) {
    super.neighborChanged(blockState, level, pos, block, blockPos2, bl);

    var otherHalf = blockState.getValue(HALF) ? Direction.DOWN : Direction.UP;
    if(!level.getBlockState(pos.offset(otherHalf.getNormal())).is(this)) {
      System.out.println(pos.offset(otherHalf.getNormal()));
      level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
      return;
    }

    var dir = blockState.getValue(FACING);
    var wallPos = pos.offset(dir.getOpposite().getNormal());
    var wallState = level.getBlockState(wallPos);
    if(!wallState.isFaceSturdy(level, wallPos, dir)) {
      level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

      var half =  blockState.getValue(HALF);
      if(half) {
        var itemStack = new ItemStack(MinecraftYellowItems.SALOON_DOOR.get());
        var center = pos.getCenter();
        var entity = new ItemEntity(level, center.x, center.y, center.z, itemStack);
        level.addFreshEntity(entity);
      }
    }
  }

  @Override
  public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
    level.setBlock(blockPos.above(), blockState.setValue(HALF, true), 3);
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new SaloonDoorBlockEntity(blockPos, blockState);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
    return SaloonDoorBlockEntity::tick;
  }
}
