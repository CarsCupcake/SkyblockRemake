package me.CarsCupcake.SkyblockRemake.Gemstones;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class GemstoneGrinderInv {

	public static Inventory getInv() {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Gemstone Grinder");
		ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(0, item);
		
		for(int i = 1; i != 13; i++) {
			inv.setItem(i, item);
		}
		
		for(int i = 14; i != 28; i++) {
			inv.setItem(i, item);
		}
		for(int i = 28; i != 35; i++) {
			inv.setItem(i, NoItemSlot());
		}
		for(int i = 35; i != 49; i++) {
			inv.setItem(i, item);
		}
		for(int i = 50; i != 54; i++) {
			inv.setItem(i, item);
		}
		
		inv.setItem(49, Close());
		
		
		
		return inv;
	}
	
	public static ItemStack NoItemSlot() {
		ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§dGemstone Slot");
		ArrayList<String> lore = new  ArrayList<>();
		lore.add("§7Place an item above to apply");
		lore.add("§7Gemstone to it!");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack Close() {
		ItemStack item = new ItemStack(Material.BARRIER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§cClose");
		
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		return item;
	}
	
	
}
