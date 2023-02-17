package me.CarsCupcake.SkyblockRemake.cmd;



import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Boss.F7.F7Phase3;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class testobjectCMD implements CommandExecutor{
	
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("testobject")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			F7Phase3.regenerate();


			return true;
			
		}
		
		return false;
	}

}
