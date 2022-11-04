package me.CarsCupcake.SkyblockRemake.Items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;

public class SpawnEggs {
	
	public static ItemStack ZombieSlayerLVL1SpawnEgg() {
		ItemStack item = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("§r§l§cRevenant Horror Tier 1 500 HP");
		PersistentDataContainer data = meta.getPersistentDataContainer();
		data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, "RevT1");
		item.setItemMeta(meta);
		return item;
	
	}
	
	
	public static ItemStack ZombieSlayerLVL2SpawnEgg() {
		ItemStack item = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("§r§l§cRevenant Horror Tier 2 20k HP");
		PersistentDataContainer data = meta.getPersistentDataContainer();
		data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, "RevT2");
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ZombieSlayerLVL3SpawnEgg() {
		ItemStack item = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("§r§l§cRevenant Horror Tier 3 400k HP");
		PersistentDataContainer data = meta.getPersistentDataContainer();
		data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, "RevT3");
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ZombieSlayerLVL4SpawnEgg() {
		ItemStack item = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("§r§l§cRevenant Horror Tier 4 1.5m HP");
		PersistentDataContainer data = meta.getPersistentDataContainer();
		data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, "RevT4");
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ZombieSlayerLVL3MiniBoss() {
		ItemStack item = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("§r§l§cRevenant Sycophant 24k HP");
		PersistentDataContainer data = meta.getPersistentDataContainer();
		data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, "RevMiniT3");
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ZombieSlayerLVL4MiniBossEasy() {
		ItemStack item = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("§r§l§cRevenant Sycophant 90k HP");
		PersistentDataContainer data = meta.getPersistentDataContainer();
		data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, "RevMiniT4_1");
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack ZombieSlayerLVL4MiniBossHard() {
		ItemStack item = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("§r§l§cRevenant Sycophant 360k HP");
		PersistentDataContainer data = meta.getPersistentDataContainer();
		data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, "RevMiniT4_2");
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack EndermanSlayerLVL2() {
		ItemStack item = new ItemStack(Material.ENDERMAN_SPAWN_EGG);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName("§r§l§cVoidgloom Seraph 15m HP");
		PersistentDataContainer data = meta.getPersistentDataContainer();
		data.set(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING, "VoidT2");
		item.setItemMeta(meta);
		return item;
	}

}
