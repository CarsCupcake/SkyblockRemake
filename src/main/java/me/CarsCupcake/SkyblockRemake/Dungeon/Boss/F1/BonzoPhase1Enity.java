package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F1;

import org.bukkit.Location;




import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalAvoidTarget;
import net.minecraft.world.entity.ai.goal.PathfinderGoalPanic;
import net.minecraft.world.entity.npc.EntityVillager;
import net.minecraft.world.level.World;

public class BonzoPhase1Enity extends EntityVillager {

	public BonzoPhase1Enity(Location loc,World world) {
		super(EntityTypes.aV, world);
		this.setHealth(20);
		this.setCustomName(new ChatComponentText("Bonzo"));
		this.setCustomNameVisible(true);
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		
	}
@Override
	public void initPathfinder() {
	this.bP.a(0,new PathfinderGoalAvoidTarget<EntityPlayer>(this, EntityPlayer.class, 45,1D,1D));

	
		}

}
