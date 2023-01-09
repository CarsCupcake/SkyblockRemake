package me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys.T1;

import java.util.HashMap;

import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys.Demons;
import me.CarsCupcake.SkyblockRemake.Slayer.Blaze.Entitys.DemonsGoal;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

import org.bukkit.craftbukkit.v1_17_R1.entity.CraftWitherSkeleton;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.world.entity.player.EntityHuman;

public class QuaziiT1 extends SkyblockEntity implements Demons {

	
	private int health = 500000;
	private WitherSkeleton entity;
	
	private BlazeSlayerT1 baseSlayer;
	private final String name = "§3ⓆⓊⒶ§?ⒾⒾ";
	
	private BukkitRunnable tpRun;
	
	private ArmorStand timer;
	
	public boolean isInvincible = true;

	@Override
	public boolean isInvinceble() {
		return isInvincible;
	}
	
	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 500000;
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
	
	


	private void startTimer() {
		tpRun = new BukkitRunnable() {
			
			@Override
			public void run() {
				timer.teleport(entity.getLocation().add(0,0.7,0));
				
			}
		};
		tpRun.runTaskTimer(Main.getMain(), 0, 1);
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return 2500;
	}
	public void startAttack(SkyblockPlayer player) {

		entity.attack(player);
	}
	public void updateTimer() {
		String str = "";
		if(isInvincible)
			str = "§4§lIMMUNE ";
		timer.setCustomName(str + "§c" +shortInteger(baseSlayer.time));
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
			string = string + "";
		} else {
			if (hours <= 9) {
				string = string + "0" + hours + ":";
			} else {
				string = string + hours + ":";
			}
		}
		if (minutes == 0) {
			string = string + "";
		} else {
			if (minutes <= 9) {
				string = string + "0" + minutes + ":";
			} else {
				string = string + minutes + ":";
			}
		}
		
			if (seconds <= 9) {
				string = string + "0" + seconds ;
			} else {
				string = string + seconds ;
			
		}
		return string;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void spawn(Location loc) {
		entity = loc.getWorld().spawn(loc, WitherSkeleton.class, s ->{
			s.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/16ca145ba435b375f763ff53b4ce04b2a0c873e8ff547e8b14b392fde6fbfd94"));
			s.getEquipment().setHelmetDropChance(0);
			s.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
			s.getEquipment().setChestplateDropChance(0);
			s.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
			s.getEquipment().setBootsDropChance(0);
			s.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_AXE));
			s.getEquipment().setItemInHandDropChance(0);
			s.setCustomNameVisible(true);
			((CraftWitherSkeleton)s).getHandle().setGoalTarget(baseSlayer.owner.getHandle(), TargetReason.TARGET_ATTACKED_ENTITY, true);
			s.setTarget(baseSlayer.owner);
			s.setRemoveWhenFarAway(false);
		});
		((CraftWitherSkeleton)entity).getHandle().bP.a(0,new DemonsGoal<EntityHuman>(this, ((CraftWitherSkeleton)entity).getHandle(), EntityHuman.class, 5,1,1));
		Attributable zombieAt = entity;
		AttributeInstance attributeSpeed = zombieAt.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
		attributeSpeed.setBaseValue(0.35);
	SkyblockEntity.livingEntity.put(entity, this);
	updateNameTag();
	timer = entity.getWorld().spawn(loc.add(0,0.7,0), ArmorStand.class, s ->{
		s.setGravity(false);
		s.setInvisible(true);
		s.setInvulnerable(true);
		s.setCustomNameVisible(true);
	
	});
	startTimer();
	}



	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
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
	
	public void setBlazeSlayer(BlazeSlayerT1 slayer) {
		baseSlayer = slayer;
	}

	@Override
	public void kill() {
		
		if(baseSlayer != null)
		baseSlayer.setQuaziiKilled();
		tpRun.cancel();
		timer.remove();
		
	}

	@Override
	public boolean hasNoKB() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void damage(double damage, SkyblockPlayer player) {
		if(!isInvincible)
			health -= damage;
		
	}
	public void setInvinceble(boolean bol) {
		isInvincible = bol;
		updateTimer();
		if(isInvincible == false)
			entity.attack(baseSlayer.owner);
	}

	@Override
	public int getTrueDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
