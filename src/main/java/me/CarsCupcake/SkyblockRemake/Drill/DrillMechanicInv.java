package me.CarsCupcake.SkyblockRemake.Drill;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DrillMechanicInv {
public static Inventory drillMechInv() {
	Inventory inv =  Bukkit.createInventory(null, 54, "§7Drill Anvil");
	
	ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(" ");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setItemMeta(meta);
	inv.setItem(0, item);
	
	for(int i = 1; i != 11; i++) {
		if(i == 9)
			inv.setItem(i, NoDrillFuelTank());
		else
		inv.setItem(i, item);
	}
	
	
	inv.setItem(11, DenieDrill());
	inv.setItem(12, DenieDrill());
	inv.setItem(13, DenieAnvil());
	inv.setItem(14, Denieother());
	inv.setItem(15, Denieother());
	for(int i = 16; i != 20; i++) {
		if(i == 18)
			inv.setItem(i, NoDrillDrillEngin());
		else
		inv.setItem(i, item);
		}
	inv.setItem(20, DenieDrill());
	inv.setItem(21, item);
	inv.setItem(22, DrillCombine());
	inv.setItem(23, item);
	inv.setItem(24, Denieother());
	inv.setItem(25, item);
	inv.setItem(26, item);
	inv.setItem(27, NoDrillUpgradeModule());
	inv.setItem(28, item);
	inv.setItem(30, item);
	inv.setItem(31, item);
	inv.setItem(32, item);
	for(int i = 34; i != 45; i++) {
		inv.setItem(i, item);
	}
	item.setType(Material.RED_STAINED_GLASS_PANE);
	for(int i = 45; i != 54; i++) {
		inv.setItem(i, item);
	}
	item.setType(Material.BARRIER);
	meta.setDisplayName("§cClose");
	item.setItemMeta(meta);
	inv.setItem(49, item);
	
	
	return inv;
}
public static ItemStack DenieDrill() {
	ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	meta.setDisplayName("§2Drill");
	ArrayList<String> lore = new ArrayList<>();
	lore.add( " " );
	lore.add("§7Place the Drill you want to");
	lore.add("§7refuel or modify in the slot");
	lore.add("§7below.");
	
	meta.setLore(lore);
	
	item.setItemMeta(meta);
	
	return item;
}
public static ItemStack Denieother() {
	ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	meta.setDisplayName("§2Drill Fuel/Part");
	ArrayList<String> lore = new ArrayList<>();
	lore.add( " " );
	lore.add("§7Place the Drill you want to");
	lore.add("§7refuel or modify in the slot");
	lore.add("§7below.");
	
	meta.setLore(lore);
	
	item.setItemMeta(meta);
	
	return item;
}

public static ItemStack DenieAnvil() {
	ItemStack item = new ItemStack(Material.BARRIER);
	ItemMeta meta = item.getItemMeta();
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	meta.setDisplayName("§cDrill Anvil");
	ArrayList<String> lore = new ArrayList<>();
	lore.add( " " );
	lore.add("§7Place a Drill in the left slot");
	lore.add("§7and §2Drill Fuel §7or a §6Drill");
	lore.add("§6Part §7in the right slot!");
	
	
	meta.setLore(lore);
	item.setItemMeta(meta);
	
	return item;
}
public static ItemStack NoDrillFuelTank() {
	ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	meta.setDisplayName("§cRemove Fuel Tank");
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§7Place a Drill in the slot to the");
	lore.add("§7right to view and remove its");
	lore.add("§6Fuel Tank §7.");
	
	meta.setLore(lore);
	
	item.setItemMeta(meta);
	
	return item;
}

public static ItemStack NoDrillDrillEngin() {
	ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	meta.setDisplayName("§cRemove Drill Engine");
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§7Place a Drill in the slot to the");
	lore.add("§7right to view and remove its");
	lore.add("§6Drill Engine §7.");
	
	meta.setLore(lore);
	
	item.setItemMeta(meta);
	
	return item;
}

public static ItemStack NoDrillUpgradeModule() {
	ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	meta.setDisplayName("§cRemove Upgrade Module");
	ArrayList<String> lore = new ArrayList<>();
	lore.add("§7Place a Drill in the slot to the");
	lore.add("§7right to view and remove its");
	lore.add("§6Upgrade Module §7.");
	
	meta.setLore(lore);
	
	item.setItemMeta(meta);
	
	return item;
}
public static ItemStack DrillCombine() {
	ItemStack item = new ItemStack(Material.HOPPER);
	ItemMeta meta = item.getItemMeta();
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	meta.setDisplayName("§aDrill Anvil");
	ArrayList<String> lore = new ArrayList<>();
	lore.add(" ");
	lore.add("§7Combine the items in the slots");
	lore.add("§7to the left and right below.");
	meta.setLore(lore);
	
	item.setItemMeta(meta);
	
	return item;
}
	
}
