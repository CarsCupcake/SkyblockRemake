package me.CarsCupcake.SkyblockRemake.AccessoryBag.Powers;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Tools;

public class MaxwellInv {
public static Inventory getMainInventory(Player player) {
	Inventory inv = Bukkit.createInventory(null, 54,"§8Accessory Bag Thaumatologist");
	
	ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(" ");
	item.setItemMeta(meta);
	for(int i = 0; i < 10; i++)
		inv.setItem(i, item);
	for(int i = 18; i < 46; i += 9)
		inv.setItem(i, item);
	for(int i =8; i < 54; i += 9)
		inv.setItem(i, item);
	inv.setItem(46, item);
	inv.setItem(52, item);
	item = Tools.CustomHeadTexture("http://textures.minecraft.net/texture/961a918c0c49ba8d053e522cb91abc74689367b4d8aa06bfc1ba9154730985ff");
	meta = item.getItemMeta();
	meta.setDisplayName("§aAccessory Bag Shortcut");
	item.setItemMeta(meta);
	inv.setItem(47, item);
	
	
	item = new ItemStack(Material.MAP);
	meta = item.getItemMeta();
	meta.setDisplayName("§aAccessorys Breakdown");
	item.setItemMeta(meta);
	inv.setItem(48, item);
	
	item = new ItemStack(Material.BARRIER);
	meta = item.getItemMeta();
	meta.setDisplayName("§cClose");
	item.setItemMeta(meta);
	inv.setItem(49, item);
	
	
	item = Tools.CustomHeadTexture("http://textures.minecraft.net/texture/71e1f6162db42245639609f728a4e134ed7bd7de3c15a7792d219a6e2a9db");
	meta = item.getItemMeta();
	meta.setDisplayName("§aLearn Power From Stones");
	item.setItemMeta(meta);
	inv.setItem(50, item);
	
	item = new ItemStack(Material.COMPARATOR);
	meta = item.getItemMeta();
	meta.setDisplayName("§aStats Tuning");
	item.setItemMeta(meta);
	inv.setItem(51, item);
	int run = 0;
	ArrayList<Powers> obt = Powers.getObitained(player);
	for(int layer = 10; layer < 38; layer += 9) {
		for(int i = layer; i < layer + 8; i++) {
			if(run < obt.size()) {
				Powers power = obt.get(run);
				ItemStack it = power.getItem();
				if(Powers.activepower.containsKey(player) && Powers.activepower.get(player) == power) 
					it.setType(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta m = it.getItemMeta();
				PersistentDataContainer data = m.getPersistentDataContainer();
				data.set(new NamespacedKey(Main.getMain(), "power"), PersistentDataType.STRING, power.getName());
				m.setDisplayName("§a" + power.getName());
				if(Powers.activepower.containsKey(player) && Powers.activepower.get(player) == power) 
					m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
				m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				ArrayList<String> lore = new ArrayList<>();
				
				for(Stats stat : Stats.values()) {
					if(power.CalculateStats(stat, player) != 0 )
						if(power.getPackage().basestats.containsKey(stat) && power.getPackage().basestats.get(stat) != 0 && power.CalculateStats(stat, player)- power.getPackage().basestats.get(stat) != 0)
							lore.add("§7" + stat.toString() + ": " + (power.CalculateStats(stat, player)- power.getPackage().basestats.get(stat))); 
						else
					lore.add("§7" + stat.toString() + ": " + power.CalculateStats(stat, player)); 
				}
				lore.add(" ");
				lore.add(" §7Unique stat bonus:");
				for(Stats stat : Stats.values()) {
					
						if(power.getPackage().basestats.containsKey(stat) && power.getPackage().basestats.get(stat) != 0)
							lore.add(" §7" + stat.toString() + ": " +  power.getPackage().basestats.get(stat)); 
						
				}
				m.setLore(lore);
				it.setItemMeta(m);
				inv.setItem(i, it);
				
			}
			run++;
		}
	}
	return inv;
	
	
	
	
	
}
public static Inventory LearnPowerFromStone(Player player) {
	Inventory inv = Bukkit.createInventory(null, 54,"§8Learn From Power Stone");
	ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(" ");
	item.setItemMeta(meta);
	for(int i = 0; i < 13; i++)
		inv.setItem(i, item);
	for (int i = 16; i < 22; i++)
		inv.setItem(i, item);
	for (int i = 25; i < 31; i++)
		inv.setItem(i, item);
	for (int i = 34; i < 49; i++)
		inv.setItem(i, item);
	
	
	
	
	
	
	item = Tools.CustomHeadTexture("http://textures.minecraft.net/texture/71e1f6162db42245639609f728a4e134ed7bd7de3c15a7792d219a6e2a9db");
	meta = item.getItemMeta();
	meta.setDisplayName("§aLearn new Power");
	item.setItemMeta(meta);
	inv.setItem(20, item);
	
	return inv;
}
}
