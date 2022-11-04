package me.CarsCupcake.SkyblockRemake.cmd;


import me.CarsCupcake.SkyblockRemake.Dungeon.MiniBoss.ShadowAssasin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;








public class testobjectCMD implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("testobject")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			new ShadowAssasin().spawn(((Player) sender).getLocation());

			return true;
			
		}
		
		return false;
	}

}
