package me.CarsCupcake.SkyblockRemake.Items;

import org.bukkit.Bukkit;

public class getNMSClass {
	public static Class<?> getNMS(String path, String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName(path + "." + version + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
