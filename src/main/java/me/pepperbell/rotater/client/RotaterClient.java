package me.pepperbell.rotater.client;

import me.pepperbell.rotater.Init;
import me.pepperbell.rotater.RotaterCommon;
import me.pepperbell.rotater.client.render.RotaterBlockEntityRenderer;
import me.pepperbell.rotater.client.render.RotaterItemModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;

public class RotaterClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			ClientTickCounter.tick();
		});
		ModelLoadingRegistry.INSTANCE.registerResourceProvider((manager) -> (resourceId, context) -> {
			if (resourceId.getNamespace().equals(RotaterCommon.MOD_ID) && resourceId.getPath().equals("item/rotater")) {
				return new RotaterItemModel(RotaterBlockEntityRenderer.TEXTURE);
			}
			return null;
		});
		BlockEntityRendererRegistry.INSTANCE.register(Init.ROTATER_BE, RotaterBlockEntityRenderer::new);
	}
}
