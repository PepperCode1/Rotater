package me.pepperbell.rotater;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

public class RotaterCommon implements ModInitializer {
	public static final String MOD_ID = "rotater";
	public static final Logger LOGGER = LogManager.getLogger("rotater");

	@Override
	public void onInitialize() {
		Init.init();
	}
}
