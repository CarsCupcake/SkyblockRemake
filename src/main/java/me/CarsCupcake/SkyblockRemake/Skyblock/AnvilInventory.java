package me.CarsCupcake.SkyblockRemake.Skyblock;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilInventory {
public static Inventory CustomAnvilInventory() {
	;
	
	Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Anvil");
	
	ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(" ");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setItemMeta(meta);
	inv.setItem(0, item);
	
	for(int i = 1; i != 11; i++) {
		inv.setItem(i, item);
	}
	
	
	inv.setItem(11, DenieUpgrade());
	inv.setItem(12, DenieUpgrade());
	inv.setItem(13, DenieButton());
	inv.setItem(14, DenieSacrifice());
	inv.setItem(15, DenieSacrifice());
	for(int i = 16; i != 20; i++) {
		inv.setItem(i, item);
	}
	inv.setItem(20, DenieUpgrade());
	inv.setItem(21, item);
	inv.setItem(22, CombineButton());
	inv.setItem(23, item);
	inv.setItem(24, DenieSacrifice());
	for(int i = 25; i != 29; i++) {
		inv.setItem(i, item);
	}
	for(int i = 30; i != 33; i++) {
		inv.setItem(i, item);
	}
	for(int i = 34; i != 45; i++) {
		inv.setItem(i, item);
	}
	item.setType(Material.RED_STAINED_GLASS_PANE);
	for(int i = 45; i != 49; i++) {
		inv.setItem(i, item);
	}
	for(int i = 50; i != 54; i++) {
		inv.setItem(i, item);
	}
	item.setType(Material.BARRIER);
	meta.setDisplayName("§cClose");
	item.setItemMeta(meta);
	inv.setItem(49, item);
	
	
	return inv;
}
public static ItemStack DenieButton() {
	ItemStack item = new ItemStack(Material.BARRIER);
	ItemMeta meta = item.getItemMeta();
	
	ArrayList<String> lore = new ArrayList<>();
	meta.setDisplayName("§cAnvil");
	lore.add("§7Place a target item in the left");
	lore.add("§7slot and a sacrifice item in the");
	lore.add("§7right slot to combine");
	lore.add("§7Enchantments");
	meta.setLore(lore);
	
	item.setItemMeta(meta);
	return item;
}
public static ItemStack DenieUpgrade() {
	ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName("§6Item To Upgrade");
	ArrayList<String> upgradeLore = new ArrayList<>();
	upgradeLore.add("§7The item you want to upgrade");
	upgradeLore.add("§7should be placed in the slot on");
	upgradeLore.add("§7this side.");
	
	meta.setLore(upgradeLore);
	item.setItemMeta(meta);
	return item;
}
public static ItemStack DenieSacrifice() {
	ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName("§6Item To Sacrifice");
	ArrayList<String> upgradeLore = new ArrayList<>();
	upgradeLore.add("§7The item you are sacreficing in");
	upgradeLore.add("§7order to upgrade the item on the");
	upgradeLore.add("§7left should be placed in the");
	upgradeLore.add("§7slot on this side.");
	
	meta.setLore(upgradeLore);
	item.setItemMeta(meta);
	return item;
}
public static ItemStack CombineButton() {
	ItemStack item = new ItemStack(Material.ANVIL);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName("§aCombine Items");
	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§7Combine the items in the slots");
	lore.add("§7to the left and right below.");
	meta.setLore(lore);


	item.setItemMeta(meta);
	return item;
}
}
