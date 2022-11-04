package me.CarsCupcake.SkyblockRemake.Dungeon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class MalikInventory {
public static Inventory Main() {
	Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GRAY +"Dungeon Blacksmith");
	ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	meta.setDisplayName(" ");
	item.setItemMeta(meta);
	for(int i = 0; i < 11; i++) {
		inv.setItem(i, item);
	}
	inv.setItem(12, item);
	inv.setItem(14, item);
	
	
	
	return inv;
	
}
}
