package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer;
import me.CarsCupcake.SkyblockRemake.Slayer.Enderman.EndermanT1;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Entitys.BlazeSlayerT1;
import me.CarsCupcake.SkyblockRemake.Entitys.QuaziiT1;
import me.CarsCupcake.SkyblockRemake.Entitys.TyphoesT1;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockScoreboard;

public class startslayer implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("slayer")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			Player player = (Player) sender;
			if (args.length < 2) {
				player.sendMessage("Wrong args");
				return true;
			}
			
			switch(args[0]){
			case "zombie":
				Main.SlayerName.put(player, "Revenant Horror");
				if(args[1].equals("1")) {
					Main.SlayerLevel.put(player, 1);
					Main.SlayerCurrXp.put(player, 0);
					Main.SlayerRequireXp.put(player, 150);
					SkyblockScoreboard.updateScoreboard(player);
					}
				if(args[1].equals("2")) {
					Main.SlayerLevel.put(player, 2);
					Main.SlayerCurrXp.put(player, 0);
					Main.SlayerRequireXp.put(player, 1440);
					SkyblockScoreboard.updateScoreboard(player);
					}
				if(args[1].equals("3")) {
					Main.SlayerLevel.put(player, 3);
					Main.SlayerCurrXp.put(player, 0);
					Main.SlayerRequireXp.put(player, 2400);
					SkyblockScoreboard.updateScoreboard(player);
					}
				if(args[1].equals("4")) {
					Main.SlayerLevel.put(player, 4);
					Main.SlayerCurrXp.put(player, 0);
					Main.SlayerRequireXp.put(player, 4800);
					SkyblockScoreboard.updateScoreboard(player);
					}
				break;
				case "blaze":
					if(args[1].equals("1")) {
						BlazeSlayerT1 slayer = new BlazeSlayerT1();
						slayer.setOwner(SkyblockPlayer.getSkyblockPlayer(player));
						slayer.spawn(player.getLocation());
					}
					if(args[1].equals("quazii")) {
						QuaziiT1 entity = new QuaziiT1();
						entity.spawn(player.getLocation());
						entity.startAttack(SkyblockPlayer.getSkyblockPlayer(player));
					}
					if(args[1].equals("typhoeus")) {
						TyphoesT1 entity = new TyphoesT1();
						entity.spawn(player.getLocation());
						entity.startAttack(SkyblockPlayer.getSkyblockPlayer(player));
					}

					break;
				case "enderman":
					if(args[1].equals("1")) {
						EndermanT1 slayer = new EndermanT1(SkyblockPlayer.getSkyblockPlayer(player));
						Slayer.summonSlayer(player.getLocation(), slayer);
					}

					break;
				
				
			}
			}
		
		
		return false;

}
	}
