package de.alex.arrowphysics;

import de.alex.arrowphysics.config.ConfigManager;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RevampedArrowPhysics implements ModInitializer {
	public static final String MOD_ID = "arrow-physics";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing overpowered!");
		ConfigManager.load();
		LOGGER.info("RevampedArrowPhysics successfully initialized :O");
	}
}