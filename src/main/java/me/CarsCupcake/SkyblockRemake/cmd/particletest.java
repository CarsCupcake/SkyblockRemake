package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import me.CarsCupcake.SkyblockRemake.Ferocity;

public class particletest implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg2.equalsIgnoreCase("part"))
		Ferocity.particles((Entity) arg0);
		return false;
	}

}
