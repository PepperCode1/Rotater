package me.pepperbell.rotater.client.render;

import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

public class RenderUtil {
	public static Mesh createTaterMesh(Sprite sprite) {
		MeshBuilder builder = RendererAccess.INSTANCE.getRenderer().meshBuilder();
		QuadEmitter emitter = builder.getEmitter();

		emitter.square(Direction.DOWN, 0.25F, 0.25F, 0.75F, 0.75F, 0.0F);
		squareSprite(emitter, sprite, 0.75F, 0.0F, 0.5F, 0.25F);
		emitter.spriteColor(0, -1, -1, -1, -1);
		emitter.emit();

		emitter.square(Direction.UP, 0.25F, 0.25F, 0.75F, 0.75F, 0.25F);
		squareSprite(emitter, sprite, 0.5F, 0.25F, 0.25F, 0.0F);
		emitter.spriteColor(0, -1, -1, -1, -1);
		emitter.emit();

		emitter.square(Direction.NORTH, 0.25F, 0.0F, 0.75F, 0.75F, 0.25F);
		squareSprite(emitter, sprite, 0.25F, 0.25F, 0.5F, 0.625F);
		emitter.spriteColor(0, -1, -1, -1, -1);
		emitter.emit();

		emitter.square(Direction.SOUTH, 0.25F, 0.0F, 0.75F, 0.75F, 0.25F);
		squareSprite(emitter, sprite, 0.75F, 0.25F, 1.0F, 0.625F);
		emitter.spriteColor(0, -1, -1, -1, -1);
		emitter.emit();

		emitter.square(Direction.WEST, 0.25F, 0.0F, 0.75F, 0.75F, 0.25F);
		squareSprite(emitter, sprite, 0.5F, 0.25F, 0.75F, 0.625F);
		emitter.spriteColor(0, -1, -1, -1, -1);
		emitter.emit();

		emitter.square(Direction.EAST, 0.25F, 0.0F, 0.75F, 0.75F, 0.25F);
		squareSprite(emitter, sprite, 0.0F, 0.25F, 0.25F, 0.625F);
		emitter.spriteColor(0, -1, -1, -1, -1);
		emitter.emit();

		return builder.build();
	}

	public static void squareSprite(QuadEmitter emitter, Sprite sprite, float minU, float minV, float maxU, float maxV) {
		emitter.sprite(0, 0, MathHelper.lerp(minU, sprite.getMinU(), sprite.getMaxU()), MathHelper.lerp(minV, sprite.getMinV(), sprite.getMaxV()));
		emitter.sprite(1, 0, MathHelper.lerp(minU, sprite.getMinU(), sprite.getMaxU()), MathHelper.lerp(maxV, sprite.getMinV(), sprite.getMaxV()));
		emitter.sprite(2, 0, MathHelper.lerp(maxU, sprite.getMinU(), sprite.getMaxU()), MathHelper.lerp(maxV, sprite.getMinV(), sprite.getMaxV()));
		emitter.sprite(3, 0, MathHelper.lerp(maxU, sprite.getMinU(), sprite.getMaxU()), MathHelper.lerp(minV, sprite.getMinV(), sprite.getMaxV()));
	}

	public static ModelTransformation getBlockTransformation(ModelLoader loader) {
		UnbakedModel model = loader.getOrLoadModel(new Identifier("block/block"));
		if (model instanceof JsonUnbakedModel) {
			return ((JsonUnbakedModel) model).getTransformations();
		}
		return ModelTransformation.NONE;
	}
}
