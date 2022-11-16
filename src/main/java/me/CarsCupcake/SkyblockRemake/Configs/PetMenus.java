package me.CarsCupcake.SkyblockRemake.Configs;

import org.bukkit.configuration.file.FileConfiguration;


public class PetMenus {
	private static CustomConfig c;

	public static void setup() {
		c = new CustomConfig("pets");

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
