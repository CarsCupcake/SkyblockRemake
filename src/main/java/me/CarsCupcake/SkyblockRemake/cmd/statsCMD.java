package me.CarsCupcake.SkyblockRemake.cmd;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;


public class statsCMD implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("stats")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			Player player = (Player) sender;
			if (args.length < 2) {
				player.sendMessage("Wrong args");
				return false;
			}
			try {
				Double.parseDouble(args[1]);
				
			}catch(Exception e) {
				player.sendMessage("Wrong Arg ussage");
				return false;
			}
			SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer(player);
			double value = Double.parseDouble(args[1]);
			CustomConfig statsConfig = new CustomConfig(p, "stats");
			Stats s = Stats.getFromDataName(args[0]);
			if(s == null) return false;
			player.sendMessage("Set Base "+s.getName()+" to: " + args[1]);
			p.setBaseStat(s, value);
			statsConfig.get().set(s.getDataName(), Double.parseDouble(args[1]));
			statsConfig.save();
			Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
			return true;
		}
		return false;
}}
