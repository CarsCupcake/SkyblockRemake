package me.CarsCupcake.SkyblockRemake.Skyblock;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class SkyblockEntity {
	protected int health;
	public SkyblockEntity(){
		health = getMaxHealth();
	}


	public abstract int getMaxHealth();
	 public int getHealth(){
		 return health;
	 }

	public abstract LivingEntity getEntity();

	public abstract int getDamage();
	public abstract void spawn(Location loc);
	public abstract String getName();
	public abstract HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player);
	public abstract void updateNameTag();
	public abstract void kill();
	public abstract void damage(double damage,SkyblockPlayer player);
	public abstract boolean hasNoKB();
	public abstract int getTrueDamage();
	
  public static HashMap<LivingEntity, SkyblockEntity> livingEntity = new HashMap<>();
	public static ArrayList<LivingEntity> cooldowns = new ArrayList<>();


 static void updateDiguestedEntity(SkyblockEntity e) {
	LivingEntity entity = e.getEntity();
	float health;
	float maxhealth;

		maxhealth =e.getMaxHealth();
		health = e.getHealth();
	
	if (health <= 0) {
		entity.setCustomNameVisible(false);

		entity.setHealth(0);
		return;
	}
	@SuppressWarnings("deprecation")
	float estimated = (float) ((health / maxhealth) * entity.getMaxHealth());
	entity.setHealth(estimated);
	e.updateNameTag();
}

static String getBaseName(String name, int health, int maxhealth){
	return getBaseName(name, health, maxhealth, false);
}

static String getBaseName(String name, int health, int maxhealth, boolean isCorrupted){
	String str;
	if(isCorrupted)
		str = "§7[§8Lv?§7] §5§ka§5Corrupted " + name
				+ "§ka §a" +  health + "§8/§a" + maxhealth;
	else
		str = "§7[§8Lv?§7] §c" + name
				+ " §a" + health + "§8/§a" + maxhealth;
	return str;
}
public static String getBaseName(SkyblockEntity entity){
	if(entity instanceof Corruptable)
		return getBaseName(entity.getName(), entity.getHealth(), entity.getMaxHealth(), ((Corruptable)entity).isCorrupted());
	else
		return getBaseName(entity.getName(), entity.getHealth(), entity.getMaxHealth());
}

 public static void updateEntity(SkyblockEntity e) {
	LivingEntity entity = e.getEntity();
	
	if (Main.entitydead.containsKey(entity) && Main.entitydead.get(entity))
		return;
	if (DiguestMobsManager.entitys.containsKey(entity)) {
		updateDiguestedEntity(e);
		return;
	}
	

	float health;
	float maxhealth;

	
		maxhealth = e.getMaxHealth();
		health =e.getHealth();
	
	if (health <= 0) {
		entity.setCustomNameVisible(false);
		
			entity.setHealth(0);
		
		return;
	}
	@SuppressWarnings("deprecation")
	float estimated = (float) ((health / maxhealth) * entity.getMaxHealth());
	entity.setHealth(estimated);


	
	e.updateNameTag();

 }
 public static boolean isOnCooldown(LivingEntity entity){
	 return cooldowns.contains(entity);
 }
 public static void setOnCooldown(LivingEntity entity){
	 cooldowns.add(entity);
	 new BukkitRunnable() {
		 @Override
		 public void run() {
			 cooldowns.remove(entity);
		 }
	 }.runTaskLater(Main.getMain(), 15);

 }

}
