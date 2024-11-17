package net.bird.hollowborn;

import net.bird.hollowborn.item.ModItemGroups;
import net.bird.hollowborn.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HollowbornMod implements ModInitializer {
	public static final String MOD_ID = "hollowborn";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}