package me.CarsCupcake.SkyblockRemake.cmd;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Laser.GuardianLaser;


public class laser implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(!arg2.equalsIgnoreCase("laser"))
			return false;
		Player player = (Player) arg0;
		
			try {
				new GuardianLaser(player.getLocation(),player.getLocation().add(0,0,5), 5, -1).start(Main.getMain());
				
			} catch (ReflectiveOperationException e) {
				e.printStackTrace();
			}
		
		
		return false;
	}

}
