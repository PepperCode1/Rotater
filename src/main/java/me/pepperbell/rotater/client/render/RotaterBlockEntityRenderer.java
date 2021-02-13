package me.pepperbell.rotater.client.render;

import me.pepperbell.rotater.RotaterCommon;
import me.pepperbell.rotater.block.RotaterBlockEntity;
import me.pepperbell.rotater.client.ClientTickCounter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class RotaterBlockEntityRenderer extends BlockEntityRenderer<RotaterBlockEntity> {
	public static final SpriteIdentifier TEXTURE = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier(RotaterCommon.MOD_ID, "model/rotater"));

	private final ModelPart model = new ModelPart(32, 32, 0, 0);

	public RotaterBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
		model.addCuboid(-4, -6, -4, 8, 12, 8);
		model.setPivot(8, 6, 8);
		model.roll = (float) (Math.PI);
	}

	@Override
	public void render(RotaterBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		model.yaw = -1 * ((ClientTickCounter.getTicks() + MinecraftClient.getInstance().getTickDelta()) * entity.getRotationSpeed());
		VertexConsumer vertices = TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid);
		model.render(matrices, vertices, light, overlay);
	}
}
