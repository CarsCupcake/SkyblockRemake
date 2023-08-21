package me.CarsCupcake.SkyblockRemake.FishingSystem;

import java.util.Random;



import org.bukkit.Location;
import org.bukkit.Material;

import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.CarsCupcake.SkyblockRemake.Main;


public class SeaCreatures {
public static Entity  roolNormalFishingDice(Location loc, Player player){

	Random r = new Random();
	  int low = 0;//includes 1
	  int high = 12369;// includes 100
	  int impotand = r.nextInt(high-low) + low;
	  if(impotand <= 2456) {
		  //Squid
		 
		  return Squid(loc);
	  }
	  impotand -= 2456;
	  int  chance;
	  chance = 1631;
	  if(impotand <= chance) {
		  return SeaWalker(loc);
	  }
	  impotand -= chance;
	  
	  chance = 1783;
	  if(impotand <= chance) {
		  return NightSquid(loc);
	  }
	  impotand -= chance;
	  
	  chance = 1223;
	  if(impotand <= chance) {
		  return SeaGuardian(loc);
	  }
	  impotand -= chance;
	  
	  chance = 1427;
	  if(impotand <= chance) {
		  return SeaWitch(loc);
	  }
	  impotand -= chance;
	  
	  chance = 1121;
	  if(impotand <= chance) {
		  return SeaArcher(loc);
	  }
	  impotand -= chance;
	  
	  chance = 815;
	  if(impotand <= chance) {
		  return RiderOfTheDeep(loc);
	  }
	  impotand -= chance;
	  
	  chance = 510;
	  if(impotand <= chance) {
		  return Catfish(loc, player);
	  }
	  impotand -= chance;
	  
	  chance = 576;
	  if(impotand <= chance) {
		  //Carrot King
	  }
	  impotand -= chance;
	  
	  chance = 326;
	  if(impotand <= chance) {
		  //Sea Leech
	  }
	  impotand -= chance;
	  
	  chance = 265;
	  if(impotand <= chance) {
		  //Guardian Defender
	  }
	  impotand -= chance;
	  
	  chance = 179;
	  if(impotand <= chance) {
		  //Deep Sea Protector
	  }
	  impotand -= chance;
	  
	  chance = 37;
	  if(impotand <= chance) {
		  //Water Hydra
	  }
	  impotand -= chance;
	  
	  chance = 20;
	  if(impotand <= chance) {
		  //The Sea Emperor
	  }
	  return Squid(loc);
	  
}

public static Entity Squid(Location loc) {
	Entity entity = loc.getWorld().spawn(loc, org.bukkit.entity.Squid.class, s->{
		s.addScoreboardTag("CustomName:Squid");s.setCustomNameVisible(true);
	});
	return entity;
	}
public static Entity NightSquid(Location loc) {
	Entity entity = loc.getWorld().spawn(loc, org.bukkit.entity.Squid.class, s->{
		s.addScoreboardTag("CustomName:Night Squid");s.setCustomNameVisible(true);
	});
	return entity;
	}
public static Entity SeaWalker(Location loc) {
	@SuppressWarnings("deprecation")
	Entity entity = loc.getWorld().spawn(loc, org.bukkit.entity.Zombie.class, s->{
		s.addScoreboardTag("CustomName:Sea Walker");
		s.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
		s.setCustomNameVisible(true);
		
	});
	return entity;
}

public static Entity SeaGuardian(Location loc) {

	Entity entity = loc.getWorld().spawn(loc, org.bukkit.entity.Guardian.class, s->{
		s.addScoreboardTag("CustomName:Sea Guardian");
		s.setCustomNameVisible(true);
		
		
	});
	return entity;
}

public static Entity SeaWitch(Location loc) {

	Entity entity = loc.getWorld().spawn(loc, org.bukkit.entity.Witch.class, s->{
		s.addScoreboardTag("CustomName:Sea Witch");
		s.setCustomNameVisible(true);
		
		
	});
	return entity;
}

public static Entity SeaArcher(Location loc) {

	Entity entity = loc.getWorld().spawn(loc, org.bukkit.entity.Skeleton.class, s->{
		s.addScoreboardTag("CustomName:Sea Archer");
		s.setCustomNameVisible(true);
		
		
	});
	return entity;
}

public static Entity RiderOfTheDeep(Location loc) {

	Entity entity = loc.getWorld().spawn(loc, org.bukkit.entity.Zombie.class, s->{
		s.setBaby();
		s.setCustomNameVisible(true);
		s.addScoreboardTag("CustomName:Rider Of The Deep");
	});
	@SuppressWarnings("deprecation")
	Chicken ch =  loc.getWorld().spawn(loc, org.bukkit.entity.Chicken.class, s->{
		
		s.addScoreboardTag("invinc");
		s.setPassenger(entity);
	});
	entity.addScoreboardTag("rider:" + ch.getUniqueId());
	
	return ch;
}

public static Entity Catfish(Location loc, Player player) {

	
	
	Entity entity = loc.getWorld().spawn(loc, org.bukkit.entity.Ocelot.class, s->{
		s.addScoreboardTag("CustomName:Catfish");
		s.attack(player);
		s.setCustomNameVisible(true);
		
	});
	
	
	return entity;
}

}
