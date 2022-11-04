package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.CarsCupcake.SkyblockRemake.DwarvenEvents.DwarvenEvent;
import me.CarsCupcake.SkyblockRemake.DwarvenEvents.DwarvenEvents;

public class StartEventCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg2.equalsIgnoreCase("event")) {
			
			new DwarvenEvent(DwarvenEvents.GoneWithTheWind);
			
			return true;
		}
	return false;	
	}

}
