package me.CarsCupcake.SkyblockRemake.configs;

import org.bukkit.configuration.file.FileConfiguration;


public class AccessoryBag {
	private static ConfigFile c;
	
	public static void setup() {
		c = new ConfigFile("Accessorys");
		
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
