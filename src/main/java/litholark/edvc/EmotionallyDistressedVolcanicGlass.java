package litholark.edvc;

import net.fabricmc.api.ModInitializer;

import net.minecraft.block.GrassBlock;
import net.minecraft.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmotionallyDistressedVolcanicGlass implements ModInitializer {
	public static final String MOD_ID = "emotionally-distressed-volcanic-glass";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		//Registry.register()

		LOGGER.info("e");
	}
}