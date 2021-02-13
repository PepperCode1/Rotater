package me.pepperbell.rotater.client.render;

import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.Quaternion;

public class RotationQuadTransform implements RenderContext.QuadTransform {
	private final Vector3f vector = new Vector3f();
	public Quaternion rotation;

	@Override
	public boolean transform(MutableQuadView quad) {
		for (int vertexIndex = 0; vertexIndex < 4; ++vertexIndex) {
			quad.copyPos(vertexIndex, vector);
			vector.add(-0.5F, -0.5F, -0.5F);
			vector.rotate(rotation);
			vector.add(0.5F, 0.5F, 0.5F);
			quad.pos(vertexIndex, vector);

			if (quad.hasNormal(vertexIndex)) {
				quad.copyNormal(vertexIndex, vector);
				vector.rotate(rotation);
				quad.normal(vertexIndex, vector);
			}
		}
		return true;
	}
}
