package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockScoreboard;

public class CoinsCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg2.equalsIgnoreCase("coins")) {
			if(!(arg0 instanceof Player)) return false;
			if(arg3.length != 2 && !arg3[0].equals("reset")) return false;
			if(!arg3[0].equals("reset"))
		 	 try {
				Double.parseDouble(arg3[1]);
			 }catch (Exception e) {
				arg0.sendMessage("Wrong Args");
				return false;
			 }
			SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player)arg0) ;

			switch (arg3[0]) {
				case "add" -> {
					player.setCoins(player.coins + Tools.round(Double.parseDouble(arg3[1]), 1));
					player.sendMessage("Succesfully added you §6" + arg3[1] + " Coins");
					SkyblockScoreboard.updateScoreboard(player);
					return true;
				}
				case "reset" -> {
					player.setCoins(0);
					player.sendMessage("Succesfully resetet your §6Coins");
					SkyblockScoreboard.updateScoreboard(player);
					return true;
				}
				case "remove" -> {
					player.setCoins(player.coins - Tools.round(Double.parseDouble(arg3[1]), 1));
					player.sendMessage("Succesfully removed you §6" + arg3[1] + " Coins");
					SkyblockScoreboard.updateScoreboard(player);
					return true;
				}
				case "set" -> {
					player.setCoins(Tools.round(Double.parseDouble(arg3[1]), 1));
					player.sendMessage("Succesfully set your §6Coins §fto §6" + arg3[1]);
					SkyblockScoreboard.updateScoreboard(player);
					return true;
				}
				default -> player.sendMessage("Wrong operator");
			}
			
			
		}
		return false;
	}

}
