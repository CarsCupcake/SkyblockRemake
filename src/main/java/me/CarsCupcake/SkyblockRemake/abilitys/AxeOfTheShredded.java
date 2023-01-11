package me.CarsCupcake.SkyblockRemake.abilitys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import me.CarsCupcake.SkyblockRemake.Ferocity;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.AdditionalManaCosts;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class AxeOfTheShredded extends ExtraDamageAbility implements AbilityManager<PlayerInteractEvent> {
private static final String itemID = "AXE_OF_THE_SHREDDED";
	@Override
	public boolean executeAbility(PlayerInteractEvent event) {
		int multi;
		SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
		if(!AbilityManager.additionalMana.get(player).containsKey(itemID)) {
			AbilityManager.additionalMana.get(player).put(itemID, new AdditionalManaCosts(player, itemID, 20, 60, -1));

			multi = 1;
		}else {
			switch (AbilityManager.additionalMana.get(player).get(itemID).amount) {
				case 20 -> {
					AbilityManager.additionalMana.get(player).get(itemID).addAmount(20);
					multi = 2;
				}
				case 40 -> {
					AbilityManager.additionalMana.get(player).get(itemID).addAmount(20);
					multi = 4;
				}
				case 60 -> {
					AbilityManager.additionalMana.get(player).get(itemID).addAmount(80);
					multi = 8;
				}
				default -> {
					AbilityManager.additionalMana.get(player).get(itemID).resetTimer();
					multi = 16;
				}
			}
		}
		thrower(player, multi);
		return false;
	}
	
	private void thrower(final SkyblockPlayer p, int multi) {
		 Location throwLoc = p.getLocation().add(0, 1.2, 0);
	        Vector throwVec = p.getLocation().add(p.getLocation().getDirection().multiply(10)).toVector().subtract(p.getLocation().toVector()).normalize().multiply(1.2D);

		ArmorStand armorStand =  p.getWorld().spawn(throwLoc, ArmorStand.class, stand ->{

				stand.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_AXE));
				stand.setInvulnerable(true);
				stand.setInvisible(true);
				});
	        final Vector[] previousVector = {throwVec};
	        Collection<Entity> damaged = new ArrayList<>();
		new BukkitRunnable() {
			private int run = -1;
			private final SkyblockPlayer player = p;
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				run++;
                if(run > 100) {
                    cancel();
                    return;
                }
                if(!armorStand.getLocation().getBlock().getType().isTransparent() || armorStand.isOnGround()) {
                    armorStand.remove();
                    cancel();
                    return;
                }

                double xPos = armorStand.getRightArmPose().getX();
                armorStand.setRightArmPose(new EulerAngle(xPos + 0.6D, 0.0D, 0.0D));

                Vector newVector = new Vector(throwVec.getX(), previousVector[0].getY() - 0.03D, throwVec.getZ());
                previousVector[0] = newVector;
                armorStand.setVelocity(newVector);

                for(Entity e : armorStand.getNearbyEntities(1, 1, 1)) {
                    if(e instanceof LivingEntity && e.getType() != EntityType.ARMOR_STAND && !(e instanceof Player)) {
                    	if(damaged.contains(e)) continue;
                    	damaged.add(e);
                    	LivingEntity entity = (LivingEntity)e;
						Calculator calculator = new Calculator();
						double damage = calculator.playerToEntityDamage(entity,player);
				        	
				        	damage *= 0.1;
				        	damage *= multi;
				        	if(SkyblockEntity.livingEntity.containsKey(entity)) {
				        		SkyblockEntity se = SkyblockEntity.livingEntity.get(entity);
				        		se.damage((int)damage,SkyblockPlayer.getSkyblockPlayer(player));
				        		
				        			
				        		
				        	}else {
				        	int live = Main.currentityhealth.get(entity) - (int) damage;
				        	Main.currentityhealth.replace(entity, live);}
						 if((SkyblockEntity.livingEntity.containsKey(entity) && SkyblockEntity.livingEntity.get(e).getHealth() <= 0) || (!SkyblockEntity.livingEntity.containsKey(entity)&&Main.currentityhealth.get(entity) <= 0) ) {
		        		
		        		Main.EntityDeath(entity);
		        		entity.damage(9999999,player);
		        		

		        		SkyblockEntity.livingEntity.remove(e);
		        		
		        		entity.addScoreboardTag("killer:" + player.getName());
		        		}
						 Main.updateentitystats(entity);
						 final double FINAL_DAMAGE = damage;
						 Location loc = new Location(entity.getWorld(), entity.getLocation().getX() ,entity.getLocation().getY() + 0.5 , entity.getLocation().getZ());
							
							ArmorStand stand =  entity.getWorld().spawn(loc, ArmorStand.class, armorstand ->{
								armorstand.setVisible(false);
							
								armorstand.setGravity(false);
								armorstand.setMarker(true);
								
							  
								armorstand.setCustomNameVisible(true);
							
								armorstand.setInvulnerable(true);
							if(calculator.isCrit) {
								String name = "§f✧";
								String num = "" + String.format("%.0f",Tools.round( FINAL_DAMAGE, 0));
								int col =1;
								int coltype = 1;
								String colstr = "§f";
								
								for (char x : num.toCharArray()) {
									name = name + colstr + x;
									++col;
									if(col ==2) {
										col = 0;
										++coltype;
										switch (coltype) {
											case 1 -> colstr = "§f";
											case 2 -> colstr = "§e";
											case 3 -> {
												colstr = "§6";
												coltype = 0;
											}
										}
										
									}
								}
								String x = "✧";
								name = name + colstr + x;
								armorstand.setCustomName(name);
							}else
								armorstand.setCustomName("§7" + String.format("%.0f",Tools.round( FINAL_DAMAGE, 0)));
							
							armorstand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
							armorstand.addScoreboardTag("damage_tag");
							armorstand.setArms(false);
							
							armorstand.setBasePlate(false);});
							
							Main.getMain().killarmorstand(stand);
						  e.setCustomNameVisible(true);
						 
						  if(Main.playerferocitycalc(player) != 0) {
							  int ferocity =(int) Main.playerferocitycalc(player);
							  
							  
							  if(ferocity < 100) {
								  Random r = new Random();
								  int low = 1;//includes 1
								  int high = 100;// includes 100
								  int result = r.nextInt(high-low) + low;
								  if(ferocity >= result) {
									  
									  Ferocity.hit(entity,(int) damage, calculator.isCrit, player);
									  Main.updateentitystats((LivingEntity)e);
								  }
							  }else {
								 double hits =(double) ferocity / 100;
								 
								  if(hits % 1 == 0) {
									  
									  SkyblockRemakeEvents.ferocity_call(entity, damage, calculator.cccalc, (int)Main.playercccalc(player), player, (int)hits);
									   
									 
								  }else {
									 int minus = ((int)hits * 100);
									 double hitchance = (double)ferocity - (double)minus;
									
									 Random r = new Random();
									  int low = 1;//includes 1
									  int high = 100;// includes 100
									  int result = r.nextInt(high-low) + low;
									 
									  if(hitchance >= result) {
										  hits = hits +1;
									  }
									  SkyblockRemakeEvents.ferocity_call(entity, damage, calculator.cccalc, (int)Main.playercccalc(player), player, (int)hits);
								  }
							  }
						  }
						  
                    
                    
                    }
                    
						  
                
                    	
                    	
                    
                }
            }
				
				
			
		}.runTaskTimer(Main.getMain(),0, 2);
	}

	@Override
	public void extraDamageEvent(SkyblockDamageEvent event) {
		int newHealth = event.getPlayer().currhealth + 50;
		if(newHealth > Main.playerhealthcalc(event.getPlayer()))
			event.getPlayer().setHealth(Main.playerhealthcalc(event.getPlayer()), HealthChangeReason.Ability);
		else
			event.getPlayer().setHealth(newHealth,HealthChangeReason.Ability);
	}
}
