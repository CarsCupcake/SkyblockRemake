package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.CarsCupcake.SkyblockRemake.KuudraBossFight.CanonObject;

public class startKuudra implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg2.equalsIgnoreCase("kuudra")) {
			if(arg0.getServer().getPort() != 25569) return false;
			new CanonObject(new Location(Bukkit.getWorld("world"), -90.5, 41, -89.5));
			new CanonObject(new Location(Bukkit.getWorld("world"), -101.5, 41, -86.5));
		}
		return false;
	}

}
