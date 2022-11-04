package me.CarsCupcake.SkyblockRemake.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CoinsTAB implements TabCompleter{

	List<String> operator = new ArrayList<String>();
public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> result = new ArrayList<String>();
		if(operator.isEmpty()) {
			operator.add("add");
			operator.add("reset");
			operator.add("remove");
			operator.add("set");
			
			}
		
		if (args.length == 1) {
			for (String a : operator) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase()))
					result.add(a);
			}
			return result;
		}
		
		if (args.length >= 2 ) {
			
			result.add("");
			return result;
		}
			
		
		
		return null;
	}
}
