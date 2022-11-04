package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Skills;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class AddSkillXpCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player && arg3.length == 2) {
			
			double amount = 0;
			try {
				amount = Double.parseDouble(arg3[1]);
			}catch (Exception e) {
				arg0.sendMessage("This is not a valid number");
				return true;
			}
			
			if(Skills.valueOf(arg3[0]) == null)
			{
				arg0.sendMessage("This is not a valid skill");
				return true;
			}
			
			SkyblockPlayer.getSkyblockPlayer((Player)arg0).addSkillXp(amount, Skills.valueOf(arg3[0]));
			
		}
		return false;
	}

}
