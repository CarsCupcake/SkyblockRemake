package me.CarsCupcake.SkyblockRemake.timer;




import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Configs.ConfigFile;




public class TimerCommand implements CommandExecutor {
	public static boolean soulActive = false;
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			
		
		if (label.equalsIgnoreCase("start")) {
		/*getConfig().set("TimerActive", true);	
		Main.getMain().time = getConfig().getInt("TimerValue"); */
		if(Main.getMain().getConfig().getBoolean("TimerActive") == false) {
		Main.getMain().Timer();
		}else {
			Bukkit.broadcastMessage("timer is already runing");
		}
		if(args.length != 0) {
			
			if(args[0].equalsIgnoreCase("soul")) {
				ConfigFile.reload();
				if(!(ConfigFile.get().getString("SoulActive") == "true")) {
					ConfigFile.get().set("SoulActive", true);
					ConfigFile.save();
					ConfigFile.reload();
					for (Player player :Bukkit.getOnlinePlayers()) {
						
						ConfigFile.get().set(player.getUniqueId().toString() + ".soulchance", 15);
						ConfigFile.get().set(player.getUniqueId().toString() + ".souls", 0);
						ConfigFile.save();
						ConfigFile.reload();
					}
				Bukkit.broadcastMessage("The §lSoul Challenge §rhas been started");
				}else {
					Bukkit.broadcastMessage("SoulChallenge has already been started");
				}
				
				return true;
			}}
			return true;
			
		}
		
		return false;
	}


	

	

}
