package me.CarsCupcake.SkyblockRemake.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import me.CarsCupcake.SkyblockRemake.Skills;

public class AddSkillXpTAB implements TabCompleter{
	List<String> arguments = new ArrayList<String>();
	List<String> stats = new ArrayList<String>();
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> result = new ArrayList<String>();
		if(stats.isEmpty()) {
			for(Skills skill : Skills.values())
				stats.add(skill.toString());
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
