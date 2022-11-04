package me.CarsCupcake.SkyblockRemake.Skyblock;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Skyblock.terminals.color;
import me.CarsCupcake.SkyblockRemake.Skyblock.terminals.maze;
import me.CarsCupcake.SkyblockRemake.Skyblock.terminals.order;



public class openterminal implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("terminal")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			Player player = (Player) sender;
			if(args.length == 1) {


				if (args[0].equalsIgnoreCase("color")) {
					color.createColorTerminal();
					player.openInventory(color.colorterminal);
				return true;
				}
				
				if (args[0].equalsIgnoreCase("maze")) {
					maze.createinventory(player.getLocation());
					player.openInventory(maze.mazeterminal);
				return true;
				}
				
				if (args[0].equalsIgnoreCase("order")) {
					order.createInventory();
					player.openInventory(order.orderterminal);
				return true;
				}
				if (args[0].equalsIgnoreCase("name")) {

					player.sendMessage("Name terminal is unfinished!");
				return true;
				}
			player.sendMessage(ChatColor.RED + "Du hast kein g§ltiges terminal ge§ffnet");
			return true;
			
			}	
		}
		return false;
	}
	
	

}
