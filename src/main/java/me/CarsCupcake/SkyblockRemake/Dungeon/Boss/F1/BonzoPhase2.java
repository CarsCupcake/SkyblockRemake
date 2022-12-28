package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
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
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
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



public class BonzoPhase2 extends SkyblockEntity {
	
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
		// TODO Auto-generated method stub
		return 250000;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

	@Override
	public LivingEntity getEntity() {
		// TODO Auto-generated method stub
		return entity;
	}



	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
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
						  
						  
						 
						moveToLoc(BonzoAbilitys.PillarLocations(result, entity.getWorld()), BonzoAbilitys.BonzosBalloon);
						
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
			Location lel = entity.getLocation().add(entity.getLocation().getDirection().multiply(1));
			new BonzoPrejectile(lel, entity);
				 runs++;
					 
				
			}
		};
		run.runTaskTimer(Main.getMain(), 10, 10);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void spawn(Location loc) {
	

		
		entity = loc.getWorld().spawn(loc, Zombie.class);
		
		Attributable zombieAt = (Attributable) entity;
		AttributeInstance attributeSpeed = zombieAt.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
		attributeSpeed.setBaseValue(0.5);
		
			
		entity.setCanPickupItems(false);
		entity.getEquipment().setItemInHand(new ItemStack(Material.BLAZE_ROD));
		
		entity.setRemoveWhenFarAway(false);


		DiguestMobsManager.createEntity(entity, loc, "Bonzo","ewogICJ0aW1lc3RhbXAiIDogMTY1OTYyNTIwODg1MSwKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzQ3VwY2FrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hNzdmNzZmOWViNjAwYTgxNGFkYjE4YzA1NGRjN2MyNGUyNzg5MDJjZTRhYWU0ZTlmNzE0YWYxYTg5Y2VhZGZmIgogICAgfQogIH0KfQ==",
				"dT4X23auQlmLPrNkMmLuY0OPCjvWSo6VOG9hgGAmXWMJUip4iRfGt7JrILxzCvCJKKMUx5VBLCc/HyTob/2BMVBfMng1nYuTsUK8pzlACQvlbLYP+RTL9gXhLNGKHSdnfbTBfo16Eg4pRtJXouwY6Ni9tk2tpPfihvIjdmMnp1lQSnjK4G8p0UwVt4mturSNGRg4INjyEquj4EO68z9BhEVFdl5hT8ZK6m1UwH0ZJltiaH27QDacmQqK6dgGb33fBdjFQjomQTjG2cL+Tbp19iUqzhLGJJQHUvVGmnbQ7vrR0ezJv+EptvPKSem0H3OaJSzALx+q0PxzLTfA1VfwaNB0gUutMQ0dT+jglbT998+MOmVKCwNICrPbnVjAE3g+CFisuePsOr1HrxNUgXrAH8UWtqAXAPdycj7kqPmo1I/QiV5ulgOl7+X/81OczYsCfdM+42wuUVXYNM7V7VSM4EvAtYyWj6iiH7KwN+8bMcPd/AIAcPzS9+yuxJwNacQJmOHwU7A6a98jMSax3eJOyOxeqeT3T4nAaevfAvsNDSG6yRBtQIqzij6m04lH4lkLGtLQmyVkQR4xEcScAe8McKUWsT0fTb4TR3WYnsaogj98vHl8ZDhGzvKJQmy2Titb45GhmmtS/BhUYBykxhRz8C1PcPAXIQOjQBwSgy+Zn1A=");
		SkyblockEntity.livingEntity.put(entity, this);
		
		bar = Bukkit.createBossBar("§cBonzo", BarColor.RED, BarStyle.SOLID);
		bar.setVisible(true);
		startFight();

	}
	
	private void balloons2() {
		run = new BukkitRunnable() {
			private int runs = 0;
			private int plre = 0; 
			@Override
			public void run() {
				plre++;
				runs++;
				if(runs == 60) {
					entity.setAI(true);
					startFight();
					cancel();
					return;
					
				}
				Random r = new Random();
				float yaw = 0;
				if(plre == 5) {
					plre = 0;
					int low = 0;
					  int high = Bukkit.getOnlinePlayers().size() -1;
					  Player player;
					  if(Bukkit.getOnlinePlayers().size() == 1) {
						  player = (Player) Bukkit.getOnlinePlayers().toArray()[0];
					  }else {
					  int result = r.nextInt(high-low) + low;
					   player = (Player) Bukkit.getOnlinePlayers().toArray()[result];
					   }
					  
					  Location lo = entity.getLocation().setDirection(player.getLocation().subtract(entity.getLocation()).toVector());
					  yaw = lo.getYaw();
				}else {
					int low = -180;
					  int high = 180;
					yaw = r.nextInt(high-low) + low;
				}
				Location loc = entity.getLocation();
				loc.setYaw(yaw);
				entity.teleport(loc);
				loc = loc.add(loc.getDirection().multiply(1));
				new BonzoPrejectile(loc, entity);
				
				
				
			}
		};
		run.runTaskTimer(Main.getMain(), 1, 5);
	}
	
	
	
	
	private void moveToLoc(Location loc, BonzoAbilitys nextAbility){
		Location lo = entity.getLocation().setDirection(loc.clone().subtract(entity.getLocation()).toVector()) ;
		System.out.println(loc);
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
					
				
					if(nextAbility == BonzoAbilitys.BonzosBalloon)
					{
						pillar();
					}else{
						if(nextAbility == BonzoAbilitys.BalloonBarrage)
							balloons2();
						else
						startFight();
					}
					
					cancel();
				}
				
				
			}
		};
   moveRunnable.runTaskTimer(Main.getMain(), 1, 1);
    }
	
	
	private void startFight() {
		entity.setAI(true);
	
		switchPhaseRunnable = new BukkitRunnable() {
			
			@Override
			public void run() {
				Random r = new Random();
				  int low = 0;
				  int high = 6;
				  int result = r.nextInt(high-low) + low;
				  currentPillar = result;
				 if(r.nextBoolean()) 
				moveToLoc(BonzoAbilitys.PillarLocations(result, entity.getWorld()), BonzoAbilitys.BonzosBalloon);
				 else
					 moveToLoc(BonzoAbilitys.PillarLocations(-1, entity.getWorld()), BonzoAbilitys.BalloonBarrage); 
				
			}
		};
		switchPhaseRunnable.runTaskLater(Main.getMain(), 20 * 10);
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
		// TODO Auto-generated method stub
		return "Bonzo";
	}

	@Override
	public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
		// TODO Auto-generated method stub
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

		
		final EntityPlayer fp = NPCUtil.spawnNPC(entity.getLocation(), "ewogICJ0aW1lc3RhbXAiIDogMTY1OTYyNTIwODg1MSwKICAicHJvZmlsZUlkIiA6ICI4MzE4ZmFmZDU1NjU0YTNlOTFhMTI5NmRmMjk5NWIzMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXJzQ3VwY2FrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hNzdmNzZmOWViNjAwYTgxNGFkYjE4YzA1NGRjN2MyNGUyNzg5MDJjZTRhYWU0ZTlmNzE0YWYxYTg5Y2VhZGZmIgogICAgfQogIH0KfQ==",
				"dT4X23auQlmLPrNkMmLuY0OPCjvWSo6VOG9hgGAmXWMJUip4iRfGt7JrILxzCvCJKKMUx5VBLCc/HyTob/2BMVBfMng1nYuTsUK8pzlACQvlbLYP+RTL9gXhLNGKHSdnfbTBfo16Eg4pRtJXouwY6Ni9tk2tpPfihvIjdmMnp1lQSnjK4G8p0UwVt4mturSNGRg4INjyEquj4EO68z9BhEVFdl5hT8ZK6m1UwH0ZJltiaH27QDacmQqK6dgGb33fBdjFQjomQTjG2cL+Tbp19iUqzhLGJJQHUvVGmnbQ7vrR0ezJv+EptvPKSem0H3OaJSzALx+q0PxzLTfA1VfwaNB0gUutMQ0dT+jglbT998+MOmVKCwNICrPbnVjAE3g+CFisuePsOr1HrxNUgXrAH8UWtqAXAPdycj7kqPmo1I/QiV5ulgOl7+X/81OczYsCfdM+42wuUVXYNM7V7VSM4EvAtYyWj6iiH7KwN+8bMcPd/AIAcPzS9+yuxJwNacQJmOHwU7A6a98jMSax3eJOyOxeqeT3T4nAaevfAvsNDSG6yRBtQIqzij6m04lH4lkLGtLQmyVkQR4xEcScAe8McKUWsT0fTb4TR3WYnsaogj98vHl8ZDhGzvKJQmy2Titb45GhmmtS/BhUYBykxhRz8C1PcPAXIQOjQBwSgy+Zn1A=");

		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				
						for(Player on : Bukkit.getOnlinePlayers()) {
							 PlayerConnection p = ((CraftPlayer) on).getHandle().b;
					          
					            p.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.
					            		e, fp));
					            p.sendPacket(new PacketPlayOutEntityDestroy(fp.getId()));
						}
						
					
		}
		}.runTaskLater(Main.getMain(), 20*10);
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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getTrueDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
