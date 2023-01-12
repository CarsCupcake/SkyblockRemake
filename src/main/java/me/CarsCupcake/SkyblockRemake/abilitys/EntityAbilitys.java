package me.CarsCupcake.SkyblockRemake.abilitys;

import java.util.Random;

import org.bukkit.Location;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;

public class EntityAbilitys {
	public static void voidgloomTeleport(Enderman creeper, Player owner) {
        voidgloomTeleport(creeper, owner, 0);
    }

	public static void voidgloomTeleport(Enderman creeper, Player owner, int i) {
		if(i >= 20)
			return;

		Random random = new Random();

		int x = owner.getLocation().getBlockX();
		int y = owner.getLocation().getBlockY();
		int z = owner.getLocation().getBlockZ();

		int newX = x + random.nextInt(4 - (-4));
		int newZ = z + random.nextInt(4 - (-4));

		int newY = getNextHighesBlockY(x,y,z,owner.getWorld());
		if (newY > 0) {
			if (newY <= y + 4 || newY >= y - 4) {
				Location newLoc = new Location(creeper.getWorld(), newX, newY, newZ);
				creeper.getWorld().playSound(creeper.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 4f, 1f);
				creeper.teleport(newLoc);
			} else {
				voidgloomTeleport(creeper, owner, i + 1);
			}
		} else {
			voidgloomTeleport(creeper, owner, i + 1);
		}
	}

	
	public static int getNextHighesBlockY(int x,int y, int z, World world) {
		int newY;
		
		Location loc = new Location(world, x, y, z);
		
		for(newY = y; newY < y + 6; newY++) {
			loc.setY(newY);
			if(loc.getBlock().isPassable()) {
				int blockY;
				for(blockY = newY; blockY > newY - 6; blockY--) {
					loc.setY(blockY);
					if(!loc.getBlock().isPassable()) {
						return blockY+1;
					}
				}
				
			}
			
		}
		return -1;
		
	}
	
}
