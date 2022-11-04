package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Pets.Pet;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class AddPetExCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player && arg3.length == 1) {
			double exp = 0;
			try {
				exp = Double.parseDouble(arg3[0]);
			} catch (Exception e) {
				
			}
			
			Pet.addPetXP(SkyblockPlayer.getSkyblockPlayer((Player)arg0), exp);
			
		}
		return false;
	}

}
