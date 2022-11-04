package me.CarsCupcake.SkyblockRemake.abilitys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class Dominus implements FullSetBonus{
	

	private SkyblockPlayer player;
	public int stacks;
	private BukkitRunnable stackRunner;
	//in seconds
	private int stackRuntime;
	public static HashMap<Player, Dominus> playerDominus = new HashMap<>();
	
	public static void getKillEvent(EntityDeathEvent event) {
		if(event.getEntity().getKiller() != null) {
			if(playerDominus.containsKey(event.getEntity().getKiller())) {
				playerDominus.get(event.getEntity().getKiller()).setKill(event);
			}
			}else
				if(!event.getEntity().getScoreboardTags().isEmpty())
					for(String str : event.getEntity().getScoreboardTags())
						if(str.startsWith("killer:"))
							if(playerDominus.containsKey(Bukkit.getPlayer(str.split(":")[1])))
							{
								if(event.getEntity().getScoreboardTags().contains("abilitykill"))
									return;
								
								playerDominus.get(Bukkit.getPlayer(str.split(":")[1])).setKill(event);
								return;
							}
			
	}
	public static void setEvent() {
		Main.getMain().getServer().getPluginManager().registerEvents(new DominusListener(), Main.getMain());
	}
	
	public void setKill(EntityDeathEvent event) {

		if(player.bonusAmounts.get(Bonuses.Dominus) == 2)
			stackRuntime = 4;
		else if(player.bonusAmounts.get(Bonuses.Dominus) == 3)
			stackRuntime = 7;
		else
			stackRuntime = 10;
		
		if(stackRunner != null )
			try {
		stackRunner.cancel();
		}catch (Exception e) {
		
		}
		stackRunner = new BukkitRunnable() {
			
			@Override
			public void run() {
				
				stackRuntime -= 1;
				
				if(stackRuntime == 0) {
				stacks -= 1;
				Main.updatebar(player);
				if(stacks == 0)
					stackRunner.cancel();
				else {
					if(player.bonusAmounts.get(Bonuses.Dominus) == 2)
						stackRuntime = 4;
					else if(player.bonusAmounts.get(Bonuses.Dominus) == 3)
						stackRuntime = 7;
					else
						stackRuntime = 10;
				}
					
				}
				
			}
		};
		stackRunner.runTaskTimer(Main.getMain(), 0, 20);
		
		
		Main.updatebar(player);
		
		if(stacks < 10)
			stacks += 1;
		if(stacks == 10)
			dominusStrike(event.getEntity().getLocation(),event.getEntity());
		
	}
	
	
	public void dominusStrike(Location loc, Entity killedEntity) {
		player.playSound(player.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
		
		int yaw = new Random().nextInt(360) - 180;

		Vector dir = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), yaw, 0).getDirection();
		Location tempLocation = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), yaw, 0);
		dir.multiply(3);
		Location loc1 = tempLocation.add(dir);
		dir = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), yaw, 0).getDirection();
		dir.multiply(-3);
		Location loc2 = loc.add(dir);
		loc2.setY(loc.getY() + 2);
		
		Vector a = loc1.toVector();
		Vector b = loc2.toVector();

		Vector between = b.subtract(a); //get the vector between two points
		double length = between.length(); //get the length of the distance
		between.normalize().multiply(0.4); //normalize our vector and specify the spacing
		double steps = (double)((double)length / 0.4); //determine number of steps
		ArrayList<Entity> alrHitEntitys = new ArrayList<>();
		alrHitEntitys.add(killedEntity);
		for (int i = 0; i < steps; i++) { //iterate up to but not beyond point b
		    Vector point = a.add(between);
		    
		    Location l = new Location(loc.getWorld(), point.getX(), point.getY(), point.getZ());
		   loc.getWorld().spawnParticle(Particle.FLAME,l,1,0,0,0,0);
		   
		   
		   
		   for(Entity entity: player.getWorld().getNearbyEntities(  player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(i)),1, 1, 1)) {
				if(entity instanceof LivingEntity && !(entity instanceof ArmorStand) && entity != player&& !alrHitEntitys.contains(entity) && !(entity instanceof Player)) {
					LivingEntity e =  (LivingEntity) entity;
					e.damage(0.0000001);
					double stre = Main.playerstrengthcalc(player);
					double dmg = Main.weapondamage(player.getItemInHand());
					double damage = (5 + (float)dmg) * (1+((float)stre/100));

					if(Main.currentityhealth.get(e) != null)
					if(Main.currentityhealth.get(e) - damage < 0) {
						Main.currentityhealth.replace(e, 0);
					}else
						Main.currentityhealth.replace(e,(int) (Main.currentityhealth.get(e) -damage));
					Main.updateentitystats(e);
					alrHitEntitys.add(entity);
					final int FINAL_DAMAGE = (int)damage;
					Location lol = new Location(e.getWorld(), e.getLocation().getX() ,e.getLocation().getY() + 0.5 , e.getLocation().getZ());
					ArmorStand stand = (ArmorStand) e.getWorld().spawn(lol, ArmorStand.class, armorstand ->{armorstand.setVisible(false);
					armorstand.setGravity(false);
					   
					armorstand.setCustomNameVisible(true);
						
					armorstand.setInvulnerable(true);
						
							
							armorstand.setCustomName("§7" + FINAL_DAMAGE);
							
						
						armorstand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
						armorstand.addScoreboardTag("damage_tag");
						armorstand.setArms(false);
						armorstand.setBasePlate(false);
						armorstand.setMarker(true);});
						Main.getMain().killarmorstand(stand);
					  stand.setCustomNameVisible(true);
					
				}
			}
		   
		   
		   
		}
		
		
		
				}
	
	
	

	@Override
	public void start() {
	
		playerDominus.put(player, this);

		
	}

	@Override
	public void stop() {
		playerDominus.remove(player);
		
	}

	@Override
	public int getPieces() {
		
		return 2;
	}

	@Override
	public void setPlayer(SkyblockPlayer player) {

		this.player = player;
		
	}

	@Override
	public Bonuses getBonus() {
		
		return Bonuses.Dominus;
	}
	@Override
	public int getMaxPieces() {
		return 4;
	}
	
	 

}
