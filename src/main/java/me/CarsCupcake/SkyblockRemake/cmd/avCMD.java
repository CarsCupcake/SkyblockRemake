package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Skyblock.AnvilInventory;

public class avCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player && arg2.equalsIgnoreCase("av")) {
			Player player = (Player) arg0;
			player.openInventory(AnvilInventory.CustomAnvilInventory());
			return true;
		}
		return false;
	}

}
