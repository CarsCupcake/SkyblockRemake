package me.CarsCupcake.SkyblockRemake.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class lavabouncetoggletab implements TabCompleter{
	List<String> arguments = new ArrayList<String>();
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> result = new ArrayList<String>();
		
		
		if (args.length >= 1) {
			for (String a : arguments) {
				a = "";
				result.add(a);
			}
			return result;
		}
			
		
		
		return null;
	}

}
