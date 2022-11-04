package me.CarsCupcake.SkyblockRemake.soulentity;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;

import net.minecraft.world.entity.animal.EntitySheep;



public class soulsheep extends EntitySheep{
	


	

	public soulsheep(Location loc) {
		super(EntityTypes.ax, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		this.setHealth(300);
		this.addScoreboardTag("SoulSheep");
		this.setCustomNameVisible(true);
		this.setCustomName(new ChatComponentText("§lSoul Sheep§r"));
		
	}
	
	public void initPathfinder() {
		super.initPathfinder();
		
	}

	

	

	

}
