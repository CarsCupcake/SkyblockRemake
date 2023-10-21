package me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag;

import java.util.ArrayList;
import java.util.HashMap;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.configs.AccessoryBag;

public class AccessoryInventory {
 public	ArrayList<Inventory> invs = new ArrayList<>();
 public static HashMap<Player, AccessoryInventory> activeInvs = new HashMap<>();
public AccessoryInventory(Player player) {

	AccessoryBag.reload();
	int slots = AccessoryBag.get().getInt(player.getUniqueId() + ".slots");
	int invs =1+  (slots / 45);

	int list = 0;
	int invCount;
	for(invCount = 1; invCount <= invs; invCount++) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Accessory Bag (" + invCount + "/" + invs + ")");
		ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		PersistentDataContainer data = meta.getPersistentDataContainer();
		 meta.setDisplayName(" ");
		 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		for(int i = 45; i < 53; i++) {
			inv.setItem(i, item);
		}
		if(invCount != invs) {
		item.setType(Material.ARROW);
		
		data.set(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER, invCount);
		meta.setDisplayName("§aNext Page");
		 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(53, item);
		}else {
			data.set(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER, invCount);
			item.setItemMeta(meta);
			inv.setItem(53, item);
		}
		if(invCount != 1) {
			item.setType(Material.ARROW);
		meta.setDisplayName("§aLast Page");
		 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		inv.setItem(45, item);
		}
		
		
		for(int i = 0; i < 45; i++) {
			AccessoryBag.reload();
			if(list < slots && AccessoryBag.get().getConfigurationSection(player.getUniqueId() + ".SLOT_" + list) != null) {
				ItemStack it = AccessoryBag.get().getItemStack(player.getUniqueId() + ".SLOT_" + list + ".item");
				inv.setItem(i, Main.item_updater(it, SkyblockPlayer.getSkyblockPlayer(player)));
				
				
				
			}else {
				if(list >= slots) {
					 item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
					 meta = item.getItemMeta();
					 data = meta.getPersistentDataContainer();
					 data.set(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING, "true");
					 meta.setDisplayName(" ");
					 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					item.setItemMeta(meta);
					inv.setItem(i, item);
				}
			}
			list++;
			
			
		}
		
		
		
		this.invs.add(inv);
		
		
	}
	activeInvs.put(player, this);
}

}
