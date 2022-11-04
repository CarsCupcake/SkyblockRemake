package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.NPC.NPC;



public class npcCommand implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("npc")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			Player player = (Player) sender;
			if (player.hasPermission("npc.plaze")) {
            if (args.length == 0) {
            player.sendMessage("Der NPC: NPC; Wurde erstellt ");
            String name = "NPC";
			NPC.createNPC(player, name);
			return true;
            }
            if (args[0].length() > 16) {
            	player.sendMessage("Name: " + args[0] + " ist zulange");
            	
            	return true;
            }else { 

            try {
            NPC.createNPC(player, args[0]);
            }
            catch(Exception e){
            	NPC.createNPC(player, args[0]);
            }
            player.sendMessage("Der NPC: " + args[0] +" wurde erstellt mit dem skin " );
            return true;
			}
            }else {
			
			player.sendMessage("Du must server Operator sein um NPC's zu Plazen");
			return true;
		}}
		
		
		return false;
	}
}
