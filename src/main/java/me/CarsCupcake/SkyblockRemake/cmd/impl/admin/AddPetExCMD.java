package me.CarsCupcake.SkyblockRemake.cmd.impl.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.jetbrains.annotations.NotNull;

public class AddPetExCMD implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender arg0, @NotNull Command arg1, @NotNull String arg2, String[] arg3) {
		if(arg0 instanceof Player player && arg3.length == 1) {
			double exp = 0;
			try {
				exp = Double.parseDouble(arg3[0]);
			} catch (Exception ignored) {
				
			}
			SkyblockPlayer.getSkyblockPlayer(player).getPetEquip().addPetXP(exp);
			
		}
		return false;
	}

}
