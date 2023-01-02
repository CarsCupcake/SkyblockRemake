package me.CarsCupcake.SkyblockRemake.cmd;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import me.CarsCupcake.SkyblockRemake.ItemsSearch;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class itemCMD implements CommandExecutor{
	public static Inventory Items;
	public static Inventory Items2;
	public static ArrayList<Inventory> items = new ArrayList<>();
	public static ArrayList<ItemManager> customItems = new ArrayList<>();
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("item")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			Player player = (Player) sender;
			if(args.length == 1)
				ItemsSearch.buildInventory(SkyblockPlayer.getSkyblockPlayer(player), args[0]);
			else
			
			player.openInventory(items.get(0));
			player.updateInventory();
			return true;
			
		}
		
		return false;
	}


	
	public static void createItemInvs() {
		
		
		ArrayList<ItemManager> managers =   new ArrayList<>() ;
		ArrayList<String> strings =   new ArrayList<>() ;
		
		for(String str: me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.keySet()) {
			if(!me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.get(str).isBaseItem) {
				strings.add(str);
				
			}
		}
		Collections.sort(strings);
		for(int i = 0; i < strings.size(); i++) {
			managers.add(me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.get(strings.get(i)));
			customItems.add(me.CarsCupcake.SkyblockRemake.Items.Items.SkyblockItems.get(strings.get(i)));
		}
		int invs =1+  (managers.size() / 45);
	
	
		int list = 0;
		int invCount = 1;
		for(invCount = 1; invCount <= invs; invCount++) {
			Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Custom Items Menu - Page " + invCount);
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
			item.setType(Material.OAK_SIGN);
			meta.setDisplayName("§aSearch");
			 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			item.setItemMeta(meta);
			inv.setItem(49, item);
			int i = 0;
			
			while(i < 45){


				if(!(list >= managers.size())) {
					if(!managers.get(list).hasEdition)
					inv.setItem(i, Main.item_updater(managers.get(list).getRawItemStack(),null) );
					else
						i -= 1;
					}
					
				
			
				
			i += 1;
				list++;
				
			}
			
			items.add(inv);
			
		}
	}
	
	
	
	

}
