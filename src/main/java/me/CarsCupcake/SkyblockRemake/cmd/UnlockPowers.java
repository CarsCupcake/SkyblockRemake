package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.AccessoryBag.Powers.Powers;


public class UnlockPowers implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player && arg3.length == 1 && arg2.equalsIgnoreCase("unlockpowers")) {
			
			
			Player player = (Player)arg0;
			
			switch (arg3[0]) {
			case "all":
				Powers.powers.values().forEach(power->{
					Powers pow = power;
					pow.addObitained(player);
				});
				break;
				default:
					if(Powers.powers.containsKey(arg3[0])) {
						Powers.powers.get(arg3[0]).addObitained(player);
					}
			}	
			
			return true;
		}
		return false;
	}

}
