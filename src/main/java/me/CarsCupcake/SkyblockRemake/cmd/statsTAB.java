package me.CarsCupcake.SkyblockRemake.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class statsTAB implements TabCompleter{
	List<String> arguments = new ArrayList<String>();
	List<String> stats = new ArrayList<String>();
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> result = new ArrayList<String>();
		if(stats.isEmpty()) {
			stats.add("health");
			stats.add("def");
			stats.add("mana");
			stats.add("speed");
			stats.add("strength");
			stats.add("critdamage");
			stats.add("critchance");
			stats.add("ferocity");
			stats.add("magicfind");
			stats.add("miningspeed");
			stats.add("miningfortune");
			stats.add("titaniumchance");
			stats.add("pristine");
			stats.add("attackspeed");
			stats.add("abilitydamage");
			stats.add("truedefense");
			stats.add("seacreaturechance");
			stats.add("fishingspeed");
			stats.add("TrophyFishChance");

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
