package me.CarsCupcake.SkyblockRemake.cmd;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Stats;
import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;


public class statsCMD implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("stats")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			Player player = (Player) sender;
			if (args.length < 2) {
				player.sendMessage("Wrong args");
				return true;
			}
			try {
				Float.parseFloat(args[1]);
				
			}catch(Exception e) {
				player.sendMessage("Wrong Arg ussage");
				return true;
			}
			SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer(player);
			double value = Double.parseDouble(args[1]);
			CustomConfig statsConfig = new CustomConfig(p, "stats");
			switch(args[0]){
				 case "health":
					 player.sendMessage("Set Base Health to: " + args[1]);
					 p.setBaseStat(Stats.Health, value);

					statsConfig.get().set(player.getUniqueId().toString() + ".basehealth", Integer.parseInt(args[1]));
					statsConfig.save();
					statsConfig.reload();
					Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "mana":
					 player.sendMessage("Set Base Mana to: " + args[1]);
					 p.setBaseStat(Stats.Inteligence, value);
					 statsConfig.get().set(player.getUniqueId().toString() + ".basemana", Integer.parseInt(args[1]));
					 statsConfig.save();
						statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "def":
					 player.sendMessage("Set Base Defense to: " + args[1]);
					 p.setBaseStat(Stats.Defense, value);
					 statsConfig.get().set(player.getUniqueId().toString() + ".basedef", Integer.parseInt(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "speed":
					 player.sendMessage("Set Base Speed to: " + args[1]);
					 p.setBaseStat(Stats.Speed, value);
					 statsConfig.get().set(player.getUniqueId().toString() + ".basespeed", Integer.parseInt(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "strength":
					 player.sendMessage("Set Base Strenght to: " + args[1]);
					 p.setBaseStat(Stats.Strength, value);
					 statsConfig.get().set(player.getUniqueId().toString() + ".basestrength", Integer.parseInt(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "critchance":
					 player.sendMessage("Set Base CC to: " + args[1]);
					 p.setBaseStat(Stats.CritChance, value);
					 statsConfig.get().set(player.getUniqueId().toString() + ".basecc", Integer.parseInt(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "critdamage":
					 player.sendMessage("Set Base CD to: " + args[1]);
					 p.setBaseStat(Stats.CritDamage, value);
					 statsConfig.get().set(player.getUniqueId().toString() + ".basecd", Integer.parseInt(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "ferocity":
					 player.sendMessage("Set Base Ferocity to: " + args[1]);
					 p.setBaseStat(Stats.Ferocity, value);
					 statsConfig.get().set(player.getUniqueId().toString() + ".baseferocity", Integer.parseInt(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "magicfind":
					 player.sendMessage("Set Base Magic Find to: " + args[1]);
					 p.setBaseStat(Stats.MagicFind, value);
					 statsConfig.get().set(player.getUniqueId().toString() + ".basemagicfind", Integer.parseInt(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "miningspeed":
					 player.sendMessage("Set Base Mining Speed to: " + args[1]);
					 p.setBaseStat(Stats.MiningSpeed, value);
					 statsConfig.get().set(player.getUniqueId().toString() + ".baseminingspeed", Integer.parseInt(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "miningfortune":
					 player.sendMessage("Set Base Mining Speed to: " + args[1]);
					 p.setBaseStat(Stats.MiningFortune, value);
					 statsConfig.get().set(player.getUniqueId().toString() + ".baseminingfortune", Integer.parseInt(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "titaniumchance":
					 player.sendMessage("Set Titanium Chance to: " + args[1]);
					 p.setTitaniumChance(value);
					 statsConfig.get().set(player.getUniqueId().toString() + ".titaniumchance", Double.parseDouble(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "pristine":
					 player.sendMessage("Set Base Pristine to: " + args[1]);
					 p.setBaseStat(Stats.Pristine, value);
					 statsConfig.get().set(player.getUniqueId().toString() + ".pristine", Double.parseDouble(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "attackspeed":
					 player.sendMessage("Set Base Attack Speed to: " + args[1]);
					 p.setBaseStat(Stats.AttackSpeed, value);
					 statsConfig.get().set(player.getUniqueId() + ".attackspeed", Integer.parseInt(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				 case "truedefense":
					 player.sendMessage("Set Base True Defense to: " + args[1]);
					 p.setBaseStat(Stats.TrueDefense, value);
					 statsConfig.get().set(player.getUniqueId() + ".truedefense", Integer.parseInt(args[1]));
					 statsConfig.save();
					 statsConfig.reload();
					 Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					 return true;
				case "abilitydamage":
					player.sendMessage("Set Base Ability Damage to: " + args[1]);
					p.setBaseStat(Stats.AbilityDamage, value);
					statsConfig.get().set(player.getUniqueId().toString() + ".abilitydamage", Float.parseFloat(args[1]));
					statsConfig.save();
					statsConfig.reload();
					Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					return true;
				case "seacreaturechance":
					player.sendMessage("Set Base Sea Creature Chance to: " + args[1]);
					p.setBaseStat(Stats.SeaCreatureChance, value);
					statsConfig.get().set(player.getUniqueId() + ".seacreaturechance", Double.parseDouble(args[1]));
					statsConfig.save();
					statsConfig.reload();
					Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					return true;
				case "fishingspeed":
					player.sendMessage("Set Base Fishing Speed to: " + args[1]);
					p.setBaseStat(Stats.FishingSpeed, value);
					statsConfig.get().set(player.getUniqueId() + "." + Stats.FishingSpeed.getDataName(), Double.parseDouble(args[1]));
					statsConfig.save();
					statsConfig.reload();
					Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					return true;
				case "TrophyFishingChance":
					player.sendMessage("Set Base Trophy Fishing Chance to: " + args[1]);
					double d = Double.parseDouble(args[1]);
					d /= 100;
					d++;
					p.setBaseTrophyFishChance(d);
					statsConfig.get().set(player.getUniqueId() + "." + Stats.FishingSpeed.getDataName(), d);
					statsConfig.save();
					statsConfig.reload();
					Main.updatebar(SkyblockPlayer.getSkyblockPlayer(player));
					return true;
					
				
				default:
					player.sendMessage("Wrong Arg ussage");
					break;
			}
		
			
			return false;	
		}
		return false;
}}
