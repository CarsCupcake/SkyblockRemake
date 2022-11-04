package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Main;

public class togglestats implements CommandExecutor{
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("statsystem")) {

			
			if (Main.getMain().getConfig().getBoolean("StatSystem") == true) {
				Main.getMain().getConfig().set("StatSystem", false);
				Main.getMain().saveConfig();
				Main.getMain().reloadConfig();
				Main.getMain().getStatRunnable().cancel();
				for(Player player : Bukkit.getOnlinePlayers()) {
					player.resetMaxHealth();
				}
				
			}else {
				Main.getMain().getConfig().set("StatSystem", true);
				Main.getMain().saveConfig();
				Main.getMain().reloadConfig();
				Main.getMain().Stats();
			}
		}
		
			
			return false;	
		}
}
