package me.CarsCupcake.SkyblockRemake;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Items.SpawnEggEntitys;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockScoreboard;

public class Ferocity {
	public static void particles(Entity entity) {
	
		final Particle.DustOptions dust = new Particle.DustOptions(
                Color.fromRGB((int) 255, (int) 0, (int) 0), 1);
		Location manage = entity.getLocation();
		
		Vector a = null;
		Vector b = null;
		Location start;
		if(manage.getYaw() >= -45 && manage.getYaw() <= 45) {
			 start = manage.add(-1,0.5,0);
			a = start.toVector();
				 b =  start.add(2, 1.5, 0).toVector();
		}else {
			if(manage.getYaw() >= 135 || manage.getYaw() <= -135) {
				start = manage.add(1,0.5,0);
				a = start.toVector();
					 b =  start.add(-2, 1.5, 0).toVector();
			}else {
				if(manage.getYaw() >= 45 && manage.getYaw() <= 135) {
					start = manage.add(0,0.5,-1);
					a = start.toVector();
						 b =  start.add(0, 1.5, 2).toVector();
			}else {
				start = manage.add(0,0.5,1);
				a = start.toVector();
					 b =  start.add(0, 1.5, -2).toVector();
			}
		}
		}
		
		
				
				 
				Vector between = b.subtract(a); //get the vector between two points
				double length = between.length(); //get the length of the distance
				between.normalize().multiply(0.3); //normalize our vector and specify the spacing
				double steps = (double)((double)length / 0.3); //determine number of steps
				
				for (int i = 0; i < steps; i++) { //iterate up to but not beyond point b
				    Vector point = a.add(between);
				    entity.getLocation().getWorld().spawnParticle(Particle.REDSTONE, point.getX(), point.getY(), point.getZ(),1, dust);
				}
                            

        

	}
	public static void playSound(Location loc) {
		loc.getWorld().playSound(loc, Sound.ITEM_FLINTANDSTEEL_USE, 1.5f, 0);
		
		loc.getWorld().playSound(loc, Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.2f, 2f);
	}

public static void hit(LivingEntity e, int damage, boolean crit, Player player) {
	if(e == null)
		return;
	particles(e);
	playSound(e.getEyeLocation());
	if(Main.currentityhealth.get(e) == null && !SkyblockEntity.livingEntity.containsKey(e))
		return;


	Calculator calculator = new Calculator(true);
	calculator.damage = damage;
	calculator.isCrit = crit;
	calculator.damageEntity(e, SkyblockPlayer.getSkyblockPlayer(player));
	calculator.showDamageTag(e);

/*	if(!Main.voidgloomHitphase.containsKey(e) || !Main.voidgloomHitphase.get(e)) {
	if(SkyblockEntity.livingEntity.containsKey(e)) {
		SkyblockEntity.livingEntity.get(e).damage(damage,SkyblockPlayer.getSkyblockPlayer(player));
	}else {
	int live = Main.currentityhealth.get(e) - (int) damage;
	Main.currentityhealth.replace(e, live);
	}
	}else {
		 Main.voidgloomHitphaseHits.replace(e, Main.voidgloomHitphaseHits.get(e) - 1);
		 
	}
	
	if((Main.currentityhealth.get(e)  != null && Main.currentityhealth.get(e) <= 0) || (SkyblockEntity.livingEntity.get(e) != null && SkyblockEntity.livingEntity.get(e).getHealth() <= 0)) {
		e.addScoreboardTag("killer:" + player.getName());
		e.damage(999999999,player);
		if(e.getScoreboardTags() != null) {
			Set<String> scores = e.getScoreboardTags();
			ArrayList<Player> owners = new ArrayList<>();
			scores.forEach(tag ->{
				
				if(tag.startsWith("combatxp:")) {

					if(Main.SlayerCurrXp.containsKey(player) == true && Main.SlayerName.containsKey(player) == true && Main.SlayerName.get(player).equals("Revenant Horror") && e.getType() == EntityType.ZOMBIE) {
						Main.SlayerCurrXp.replace(player, Main.SlayerCurrXp.get(player) + Integer.parseInt(tag.split(":")[1]));
						SkyblockScoreboard.updateScoreboard(player);
						 Random r = new Random();
						  int low = 0;//includes 1
						  int high = 100;// includes 100
						  int result = r.nextInt(high-low) + low;
						  if(result <= 15) {
							  if(Main.SlayerLevel.get(player) == 4) {
							 low = 1;
							 high = 5;
							 result = r.nextInt(high-low) + low;
							 if(result == 5) {
								 SpawnEggEntitys.SummonRevT4MiniBoss2(e.getLocation());
							 }else {
								 SpawnEggEntitys.SummonRevT4MiniBoss1(e.getLocation());
							 }
							 }else {
								 SpawnEggEntitys.SummonRevT3MiniBoss1(e.getLocation());
							 }
						  }
							
					if(Main.SlayerCurrXp.get(player) >= Main.SlayerRequireXp.get(player)) {
					Main.SlayerCurrXp.remove(player);
					BukkitRunnable runnable =new BukkitRunnable() {
						@Override
						public void run() {
							System.out.println("runnn");
						if(Main.SlayerLevel.get(player) == 1)
							SpawnEggEntitys.SummonT1Rev(e.getLocation(), player.getName());
						if(Main.SlayerLevel.get(player) == 2)
							SpawnEggEntitys.SummonT2Rev(e.getLocation(), player.getName());
						if(Main.SlayerLevel.get(player) == 3)
							SpawnEggEntitys.SummonT3Rev(e.getLocation(), player.getName());
						if(Main.SlayerLevel.get(player) == 4)
							SpawnEggEntitys.SummonT4Rev(e.getLocation(), player.getName());
						
						}
						};runnable.runTaskLater(Main.getMain(), 2*20);
					
						
					}
					
					}
				}
				if(tag.startsWith("revslayer")) {
					
					scores.forEach(tags ->{
						if(tags.startsWith("owner")) {
							Player owner = Bukkit.getServer().getPlayer(tags.split(":")[1]);
							owner.sendMessage("Your Rev slayer has ben killed");
							owners.add(owner);
							if(Main.SlayerName.containsKey(owner)) {
							Main.SlayerName.remove(owner);
							Main.SlayerLevel.remove(owner);
							Main.SlayerRequireXp.remove(owner);}
							SkyblockScoreboard.updateScoreboard(player);
						}
					});
				}
				if(tag.startsWith("voidgloomt2")) {
					if( Main.beaconPicketUp.containsKey(e) && Main.beaconPicketUp.get(e) == false) {
					if(Main.beaconBeforeBlock.get(Main.beaconLocation.get(e)) != null)
					Main.beaconLocation.get(e).getBlock().setType(Main.beaconBeforeBlock.get(Main.beaconLocation.get(e)).getType());
					else
					Main.beaconLocation.get(e).getBlock().setType(Material.AIR);
					}
					if(Main.beaconThrown.containsKey(e) && Main.beaconThrown.get(e) == true)
						SkyblockRemakeEvents.kill_voidgloom_beacon(e);
					Main.beaconBeforeBlock.remove(Main.beaconLocation.get(e));
					Main.beaconLocation.remove(e);
					Main.beaconOnGround.remove(e);
					Main.beaconOwner.remove(player);
					Main.beaconPicketUp.remove(e);
					Main.beaconThrown.remove(e);
					
					
				}
			});
			if(owners != null) {
				owners.forEach(owner->{
					e.addScoreboardTag("killer:" + owner.getName());
				});
			}
		}
	}
		

Main.updateentitystats(e);
Location loc = new Location(e.getWorld(), e.getLocation().getX() ,e.getLocation().getY() + 0.5 , e.getLocation().getZ());
ArmorStand stand = (ArmorStand) e.getWorld().spawn(loc, ArmorStand.class, armorstand ->{armorstand.setVisible(false);
armorstand.setGravity(false);
armorstand.setMarker(true);
   
armorstand.setCustomNameVisible(true);

armorstand.setInvulnerable(true);
if(crit) {
	String name = "§f✧";
	String num = "" + (int) damage;
	int col =1;
	int coltype = 1;
	String colstr = "§f";
	
	for (char x : num.toCharArray()) {
		name = name + colstr + x;
		++col;
		if(col ==2) {
			col = 0;
			++coltype;
			switch(coltype) {
			case 1:
				colstr = "§f";
				break;
			case 2:
				colstr = "§e";
				break;
			case 3:
				colstr = "§6";
				coltype = 0;
				break;
				
			
			
		}
	}
}
	armorstand.setCustomName(name);
}else
	armorstand.setCustomName("§7" + (int)damage);
armorstand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
armorstand.addScoreboardTag("damage_tag");
armorstand.setArms(false);
armorstand.setBasePlate(false);});

Main.getMain().killarmorstand(stand);
e.setCustomNameVisible(true);*/
}


public static void playerhit(Player player, int totaldmg, boolean crit, EntityDamageByEntityEvent event) {
	
	playSound(player.getLocation());
	particles(player);
	SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer(player);
	
	if(Main.absorbtion.get(player) - totaldmg  < 0) {
		float restdamage =   (float)totaldmg - (float) Main.absorbtion.get(player);
		Main.absorbtion.replace(player, 0);
		p.setHealth( p.currhealth - (int)restdamage);
	}else {
		Main.absorbtion.replace(player, Main.absorbtion.get(player) - totaldmg);
	}
	
	if(p.currhealth <= 0) {
		player.setHealth(0);
		Main.deathPersons.add(player);
	}else
	Main.updatebar(p);
	
	final int FINAL_DAMAGE = totaldmg;
	Location loc = new Location(event.getEntity().getWorld(), event.getEntity().getLocation().getX() ,event.getEntity().getLocation().getY() + 0.5 , event.getEntity().getLocation().getZ());
	
	ArmorStand stand = (ArmorStand) event.getEntity().getWorld().spawn(loc, ArmorStand.class, armorstand ->{
		armorstand.setVisible(false);
	
		armorstand.setGravity(false);
		armorstand.setMarker(true);
		
	  
		armorstand.setCustomNameVisible(true);
	
		armorstand.setInvulnerable(true);
	if(crit) {
		String name = "§f✧";
		String num = "" + (int) FINAL_DAMAGE;
		int col =1;
		int coltype = 1;
		String colstr = "§f";
		
		for (char x : num.toCharArray()) {
			name = name + colstr + x;
			++col;
			if(col ==2) {
				col = 0;
				++coltype;
				switch(coltype) {
				case 1:
					colstr = "§f";
					break;
				case 2:
					colstr = "§e";
					break;
				case 3:
					colstr = "§6";
					coltype = 0;
					break;
					
				}
				
			}
		}
		
		armorstand.setCustomName(name);
	}else
		armorstand.setCustomName("§7" + (int)FINAL_DAMAGE);
	armorstand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
	armorstand.addScoreboardTag("damage_tag");
	armorstand.setArms(false);
	
	armorstand.setBasePlate(false);});
	
	Main.getMain().killarmorstand(stand);
	
	
	
}




}
