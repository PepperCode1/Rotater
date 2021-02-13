package me.pepperbell.rotater.block;

import me.pepperbell.rotater.Init;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;

public class RotaterBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
	public static final float DEFAULT_SPEED = 0.05F;
	public static final String SPEED_KEY = "Speed";

	private float rotationSpeed = DEFAULT_SPEED;

	public RotaterBlockEntity(BlockEntityType<?> type) {
		super(type);
	}

	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		super.fromTag(state, tag);
		rotationSpeed = tag.getFloat(SPEED_KEY);
		validateRotationSpeed();
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		tag = super.toTag(tag);
		tag.putFloat(SPEED_KEY, rotationSpeed);
		return tag;
	}

	@Override
	public void fromClientTag(CompoundTag tag) {
		rotationSpeed = tag.getFloat(SPEED_KEY);
	}

	@Override
	public CompoundTag toClientTag(CompoundTag tag) {
		tag.putFloat(SPEED_KEY, rotationSpeed);
		return tag;
	}

	public float getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(float speed) {
		rotationSpeed = speed;
		validateRotationSpeed();
		markDirty();
	}

	public void validateRotationSpeed() {
		if (rotationSpeed == 0.0F) {
			rotationSpeed = DEFAULT_SPEED;
		}
	}

	public static RotaterBlockEntity create() {
		return new RotaterBlockEntity(Init.ROTATER_BE);
	}
}
