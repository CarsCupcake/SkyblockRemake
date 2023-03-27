package me.CarsCupcake.SkyblockRemake.cmd;

import java.util.ArrayList;
import java.util.List;

import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

public class statsTAB implements TabCompleter{
	List<String> arguments = new ArrayList<>();
	List<String> stats = new ArrayList<>();
	
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		
		List<String> result = new ArrayList<>();
		if(stats.isEmpty()) {
			for (Stats s : Stats.values())
				stats.add(s.getDataName());

			}
		if (args.length == 1) {
			for (String a : stats) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase()))
					result.add(a);
			}
			return result;
		}
		if (args.length > 1) {
			for (String a : arguments) {
				a = "";
				result.add(a);
			}
			return result;
		}
			
		
		
		return null;
	}

}
