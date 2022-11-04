package me.CarsCupcake.SkyblockRemake.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;



public class healthitems implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("healthitems")) {

			int def = 0;
			if (args.length >= 2) {
			if(args[1] == null) {
				 def = 0;
			}else {
				 def = Integer.parseInt(args[1]);
			}
			}
			ItemStack item = new ItemStack(Material.STICK);
			ItemMeta meta = item.getItemMeta();
			PersistentDataContainer data = meta.getPersistentDataContainer();
			int health = (int) Integer.parseInt(args[0]);
			data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.INTEGER, health);
			data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.INTEGER, def);
			List<String> lore = new ArrayList<>();
			if(health >= 0) {
				lore.add("?fHealth: ?a+" + health);
			}else {
				lore.add("?fHealth: ?a" + health);
			}
			if(def > 0) {
				lore.add("?fDefense: ?a+" + def);
			}else {
				if (def <0)
				lore.add("?fDefense: ?a" + def);
			}
			meta.setLore(lore);
			item.setItemMeta(meta);
			Player player = (Player) sender;
			player.getInventory().addItem(item);
			player.updateInventory();
			
		
			
			return false;	
		}
		return false;
}
}
