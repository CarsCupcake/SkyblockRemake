package me.CarsCupcake.SkyblockRemake.cmd;

import java.util.Random;

import org.bukkit.Location;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Tools;

public class beaconThrow implements CommandExecutor{


	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		
		//Voidgloom beacon:
		
		if(!arg2.equalsIgnoreCase("beacon"))
			return false;
		Player player = (Player) arg0;
		ArmorStand block = player.getWorld().spawn(new Location(player.getLocation().getWorld(),player.getLocation().getBlockX(),player.getLocation().getBlockY() + 1,player.getLocation().getBlockZ()), ArmorStand.class);
		block.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/eb07594e2df273921a77c101d0bfdfa1115abed5b9b2029eb496ceba9bdbb4b3"));

block.setGravity(false);


		block.setInvisible(true);
		
		block.addScoreboardTag("eye_target:" + player.getName());
		Random rand = new Random();
		block.setVelocity(block.getVelocity().add(new Vector((rand.nextDouble()- 0.5),0.1,(rand.nextDouble() - 0.5))));

		
		
		
		System.out.println(block.getVelocity());
		return false;
	}

}
