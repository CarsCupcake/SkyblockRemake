package me.CarsCupcake.SkyblockRemake.Skyblock;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;


	public class openterminaltab implements TabCompleter{
		
		List<String> arguments = new ArrayList<String>();
			
			public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
				
				List<String> result = new ArrayList<String>();
				if(arguments.isEmpty()) {
				arguments.add("maze");
				arguments.add("color");
				arguments.add("name");
				arguments.add("order");
				}
				
				
				
				if (args.length == 1) {
					for (String a : arguments) {
						if (a.toLowerCase().startsWith(args[0].toLowerCase()))
							result.add(a);
					}
					return result;
				}
				if (args.length > 1) {



							result.add("");
					
					return result;
				}
				
					
				
				
				return null;
			}

		}

