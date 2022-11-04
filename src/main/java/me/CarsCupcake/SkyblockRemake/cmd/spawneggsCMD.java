package me.CarsCupcake.SkyblockRemake.cmd;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.CarsCupcake.SkyblockRemake.Items.SpawnEggs;


public class spawneggsCMD implements CommandExecutor{
	public static Inventory Items;
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("spawns")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			Player player = (Player) sender;
			if(!player.getName().equals("CarsCupcake")){
				player.sendMessage("§cTemporarely unavaidable!");
				return false;
			}

			createItemInventory();
			player.openInventory(Items);
			player.updateInventory();
			return false;
			
		}
		
		return false;
	}
	
	public static void createItemInventory() {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Custom Spawn Eggs Menu - Page 1");
		
		inv.setItem(0, SpawnEggs.ZombieSlayerLVL1SpawnEgg());
		inv.setItem(1, SpawnEggs.ZombieSlayerLVL2SpawnEgg());
		inv.setItem(2, SpawnEggs.ZombieSlayerLVL3SpawnEgg());
		inv.setItem(3, SpawnEggs.ZombieSlayerLVL4SpawnEgg());
		inv.setItem(4, SpawnEggs.ZombieSlayerLVL3MiniBoss());
		inv.setItem(5, SpawnEggs.ZombieSlayerLVL4MiniBossEasy());
		inv.setItem(6, SpawnEggs.ZombieSlayerLVL4MiniBossHard());
		inv.setItem(7, SpawnEggs.EndermanSlayerLVL2());
		
	
		
		Items = inv;
	}

}
