package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;

import org.bukkit.Particle;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftVillager;
import org.bukkit.entity.ArmorStand;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.entity.EntityPose;
import net.minecraft.world.entity.Entity.RemovalReason;



public class BonzoPhase1 extends SkyblockEntity {
	
	private int health = 250000;
	private LivingEntity entity;
	private BukkitRunnable run;
	private BukkitRunnable switchPhaseRunnable;
	private BukkitRunnable moveRunnable;
	private int currentPillar = -1;
	private int pillars = 0;
	private BossBar bar;
	@Override
	public int getMaxHealth() {
		return 250000;
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
		return 8000;
	}
	private void pillar() {
		pillars++;
		
		run = new BukkitRunnable() {
			private int runs = 0;
			@Override
			public void run() {
				Random r = new Random();
				if(runs == 5) {
					if(pillars != 3) {
						int low = 0;
						  int high = 6;
						  int result = r.nextInt(high-low) + low;
						  
						  while(result == currentPillar) {
							   result = r.nextInt(high-low) + low;
						  }
						  
						  currentPillar = result;
						  
						  
						 
						moveToLoc(BonzoAbilitys.PillarLocations(result, entity.getWorld()), BonzoAbilitys.WitherSkull);
						
					}else {
						pillars = 0;
						moveToLoc(BonzoAbilitys.PillarLocations(-1, entity.getWorld()), null);
					}
					
					cancel();
					return;
				}
				
				
				int low = 0;
				  int high = Bukkit.getOnlinePlayers().size() -1;
				  Player player;
				  if(Bukkit.getOnlinePlayers().size() == 1) {
					  player = (Player) Bukkit.getOnlinePlayers().toArray()[0];
				  }else {
				  int result = r.nextInt(high-low) + low;
				   player = (Player) Bukkit.getOnlinePlayers().toArray()[result];}
				  Location lo = entity.getLocation().setDirection(player.getLocation().subtract(entity.getLocation()).toVector());
				  entity.teleport(lo);
				  entity.launchProjectile(WitherSkull.class);
				 runs++;
					 
				
			}
		};
		run.runTaskTimer(Main.getMain(), 10, 10);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void spawn(Location loc) {
		loc = new Location(loc.getWorld(), -42.5, 71, 20.5,0,0);
		WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
		BonzoPhase1Enity z = new BonzoPhase1Enity(loc,world);
		UUID id = z.getUniqueID();
		world.addEntity(z);
		entity = (LivingEntity) Bukkit.getEntity(id);
		entity.setAI(false);
			
			
		entity.setCanPickupItems(false);
		entity.getEquipment().setItemInHand(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/97c4036f80bf3609180c7c4a9568853fd815f154ad8438b92f3851019835b070"));
		
		entity.setRemoveWhenFarAway(false);


		DiguestMobsManager.createEntity(entity, loc, "Bonzo","ewogICJ0aW1lc3RhbXAiIDogMTY1OTYyNTAwNzUwMiwKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzQ3VwY2FrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85MWU3OWY3ZTYyOGYyMWY0Mjc2MTBjYzZjZWQwZmYxNTNlMjE4ODBkNjFjYmFjNjczYmY4NjJiMWQ0ZTQ0MjZiIgogICAgfQogIH0KfQ==",
				"Re4ZMY9+QFaiuyShGFJUpEfZ2KP+BjZn49Q195tVO8Z+at6XOu59HatnTX392oxq9mYjQmImVailc5Q9n1rdXAj8UpIilzOYYweiY7e9YP9MyHjRCQeqI1uqkWzB/JXI7zYLzRizB6zr2Wp7eLzm8Q9fTrjIQmkuqkuUYOHt6e3AYvhER4yFQtFXvGHq97RQLFEQJpqKNl4hfEf5ZgLfuVs5ep3TTVLShUPTTlFKDqrv2XIugTpGFxsJBhamFMDZ86ogDtHD46Rjb2dImIWscnVoASdrVkDCKn+P6Dnt/3QL0E/8RWzrAF+NiPJRaJfhs6tFPdMySAOwiTpMnx4nJ7xuV95YvTK394TuNPg093cR9vJpSkrN9LWsmMfrFZy+38/uTncSHMovGhHdGfI8tFsihCW15HcUdSBPCLuOKtpYDTvlIFL1BH2ayMMINzBj55IBGGfRuRqMrri+NMu3BBLJq2II+dKefIPTlAMqZUdUTnfjU0WLLbXofip5cd9jlfWHn1sXjmMq+eX5bOEkEAnozAAkC6+yfKcVRG3BCSrviJWiTwJVhrDeos1ui/18w9VoyY4rITHer2zKRCieDJeiSj8EEbVj0ljrpIw+hdgG0Hj1akd65VojevFq74umaw6Gvr5CIJU/yVvGorXUsn42QCJuXw3a+9O4JBNFhZo=");
		SkyblockEntity.livingEntity.put(entity, this);
		dialog1();
		bar = Bukkit.createBossBar("§cBonzo", BarColor.RED, BarStyle.SOLID);
		bar.setVisible(true);
		

	}
	private void moveToLoc(Location loc, BonzoAbilitys nextAbility){
		Location lo = entity.getLocation().setDirection(loc.clone().subtract(entity.getLocation()).toVector());
	entity.teleport(lo);
   entity.setAI(false);
        
   moveRunnable= new BukkitRunnable() {
			
			@Override
			public void run() {
				//Face to target Position player

				Vector dir = entity.getLocation().getDirection();
				Location l = entity.getLocation();
				if(l.distance(loc) > 0.65) {
				dir.multiply(0.65);
				l = l.add(dir);
				}
				else {
				l.setX(loc.getX());	
				l.setY(loc.getY());
				l.setZ(loc.getZ());
				}
				
				
				entity.teleport(l);
				entity.getWorld().spawnParticle(Particle.SPELL_WITCH, entity.getLocation(), 6);
				if(l.distance(loc) < 0.1 && l.distance(loc) >-0.1)
				{
					
				
					if(nextAbility == BonzoAbilitys.WitherSkull)
					{
						pillar();
					}else{
						startFight();
					}
					
					cancel();
				}
				
				
			}
		};
   moveRunnable.runTaskTimer(Main.getMain(), 1, 1);
    }
	
	private void dialog1() {
		Bukkit.broadcastMessage("§c[BOSS] Bonzo§f: Gratz for making it this far, but I'm basically unbeatable.");
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				
				Bukkit.broadcastMessage("§c[BOSS] Bonzo§f: I don't even need to fight, this is the life!.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						Bukkit.broadcastMessage("§c[BOSS] Bonzo§f: I can summon a lots of undead! Check this out.");
						new BukkitRunnable() {
							
							@Override
							public void run() {
								startFight();
								
							}
						}.runTaskLater(Main.getMain(), 40);
						
					}
				}.runTaskLater(Main.getMain(), 40);
			}
		}.runTaskLater(Main.getMain(), 20*3);
	}
	private void startFight() {
		entity.setAI(true);
		summonUndead(new Vector(0,0.4,0));
		switchPhaseRunnable = new BukkitRunnable() {
			
			@Override
			public void run() {
				Random r = new Random();
				  int low = 0;
				  int high = 6;
				  int result = r.nextInt(high-low) + low;
				  currentPillar = result;
				moveToLoc(BonzoAbilitys.PillarLocations(result, entity.getWorld()), BonzoAbilitys.WitherSkull);
				
			}
		};
		switchPhaseRunnable.runTaskLater(Main.getMain(), 20 * 10);
	}
	
	private void summonUndead(Vector throwVec) {
		System.out.println("startvector :D");
		ArmorStand armorStand = entity.getWorld().spawn(entity.getLocation().add(0,1,0), ArmorStand.class, s ->{
			s.setInvisible(true);
	
			s.setInvulnerable(true);
		});
		final Vector[] previousVector = {throwVec};
		new BukkitRunnable() {
			private int run = -1;
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
                    System.out.println("undead sumoned");
                    cancel();
                    return;
                }
		 Vector newVector = new Vector(throwVec.getX(), previousVector[0].getY() - 0.03D, throwVec.getZ());
         previousVector[0] = newVector;
         armorStand.setVelocity(newVector);
         final Particle.DustOptions dust = new Particle.DustOptions(
                 Color.BLACK, 1);
 		armorStand.getLocation().getWorld().spawnParticle(Particle.REDSTONE, armorStand.getLocation(),1, dust);
         
			}
		}.runTaskTimer(Main.getMain(), 1, 1);
		 
	}
	
