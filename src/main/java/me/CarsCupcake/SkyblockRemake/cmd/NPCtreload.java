package me.CarsCupcake.SkyblockRemake.cmd;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.CarsCupcake.SkyblockRemake.Main;





public class NPCtreload implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg2.equalsIgnoreCase("reloadchallengeplugin")) {
			Main.getMain().onDisable();
			Main.getMain().onEnable();
			Bukkit.broadcastMessage("Challenge Plugin got reloaded!");
			return true;
		}
		return false;
	}

}
