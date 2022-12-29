package me.CarsCupcake.SkyblockRemake.abilitys;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class ArcaneZap implements AbilityManager<PlayerInteractEvent> {

	
	private boolean hasRightRune(LivingEntity entity, ArcaneRune rune) {
		
		if(!entity.getScoreboardTags().isEmpty() && entity.getScoreboardTags().contains("rune:" + rune.toString().toLowerCase())) 
			return true;
		
		return false;
	}
	
	@Override
	public boolean executeAbility(PlayerInteractEvent event) {

		
		Vector dir = event.getPlayer().getEyeLocation().getDirection();
		Location loc = event.getPlayer().getEyeLocation();
		
		SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
		Particle part = ArcaneRune.getPlayerRune(player).getParticle();
		for(int i = 0; i < 30+3; i++) {
			dir.multiply(1);
			loc = loc.add(dir);
			Location l = loc.clone();
			
			loc.getWorld().spawnParticle(part, l, 0, 0, 0, 0, 0, null);
			loc.getWorld().spawnParticle(part, l.subtract(0, 1, 0), 0, 0, 0, 0, 0, null);
			loc.getWorld().spawnParticle(part, l.subtract(0, 1, 0), 0, 0, 0, 0, 0, null);
			for(Entity entity : l.getWorld().getNearbyEntities(loc, 1, 1, 1)) {
				if(entity instanceof LivingEntity && !(entity instanceof Player) && entity.getType() != EntityType.ARMOR_STAND) {
					
					ArcaneRune rune = ArcaneRune.getPlayerRune(player);
					if(hasRightRune((LivingEntity) entity, rune)&&ArcaneVision.hasArcaneVision(player)) {
						ArcaneVision.getArcaneVision(player).addStack();
						if(ArcaneVision.getArcaneVision(player).stacks == 10) {
							event.getPlayer().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, entity.getLocation().add(0, 0.5, 0), 6, 0, 0, 0, 6, null, true);
							event.getPlayer().getWorld().playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 2);
						}
					}

					
					double mana = Main.playermanacalc(player);
					double baseDamage = 8000;
					float abilitidmg = Main.playerabilitydamagecalc(player);
					double damage;
					
					
					
					
					double mult = 1;
					if(ArcaneVision.hasArcaneVision(player)) {
						mult += ArcaneVision.getArcaneVision(player).stacks * 0.02;
					}
					
					 if(entity.getScoreboardTags().contains("abilityimun"))
		     			 damage = 0;
					 else
		     		 damage= (baseDamage*(1+(((mana-100)/100)*0.5)*(1+(abilitidmg/100))))*mult;
		     		
		     		 
		     		 Main.currentityhealth.replace(entity, Main.currentityhealth.get(entity) - (int)damage);
		     		 if( Main.currentityhealth.get(entity)  <= 0) {
		     			 ((LivingEntity) entity).addScoreboardTag("killer:" + player.getName());
		     			 ((LivingEntity) entity).addScoreboardTag("abilitykill");
		     			 
		     			 
		     		 }
		     		 Main.updateentitystats((LivingEntity)entity);
					ArmorStand stand = (ArmorStand) loc.getWorld().spawn(loc, ArmorStand.class, armorstand ->{
						armorstand.setVisible(false);
					
					armorstand.setGravity(false);
					   
					armorstand.setCustomNameVisible(true);
						
					armorstand.setInvulnerable(true);
					
					armorstand.setCustomName("§7" + (int)damage);
					armorstand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
					armorstand.addScoreboardTag("damage_tag");
					armorstand.setArms(false);
					armorstand.setBasePlate(false);
					armorstand.setMarker(true);});
					Main.getMain().killarmorstand(stand);
				  stand.setCustomNameVisible(true);
					
					return true;
				}
					
			}
			if(!loc.getBlock().isPassable())
				return true;
				
			dir = loc.getDirection();
		}

		
		return false;
	}


	
	//mode particles:
	//virtuoso: witch
	//mediator: flame
	//Defender: enchanted_hit

}
