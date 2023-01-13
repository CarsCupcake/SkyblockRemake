package me.CarsCupcake.SkyblockRemake.cmd;


import me.CarsCupcake.SkyblockRemake.Entities.DummyEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys.FirePillar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.Player;


public class testobjectCMD implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("testobject")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			DummyEntity entity = new DummyEntity(10, Blaze.class);
			entity.spawn(((Player) sender).getLocation());
			new FirePillar(entity, SkyblockPlayer.getSkyblockPlayer((Player) sender));


			return true;
			
		}
		
		return false;
	}

}
