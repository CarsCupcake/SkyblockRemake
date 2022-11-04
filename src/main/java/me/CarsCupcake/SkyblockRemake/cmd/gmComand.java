package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gmComand implements CommandExecutor{
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if (label.equalsIgnoreCase("gm")) {
		Player player = (Player) sender;
		
		if (player.getGameMode() != GameMode.CREATIVE) {
			player.setGameMode(GameMode.CREATIVE);
		}else {
			player.setGameMode(GameMode.SURVIVAL);
		}
	}
	
		
		return false;	
	}

}
