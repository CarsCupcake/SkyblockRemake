package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;

import me.CarsCupcake.SkyblockRemake.soulentity.soulsheep;
import net.minecraft.server.level.WorldServer;

public class soulsheepSpawnCMD implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("soulsheep")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			Player player = (Player) sender;
			soulsheep sheep = new soulsheep(player.getLocation());
			WorldServer world = ((CraftWorld)player.getLocation().getWorld()).getHandle();
			world.addEntity(sheep);
			return true;
			
		}
		
		return false;
	}

}
