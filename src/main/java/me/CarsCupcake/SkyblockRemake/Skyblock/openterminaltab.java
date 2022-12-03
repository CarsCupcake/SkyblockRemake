package me.CarsCupcake.SkyblockRemake.Skyblock;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;


public class openterminaltab implements TabCompleter{
		
		List<String> arguments = new ArrayList<>();
			
			public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
				
				List<String> result = new ArrayList<>();
				if(arguments.isEmpty()) {
				arguments.add("maze");
				arguments.add("color");
				arguments.add("name");
					arguments.add("order");
					arguments.add("panes");
					arguments.add("lockinslot");
					arguments.add("text");
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

