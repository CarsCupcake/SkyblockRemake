package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Skyblock.terminals.maze;
import org.jetbrains.annotations.NotNull;


public class openterminal implements CommandExecutor {

	public boolean onCommand(@NotNull CommandSender sender,@NotNull Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("terminal")) {
			if (!(sender instanceof Player player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			if(args.length == 1) {


				if (args[0].equalsIgnoreCase("color")) {
					new ColorTerminal(null, -1).open(SkyblockPlayer.getSkyblockPlayer(player));
				return true;
				}
				
				if (args[0].equalsIgnoreCase("maze")) {
					maze.createinventory(player.getLocation());
					player.openInventory(maze.mazeterminal);
				return true;
				}

				if (args[0].equalsIgnoreCase("order")) {
					new OrderTerminal(null, -1).open(SkyblockPlayer.getSkyblockPlayer(player));
					return true;
				}
				if (args[0].equalsIgnoreCase("text")) {
					new TextTerminal(null, -1).open(SkyblockPlayer.getSkyblockPlayer(player));
					return true;
				}
				if(args[0].equals("panes")){
					new PanesTerminal(null, -1).open(SkyblockPlayer.getSkyblockPlayer(player));
					return true;
				}
				if(args[0].equals("lockinslot")){
					new LockInSlotTerminal(null, -1).open(SkyblockPlayer.getSkyblockPlayer(player));
					return true;
				}
				if (args[0].equalsIgnoreCase("name")) {

					player.sendMessage("Name terminal is unfinished!");
				return true;
				}
			player.sendMessage(ChatColor.RED + "Du hast kein gültiges terminal geöffnet");
			return true;
			
			}	
		}
		return false;
	}
	
	

}
