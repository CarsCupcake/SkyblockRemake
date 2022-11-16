package me.CarsCupcake.SkyblockRemake.Configs;

import org.bukkit.configuration.file.FileConfiguration;


public class EditionItems {
	private static CustomConfig c;

	public static void setup() {
		c = new CustomConfig("EditionItems");

	}

	public static FileConfiguration get() {
		return c.get();
	}

	public static void save() {
		c.save();
	}

	public static void reload() {
		c.reload();
	}

}
