package me.CarsCupcake.SkyblockRemake.Configs;

import org.bukkit.configuration.file.FileConfiguration;


public class AccessoryBag {
	private static CustomConfig c;
	
	public static void setup() {
		c = new CustomConfig("Accessorys");
		
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
