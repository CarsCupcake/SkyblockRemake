package me.CarsCupcake.SkyblockRemake.cmd.impl.test;


import me.CarsCupcake.SkyblockRemake.utils.Laser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Main;


public class laser implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(!arg2.equalsIgnoreCase("laser"))
			return false;
		Player player = (Player) arg0;

		new Laser(player.getLocation(),player.getLocation().add(0,0,5));

		return false;
	}

}