//	private void shoot() {
//		run = new BukkitRunnable() {
//			
//			@Override
//			public void run() {
//				entity.launchProjectile(WitherSkull.class);
//				
//			}
//		};
//		run.runTaskTimer(Main.getMain(), 10, 10);
//	}


	@Override
	public String getName() {
		return "Bonzo";
	}

	@Override
	public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
		return null;
	}

	@Override
	public void updateNameTag() {
		if (health > 999) {
			if (health > 9999) {
				if (health > 999999) {
					if (health > 9999999) {
						DiguestMobsManager.getDiguested.get(entity).setName("§6﴾ §c" + getName()
						+ " §a"+ health / 1000000 + "m§c§?§" + " §6﴿");
							
						
					} else {
						DiguestMobsManager.getDiguested.get(entity).setName("§6﴾ §c" + getName()
						+ " §a"+ Tools.round(
								(float) ((float) health / 1000000), 1) + "m§c§?§" + " §6﴿");
						
						
					}
				} else {
					DiguestMobsManager.getDiguested.get(entity).setName("§6﴾ §c" + getName()
					+ " §a"+ health / 1000 + "k§c§?§" + " §6﴿");
						
					;

				}
			} else {
				DiguestMobsManager.getDiguested.get(entity).setName("§6﴾ §c" + getName()
				+ " §a"+ Tools.round((float) ((float) health / 1000), 1) + "k§c§?§" + " §6﴿");
					
				
			}
		} else 
			DiguestMobsManager.getDiguested.get(entity).setName("§6﴾ §c" + getName()
			+ " §a"+ health + "§c§?§" + " §6﴿");
			
		for(Player p : Bukkit.getOnlinePlayers()) {
			bar.addPlayer(p);
		}
		System.out.println(bar.getProgress());

	}

	@Override
	public void kill() {
		if(run != null && !run.isCancelled())
		run.cancel();
		if(switchPhaseRunnable != null && !switchPhaseRunnable.isCancelled())
			switchPhaseRunnable.cancel();
		if(moveRunnable != null && !moveRunnable.isCancelled())
			moveRunnable.cancel();
		entity.remove();
		bar.removeAll();

		
		final EntityPlayer fp = NPCUtil.spawnNPC(entity.getLocation(), "ewogICJ0aW1lc3RhbXAiIDogMTY1OTYyNTAwNzUwMiwKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzQ3VwY2FrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85MWU3OWY3ZTYyOGYyMWY0Mjc2MTBjYzZjZWQwZmYxNTNlMjE4ODBkNjFjYmFjNjczYmY4NjJiMWQ0ZTQ0MjZiIgogICAgfQogIH0KfQ==",
				"Re4ZMY9+QFaiuyShGFJUpEfZ2KP+BjZn49Q195tVO8Z+at6XOu59HatnTX392oxq9mYjQmImVailc5Q9n1rdXAj8UpIilzOYYweiY7e9YP9MyHjRCQeqI1uqkWzB/JXI7zYLzRizB6zr2Wp7eLzm8Q9fTrjIQmkuqkuUYOHt6e3AYvhER4yFQtFXvGHq97RQLFEQJpqKNl4hfEf5ZgLfuVs5ep3TTVLShUPTTlFKDqrv2XIugTpGFxsJBhamFMDZ86ogDtHD46Rjb2dImIWscnVoASdrVkDCKn+P6Dnt/3QL0E/8RWzrAF+NiPJRaJfhs6tFPdMySAOwiTpMnx4nJ7xuV95YvTK394TuNPg093cR9vJpSkrN9LWsmMfrFZy+38/uTncSHMovGhHdGfI8tFsihCW15HcUdSBPCLuOKtpYDTvlIFL1BH2ayMMINzBj55IBGGfRuRqMrri+NMu3BBLJq2II+dKefIPTlAMqZUdUTnfjU0WLLbXofip5cd9jlfWHn1sXjmMq+eX5bOEkEAnozAAkC6+yfKcVRG3BCSrviJWiTwJVhrDeos1ui/18w9VoyY4rITHer2zKRCieDJeiSj8EEbVj0ljrpIw+hdgG0Hj1akd65VojevFq74umaw6Gvr5CIJU/yVvGorXUsn42QCJuXw3a+9O4JBNFhZo=");

		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				Bukkit.broadcastMessage("§c[BOSS] Bonzo§f: Oh I'm dead!");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						Bukkit.broadcastMessage("§c[BOSS] Bonzo§f: Sike!");
						fp.setRemoved(RemovalReason.a);
						for(Player on : Bukkit.getOnlinePlayers()) {
							 PlayerConnection p = ((CraftPlayer) on).getHandle().b;
					          
					            p.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.
					            		e, fp));
					            p.sendPacket(new PacketPlayOutEntityDestroy(fp.getId()));
						}
						new BonzoPhase2().spawn(entity.getLocation().add(0,1,0));
						
					}
				}.runTaskLater(Main.getMain(), 3*20);
				
			}
		}.runTaskLater(Main.getMain(), 20);
		
	}

	@Override
	public void damage(double damage, SkyblockPlayer player) {
	
		health -= damage;
		if(health/getMaxHealth() < 0)
			bar.removeAll();
			else
		bar.setProgress((double)health/(double)getMaxHealth());
	}

	@Override
	public boolean hasNoKB() {
		return true;
	}

	@Override
	public int getTrueDamage() {
		return 0;
	}

}
