package me.CarsCupcake.SkyblockRemake.Slayer.blaze.Entities.T3;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.HellionShield;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.FinalDamageDesider;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Slayer.blaze.Entities.Demons;
import me.CarsCupcake.SkyblockRemake.Slayer.blaze.Entities.DemonsGoal;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.Laser;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftWitherSkeleton;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

public class QuaziiT3 extends SkyblockEntity implements Demons, FinalDamageDesider {

	
	private int health = 5000000;
	private WitherSkeleton entity;
	
	private BlazeSlayerT3 baseSlayer;
	private final String name = "§3ⓆⓊⒶ§?ⒾⒾ";
	
	private BukkitRunnable tpRun;
	
	private ArmorStand timer;
	
	public boolean isInvincible = true;
	private HellionShield shield = HellionShield.Spirit;
	private int hits = 8;
	private BukkitRunnable run;

	@Override
	public boolean isInvinceble() {
		return isInvincible;
	}
	
	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 5000000;
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
		return 15000;
	}
	public void startAttack(SkyblockPlayer player) {
		entity.setTarget(player);
	}
	public void updateTimer() {
		String str;
		if(isInvincible)
			str = "§4§lIMMUNE ";
		else
			str = shield.getName() + " " + hits + " ";
		timer.setCustomName(str +"§c" +shortInteger(baseSlayer.time));
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
	SkyblockEntity.livingEntity.addEntity(entity, this);
	updateNameTag();
	timer = entity.getWorld().spawn(loc.add(0,0.7,0), ArmorStand.class, s ->{
		s.setGravity(false);
		s.setInvisible(true);
		s.setInvulnerable(true);
		s.setCustomNameVisible(true);
	
	});
	startTimer();
		Laser l1 = new Laser(entity.getLocation(), entity.getLocation().add(10,0,0));
		Vector ve = new Vector(10, 0, 0);
		HashMap<Laser, Double> items = new HashMap<>();
		items.put(l1, 0d);
		l1 = new Laser(entity.getLocation().add(0, entity.getEyeHeight() * 0.25, 0), entity.getLocation().add(10,entity.getEyeHeight()/2,0));
		items.put(l1, entity.getEyeHeight()* 0.25);
		l1 = new Laser(entity.getLocation().add(0, entity.getEyeHeight() * 0.75, 0), entity.getLocation().add(10,entity.getEyeHeight()/2,0));
		items.put(l1, entity.getEyeHeight()* 0.75);
		l1 = new Laser(entity.getEyeLocation(), entity.getEyeLocation().add(10,0,0));
		items.put(l1, entity.getEyeHeight());
	run = new BukkitRunnable() {
		int i = 0;
		final HashMap<SkyblockPlayer, Integer> hitCooldown = new HashMap<>();
		@Override
		public void run() {
			for (Laser l : items.keySet()){
				ve.rotateAroundY(0.025);
				l.getEnd().getEntity().teleport(l.getStart().getEntity().getLocation().add(ve));
				Location k = entity.getLocation().add(0, items.get(l), 0);
				l.getStart().getEntity().teleport(k);
				((Guardian)l.getStart().getEntity()).setTarget(l.getEnd().getEntity());
				Vector dir = ve.clone().normalize().multiply(0.3);
				Location lol = l.getStart().getEntity().getLocation();
				if(i > 10)
					for (double d = 0.3; d < 10; d += 0.3){
						Stream<Entity> e = lol.getWorld().getNearbyEntities(lol,0.3, 0.3, 0.3).
								stream().filter(c -> c instanceof Player p && SkyblockPlayer.getSkyblockPlayer(p) != null && !hitCooldown.containsKey(SkyblockPlayer.getSkyblockPlayer(p) ));
						e.forEach(b -> {
							hitCooldown.put(SkyblockPlayer.getSkyblockPlayer((Player) b), 0);
							Calculator c = new Calculator();
							SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) b);
							c.entityToPlayerDamage(QuaziiT3.this, player, new Bundle<>(0, (int)( Main.getPlayerStat(SkyblockPlayer.getSkyblockPlayer((Player) b), Stats.Health) * 0.7)));
							c.damagePlayer(player);
							c.showDamageTag(player);
						});

						lol = lol.add(dir);
					}
				else
					i++;
				for (SkyblockPlayer player : hitCooldown.keySet()){
					hitCooldown.replace(player, hitCooldown.get(player) + 1);
					if(hitCooldown.get(player) > 10)
						hitCooldown.remove(player);
				}
			}
		}
		@Override
		public void cancel(){
			super.cancel();
			items.keySet().forEach(Laser::stop);
		}
	};
	run.runTaskTimer(Main.getMain(), 1, 1);


	}



	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}



	@Override
	public HashMap<ItemManager,Integer> getGarantuedDrops(SkyblockPlayer player) {

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
	
	public void setBlazeSlayer(BlazeSlayerT3 slayer) {
		baseSlayer = slayer;
	}

	@Override
	public void kill() {
		try {
			run.cancel();
		}catch (Exception ignored){}
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

		if(health > 0) {
			if(!ItemHandler.hasPDC("attuned", player.getItemInHand(), PersistentDataType.STRING)) {
				player.sendMessage("§cYour hit was reduced by Hellion Shield");
				player.sendMessage("§cStrike using the " + shield.getName() + " §cattunement on your dagger!");
				return;
			}
			HellionShield hellionShield = getShieldFromString(ItemHandler.getPDC("attuned", player.getItemInHand(),
					PersistentDataType.STRING));
			if(hellionShield != shield) {
				player.sendMessage("§cYour hit was reduced by Hellion Shield");
				player.sendMessage("§cStrike using the " + shield.getName() + " §cattunement on your dagger!");
				return;
			}
			hits--;
			if(hits == 0){
				hits = 8;
				shield = getNext();
				if(baseSlayer.isTyphoeusAlive()){
					setInvinceble(true);
					baseSlayer.getTyphoes().setInvinceble(false);
				}
			}
			updateTimer();

		}
		
	}
	public void setInvinceble(boolean bol) {
		isInvincible = bol;
		updateTimer();
		if(!isInvincible)
			startAttack(baseSlayer.owner);
	}

	@Override
	public int getTrueDamage() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getFinalDamage(SkyblockPlayer player, double damage) {
		if(isInvincible)
			return 0;

		if(!ItemHandler.hasPDC("attuned", player.getItemInHand(), PersistentDataType.STRING))
			return damage * 0.01;
		HellionShield hellionShield = getShieldFromString(ItemHandler.getPDC("attuned", player.getItemInHand(),
				PersistentDataType.STRING));
		if(hellionShield == shield)
			return damage;

		return 0.01 * damage;
	}
	private HellionShield getShieldFromString(String str){
		switch (str){
			case "ashen" -> {
				return HellionShield.Ashen;
			}
			case "auric" -> {
				return HellionShield.Auric;
			}
			case "crystal" -> {
				return HellionShield.Crystal;
			}
			case "spirit" -> {
				return HellionShield.Spirit;
			}
			default -> {

				return null;
			}
		}
	}
	private HellionShield getNext(){
		if (Objects.requireNonNull(shield) == HellionShield.Spirit) {
			return HellionShield.Crystal;
		}
		return HellionShield.Spirit;
	}

}
