package me.pepperbell.rotater.block;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class RotaterBlock extends Block implements BlockEntityProvider, Waterloggable {
	private static final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 12, 12);

	public RotaterBlock(Settings settings) {
		super(settings);
		setDefaultState(stateManager.getDefaultState().with(Properties.WATERLOGGED, false));
	}

	@Override
	public BlockEntity createBlockEntity(BlockView blockView) {
		return RotaterBlockEntity.create();
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(Properties.WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return super.getPlacementState(ctx).with(Properties.WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
		if (state.get(Properties.WATERLOGGED)) {
			world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
	}

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return false;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(Properties.WATERLOGGED);
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		super.appendTooltip(stack, world, tooltip, options);
		tooltip.add(new TranslatableText("tooltip.rotater.rotater.speed", Formatting.GREEN.toString() + getRotationSpeed(stack)).formatted(Formatting.GRAY));
	}

	@Override
	public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
		ItemStack stack = super.getPickStack(world, pos, state);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof RotaterBlockEntity) {
			setRotationSpeed(stack, ((RotaterBlockEntity) blockEntity).getRotationSpeed());
		}
		return stack;
	}

	public static float getRotationSpeed(ItemStack stack) {
		CompoundTag tag = stack.getSubTag("BlockEntityTag");
		if (tag != null) {
			float speed = tag.getFloat(RotaterBlockEntity.SPEED_KEY);
			if (speed != 0.0F) {
				return speed;
			}
		}
		return RotaterBlockEntity.DEFAULT_SPEED;
	}

	public static void setRotationSpeed(ItemStack stack, float speed) {
		if (speed == 0.0F) {
			speed = RotaterBlockEntity.DEFAULT_SPEED;
		}
		stack.getOrCreateSubTag("BlockEntityTag").putFloat(RotaterBlockEntity.SPEED_KEY, speed);
	}
}
