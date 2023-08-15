package me.CarsCupcake.SkyblockRemake.cmd;

import java.util.ArrayList;

import java.util.List;

import org.bukkit.command.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import me.CarsCupcake.SkyblockRemake.Items.reforges.RegisteredReforges;
import org.jetbrains.annotations.NotNull;


public class AddReforgeTAB implements  TabCompleter{
	
List<String> arguments = new ArrayList<>();
	
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		
		List<String> result = new ArrayList<>();
		if(arguments.isEmpty()) {
			arguments.addAll(RegisteredReforges.reforges.keySet());
		arguments.add("remove");
		}
		
		if(args.length == 1) {
			for (String a : arguments) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase()))
					result.add(a);
			}
			return result;
		}
		
		if (args.length >= 2) {
			for (String ignored : arguments) {
				result.add("");
			}
			return result;
		}
			
		
		
		return null;
	}

}
