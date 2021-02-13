package me.pepperbell.rotater.client;

public class ClientTickCounter {
	private static int ticks = 0;

	public static int getTicks() {
		return ticks;
	}

	public static void tick() {
		++ticks;
	}
}
