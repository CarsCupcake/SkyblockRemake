package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys.T2.BlazeSlayerT2;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys.T3.BlazeSlayerT3;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys.T4.BlazeSlayerT4;
import me.CarsCupcake.SkyblockRemake.Slayer.Enderman.EndermanT1;
import me.CarsCupcake.SkyblockRemake.Slayer.Enderman.EndermanT4;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys.T1.BlazeSlayerT1;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys.T1.QuaziiT1;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys.T1.TyphoesT1;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class startslayer implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("slayer")) {

			if (!(sender instanceof Player player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			if (args.length < 2) {
				player.sendMessage("Wrong args");
				return true;
			}

			switch (args[0]) {
				case "zombie" -> player.sendMessage("§cTemporarily not available.");
				case "blaze" -> {
					if (args[1].equals("1")) {
						BlazeSlayerT1 slayer = new BlazeSlayerT1();
						slayer.setOwner(SkyblockPlayer.getSkyblockPlayer(player));
						slayer.spawn(player.getLocation());
					}
					if (args[1].equals("2")) {
						BlazeSlayerT2 slayer = new BlazeSlayerT2();
						slayer.setOwner(SkyblockPlayer.getSkyblockPlayer(player));
						slayer.spawn(player.getLocation());
					}
					if (args[1].equals("3")) {
						BlazeSlayerT3 slayer = new BlazeSlayerT3();
						slayer.setOwner(SkyblockPlayer.getSkyblockPlayer(player));
						slayer.spawn(player.getLocation());
					}
					if (args[1].equals("4")) {
						BlazeSlayerT4 slayer = new BlazeSlayerT4();
						slayer.setOwner(SkyblockPlayer.getSkyblockPlayer(player));
						slayer.spawn(player.getLocation());
					}
				}
				case "enderman" -> {
					if (args[1].equals("1")) {
						EndermanT1 slayer = new EndermanT1(SkyblockPlayer.getSkyblockPlayer(player));
						Slayer.summonSlayer(player.getLocation(), slayer);
					}
					if (args[1].equals("4")) {
						EndermanT4 slayer = new EndermanT4(SkyblockPlayer.getSkyblockPlayer(player));
						Slayer.summonSlayer(player.getLocation(), slayer);
					}
				}
			}
			}
		
		
		return false;

}
	}
