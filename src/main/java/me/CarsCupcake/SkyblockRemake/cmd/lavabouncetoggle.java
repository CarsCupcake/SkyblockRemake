package me.CarsCupcake.SkyblockRemake.cmd;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.CarsCupcake.SkyblockRemake.Main;

public class lavabouncetoggle implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("lavabounce")) {

			
			if (Main.getMain().getConfig().getBoolean("LavaBounce") == true) {
				Main.getMain().getConfig().set("LavaBounce", false);
				Main.getMain().saveConfig();
				Main.getMain().reloadConfig();
			}else {
				Main.getMain().getConfig().set("LavaBounce", true);
				Main.getMain().saveConfig();
				Main.getMain().reloadConfig();
			}
		}
		
			
			return false;	
		}
}
