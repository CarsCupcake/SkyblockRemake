package me.CarsCupcake.SkyblockRemake.cmd;



import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.privateIsle.PrivateIslandManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class testobjectCMD implements CommandExecutor{
	
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("testobject")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			PrivateIslandManager.createNewIsle(SkyblockPlayer.getSkyblockPlayer((Player) sender));


			return true;
			
		}
		
		return false;
	}

}
