package me.CarsCupcake.SkyblockRemake.timer;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.CarsCupcake.SkyblockRemake.Main;


public class timer implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("timer")) {
			if(args[0].equalsIgnoreCase("stop")) {
				Main.getMain().getRunnable().cancel();
				Main.getMain().getConfig().set("TimerActive", false);
				Main.getMain().getConfig().set("TimerValue", Main.getMain().time);
				Main.getMain().saveConfig();
				Main.getMain().reloadConfig();
			}
			
			if(args[0].equalsIgnoreCase("resume")) {
				Main.getMain().reloadConfig();
				if(Main.getMain().getConfig().getBoolean("TimerActive") == false)
				Main.getMain().Timer();
				else
				Bukkit.broadcastMessage("Timer is already running");
			}
			if(args[0].equalsIgnoreCase("clear")) {
				Main.getMain().getRunnable().cancel();
				Main.getMain().getConfig().set("TimerValue", 0);
				Main.getMain().time = 0;
				Main.getMain().saveConfig();
				Main.getMain().reloadConfig();
				if(Main.getMain().getConfig().getBoolean("TimerActive")) {
					Main.getMain().Timer();
				}
			}
		}
	
		
		return false;	
	}





}
