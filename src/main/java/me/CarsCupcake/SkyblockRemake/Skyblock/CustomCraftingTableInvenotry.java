package me.CarsCupcake.SkyblockRemake.Skyblock;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class CustomCraftingTableInvenotry {


@Contract(pure = true)
public static @Nullable Inventory createInventory() {
	Inventory inv = Bukkit.createInventory(null,54,"§8Crafting Table");
	ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(" ");
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setItemMeta(meta);
	for (int i = 0;i < 10; i++)
		inv.setItem(i,item);
	inv.setItem(13,item);
	inv.setItem(14,item);
	inv.setItem(15,item);
	inv.setItem(16,QuickCraftDenieButton());
	inv.setItem(17,item);
	inv.setItem(18,item);
	inv.setItem(22,item);
	inv.setItem(23,CraftDenie());
	inv.setItem(24,item);
	inv.setItem(25,QuickCraftDenieButton());
	inv.setItem(26,item);
	inv.setItem(27,item);
	inv.setItem(31,item);
	inv.setItem(32,item);
	inv.setItem(33,item);
	inv.setItem(34,QuickCraftDenieButton());
	for (int i = 35; i < 45; i++)
		inv.setItem(i,item);
	item.setType(Material.RED_STAINED_GLASS_PANE);
	for (int i = 45; i < 54; i++)
		inv.setItem(i,item);
	inv.setItem(49,GoBackArrow());





	return inv;
}

public static @NotNull ItemStack QuickCraftDenieButton() {
	ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	
	ArrayList<String> lore = new ArrayList<>();
	meta.setDisplayName("§cQuick Craft Slot");
	lore.add("§7Quick crafting allows you to");
	lore.add("§7craft items without assembling");
	lore.add("§7the recipe");
	lore.add("§cCOMMING SOON");
	meta.setLore(lore);
	
	item.setItemMeta(meta);
	return item;
}
public static @NotNull ItemStack CraftDenie() {
	ItemStack item = new ItemStack(Material.BARRIER);
	ItemMeta meta = item.getItemMeta();
	
	ArrayList<String> lore = new ArrayList<>();
	meta.setDisplayName("§cRecipe Required");
	lore.add("§7Add the items for a valid recipe");
	lore.add("§7in the crafting grid to the");
	lore.add("§7left!");
	
	meta.setLore(lore);
	
	item.setItemMeta(meta);
	return item;
}
public static @NotNull ItemStack GoBackArrow() {
	ItemStack item = new ItemStack(Material.ARROW);
	ItemMeta meta = item.getItemMeta();
	
	ArrayList<String> lore = new ArrayList<>();
	meta.setDisplayName("§aGo Back");
	lore.add("§7To Skyblock Menu");
	
	meta.setLore(lore);
	
	item.setItemMeta(meta);
	return item;
}

}
