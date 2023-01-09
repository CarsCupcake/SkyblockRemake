package me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys.T1;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class BlazeSlayerT1 extends SkyblockEntity{

	
	private int health = 2500000;
	private LivingEntity entity;
	public SkyblockPlayer owner;
	private BukkitRunnable run;
	private int ticks = 0;
	public int time = 260;
	private ArmorStand stand;
	private boolean demonsplitHasActivated = false;
	
	private boolean quaziiAlive = false;
	private boolean typhoeusAlive = false;
	
	private boolean isInvincible = false;
	
	private BukkitRunnable teleportRunnable;
	
	private QuaziiT1 quazii;
	private TyphoesT1 typhoes;
	
	private BukkitRunnable aoeRunner;
	@Override
	public int getMaxHealth() {
		return 2500000;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public LivingEntity getEntity() {
		return entity;
	}

	@Override
	public int getDamage() {
		return 0;
	}

	@Override
	public void spawn(Location loc) {
		entity = loc.getWorld().spawn(loc, Blaze.class, blaze ->{
			blaze.setCustomNameVisible(true);
					blaze.addScoreboardTag("combatxp:50");
					blaze.addScoreboardTag("abilityimun");
			blaze.setRemoveWhenFarAway(false);
		}
		);
		startAoe();
		SkyblockEntity.livingEntity.put(entity, this);
		
		stand = entity.getWorld().spawn(loc, ArmorStand.class, s ->{
			s.setGravity(false);
			s.setInvisible(true);
			s.setInvulnerable(true);
			s.setCustomNameVisible(true);
			s.setCustomName("§c" +shortInteger(time));
		});
		timeTag();
		Main.updateentitystats(entity);
		
	}
	public void setOwner(SkyblockPlayer player) {
		owner = player;
	}


	@Override
	public String getName() {
		return "Inferno Demonlord";
	}



	@Override
	public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
		return null;
		}
	
	
	
	@Override
	public void updateNameTag() {
		String name = "Inferno Demonlord";
		if (health > 999) {
			if (health > 9999) {
				if (health > 999999) {
					if (health > 9999999) {
						
							entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name
									+ " §a" + health / 1000000 + "m§c§?§");
						
					} else {
						
							entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name
									+ " §a"
									+ Tools.round(
											(float) ((float) health / 1000000), 1)
									+ "m§c§?§");
						
					}
				} else {
					
						entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a"
								+ health / 1000 + "k§c§?§");
					;

				}
			} else {
				
					entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a"
							+ Tools.round((float) ((float) health / 1000), 1)
							+ "k§c§?§");
				
			}
		} else 
			entity.setCustomName("§c" + Character.toChars(9760)[0] + " §b" + name + " §a"
					+ health + "§c§?§");
		
	}

	@Override
	public void kill() {
		run.cancel();
		stand.remove();
		aoeRunner.cancel();
		
	}
	
	private void timeTag() {
		run = new BukkitRunnable() {
			
			@Override
			public void run() {
				ticks += 1;
				if(ticks == 20) {
					ticks = 0;
					time -= 1;
					if(time == 0) {
						kill();
						entity.remove();
						if(stand != null)
						stand.remove();
						if(quaziiAlive) {
							quazii.kill();
							quazii.getEntity().remove();
							}
						if(typhoeusAlive) {
							typhoes.kill();
							typhoes.getEntity().remove();
							}
						cancel();
						return;
					}else {
						if(stand != null)
						stand.setCustomName("§c" + shortInteger(time));
						if(quaziiAlive)
							quazii.updateTimer();
						if(typhoeusAlive)
							typhoes.updateTimer();
					}
				}
				if(stand != null)
				stand.teleport(entity.getLocation().add(0,0.25,0));
				
			}
		};
		run.runTaskTimer(Main.getMain(), 1, 1);
	}
	
	
	
	public void demonsplit(int beforhealth) {
		if(!demonsplitHasActivated && health >0) {
			if(health <= getMaxHealth() /2) {
				demonsplitHasActivated = true;
				isInvincible = true;
				entity.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, entity.getLocation(), 0, 0, 0, 2, 0, null);
				stayTeleport(entity.getLocation().add(0,2,0));
				new BukkitRunnable() {
					
					@SuppressWarnings("deprecation")
					@Override
					public void run() {
					Location quazziLoc = entity.getLocation().clone();
					float quazziYaw = quazziLoc.getYaw();
					quazziYaw += 90;
					if(quazziYaw > 180)
						quazziYaw -= 360;
					quazziLoc.setYaw(quazziYaw);
					quazziLoc.setPitch(0);
					Vector quaziiDir = quazziLoc.getDirection();
					quaziiDir.multiply(2);
					quazziLoc = quazziLoc.add(quaziiDir);
					
					
						
					final ArmorStand quaziiStand =	entity.getLocation().getWorld().spawn(quazziLoc, ArmorStand.class, s ->{
							s.setInvisible(true);
							s.setInvulnerable(true);
							s.setGravity(false);
							
						});
					entity.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_HUGE,quaziiStand.getLocation().add(0,0.5,0), 0, 0, 0, 5, 0, null);
					Location typhoeusLoc = entity.getLocation().clone();
					float typhoeusYaw = typhoeusLoc.getYaw();
					typhoeusYaw -= 90;
					if(typhoeusYaw < -180)
						typhoeusYaw += 360;
					typhoeusLoc.setYaw(typhoeusYaw);
					typhoeusLoc.setPitch(0);
					Vector typhoeusDir = typhoeusLoc.getDirection();
					typhoeusDir.multiply(2);
					typhoeusLoc = typhoeusLoc.add(typhoeusDir);
					final ArmorStand typhoeusStand = entity.getLocation().getWorld().spawn(typhoeusLoc, ArmorStand.class, s ->{
							s.setInvisible(true);
							s.setInvulnerable(true);
							s.setGravity(false);
							
						});
					entity.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_HUGE,typhoeusStand.getLocation().add(0,0.5,0), 0, 0, 0, 5, 0, null);
					
					QuaziiT1 quazii = new QuaziiT1();
					quazii.setBlazeSlayer(BlazeSlayerT1.this);
					quazii.spawn(quazziLoc);
//					quazii.startAttack(SkyblockPlayer.getSkyblockPlayer(owner));
					quaziiAlive = true;
					BlazeSlayerT1.this.quazii = quazii;
					quaziiStand.setPassenger(quazii.getEntity());
					
					TyphoesT1 typhoes = new TyphoesT1();
					typhoes.setBlazeSlayer(BlazeSlayerT1.this);
					
					typhoes.spawn(typhoeusLoc);
//					typhoes.startAttack(SkyblockPlayer.getSkyblockPlayer(owner));
					typhoeusAlive = true;
					typhoeusStand.setPassenger(typhoes.getEntity());
					BlazeSlayerT1.this.typhoes = typhoes;	
					boolean bol = new Random().nextBoolean();
					System.out.println(bol);
					if(bol) {
						quazii.setInvinceble(true);
						typhoes.setInvinceble(false);
					}else {
						quazii.setInvinceble(false);
						typhoes.setInvinceble(true);
					}
						aoeRunner.cancel();
						teleportRunnable.cancel();
						entity.remove();
						stand.remove();
						stand = null;
						SkyblockEntity.livingEntity.remove(entity);
						new BukkitRunnable() {
							
							@Override
							public void run() {
								quaziiStand.remove();
								typhoeusStand.remove();
								
							}
						}.runTaskLater(Main.getMain(), 40);
						
						
					}
				}.runTaskLater(Main.getMain(), 20*3);
				
				
			}
		}
	}
	
	private void stayTeleport(final Location loc) {
		teleportRunnable = new BukkitRunnable() {
			
			@Override
			public void run() {
				entity.teleport(loc);
				
			}
		};
		teleportRunnable.runTaskTimer(Main.getMain(), 0, 1);
	}
	
	
	public void setQuaziiKilled() {
		quaziiAlive = false;
		if(!typhoeusAlive) {
			summounBlazeSlayerBack(quazii);
		}else
			typhoes.setInvinceble(false);
	}
	public void setTyphoeusKilled() {
		typhoeusAlive = false;
		if(!quaziiAlive) {
			summounBlazeSlayerBack(typhoes);
		}else
			quazii.setInvinceble(false);
	}
	
	private void summounBlazeSlayerBack(SkyblockEntity e) {
		isInvincible = false;
		Location loc = e.getEntity().getLocation();
		loc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, loc, 0, 0, 0, 1, 0, null);
		entity = loc.getWorld().spawn(loc, Blaze.class, blaze ->{
					blaze.setCustomNameVisible(true);
					blaze.addScoreboardTag("combatxp:50");
					blaze.addScoreboardTag("abilityimun");
					blaze.setRemoveWhenFarAway(false);
		}
		);
		startAoe();
		SkyblockEntity.livingEntity.put(entity, this);
		
		stand = entity.getWorld().spawn(loc, ArmorStand.class, s ->{
			s.setGravity(false);
			s.setInvisible(true);
			s.setInvulnerable(true);
			s.setCustomNameVisible(true);
			s.setCustomName("§c" +shortInteger(time));
		});
		Main.updateentitystats(entity);
	}
	
	
	private String shortInteger(int duration) {
		String string = "";
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		if (duration / 60 / 60 / 24 >= 1) {
			duration -= duration / 60 / 60 / 24 * 60 * 60 * 24;
		}
		if (duration / 60 / 60 >= 1) {
			hours = duration / 60 / 60;
			duration -= duration / 60 / 60 * 60 * 60;
		}
		if (duration / 60 >= 1) {
			minutes = duration / 60;
			duration -= duration / 60 * 60;
		}
		if (duration >= 1)
			seconds = duration;
		if (hours == 0) {
			string = String.valueOf(string) + "";
		} else {
			if (hours <= 9) {
				string = String.valueOf(string) + "0" + hours + ":";
			} else {
				string = String.valueOf(string) + hours + ":";
			}
		}
		if (minutes == 0) {
			string = String.valueOf(string) + "";
		} else {
			if (minutes <= 9) {
				string = String.valueOf(string) + "0" + minutes + ":";
			} else {
				string = String.valueOf(string) + minutes + ":";
			}
		}
		
			if (seconds <= 9) {
				string = String.valueOf(string) + "0" + seconds ;
			} else {
				string = String.valueOf(string) + seconds ;
			}
		
		return string;
	}

	@Override
	public boolean hasNoKB() {
		return true;
	}

	@Override
	public void damage(double damage, SkyblockPlayer player) {
		if(isInvincible)
			return;
		int beforehealth = health;
		health -= damage;
		if(health > 0)
		demonsplit(beforehealth);
		
	}

	@Override
	public int getTrueDamage() {
		if(owner == null)
			return 100;
		double mult = 0;
		if(getMaxHealth()*0.33>health )
			mult = 0.12;
		else
			if(getMaxHealth()*0.66>health)
				mult = 0.08;
			else mult = 0.05;
		return (int)(100 + ( Main.playerhealthcalc(owner) * mult ));
	}
	private void startAoe() {
		aoeRunner = new BukkitRunnable() {
			
			@Override
			public void run() {
				owner.damage(0.1);
				int truedamage = getTrueDamage();
			
					float trueehp = (float) (float)Main.playerhealthcalc(owner)*(1+((float)Main.playertruedefense(owner)/100));
					float effectivetruedmg = (float)Main.playerhealthcalc(owner)/(float)trueehp;
					int totaldmg = (int) ((int) truedamage*effectivetruedmg);
					if(Main.absorbtion.get(owner) - totaldmg  < 0) {
						float restdamage =   (float)totaldmg - (float) Main.absorbtion.get(owner);
						Main.absorbtion.replace(owner, 0);
						owner.setHealth( owner.currhealth  - (int)restdamage);
					}else {
						Main.absorbtion.replace(owner, Main.absorbtion.get(owner) - totaldmg);
					}
					
					if(owner.currhealth  <= 0) {
						owner.setHealth(0);
						
					}
					Main.updatebar(owner);
				
			}
		};
		aoeRunner.runTaskTimer(Main.getMain(), 20, 20);
	}

}
