package me.CarsCupcake.SkyblockRemake.abilitys;

import java.util.List;

import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;

public class Hyperion_WitherImpact implements AbilityManager<PlayerInteractEvent>{

	@Override
	public boolean executeAbility(PlayerInteractEvent event) {
		

		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		ItemMeta meta = item.getItemMeta();
		PersistentDataContainer data = meta.getPersistentDataContainer();
		double cd = Main.playercdcalc(player);
		event.getPlayer().setVelocity(event.getPlayer().getVelocity().setY(0));
		if(event.getPlayer().getEyeLocation().add(0,1,0).getBlock().isPassable()) {
			event.getPlayer().teleport(player.getLocation().add(0,1,0));
		}
		teleport(event.getPlayer());
		event.getPlayer().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getPlayer().getLocation().add(0, 0.5, 0), 6, 0, 0, 0, 6, null, true);
		
		if(Main.absorbtion.containsKey(player)) {
			
				Main.absorbtion.put(player, (int)(cd * 1.5));
				Main.getMain().absorbtioneffect(player, 5);
			
		}else {
			Main.absorbtion.put(player, (int)(cd*1.5));
			Main.getMain().absorbtioneffect(player, 5);
		}
		Location loc = event.getPlayer().getLocation();
		List<Entity> close = (List<Entity>) event.getPlayer().getWorld().getNearbyEntities(loc, 6, 6, 6);
		close.remove(event.getPlayer());
		int i = 0;
		double damage = 0;
		for(Entity target : close){
	         if(target instanceof LivingEntity && !(target instanceof Player) && target.getType() != EntityType.ARMOR_STAND && !Main.entitydead.containsKey(target)  ){
				 Calculator calculator = new Calculator();
				 calculator.setMagic("Implosion", 0.3);
				 calculator.playerToEntityMagicDamage(SkyblockPlayer.getSkyblockPlayer(player), (LivingEntity) target, 10000);
				 calculator.damageEntity((LivingEntity) target, SkyblockPlayer.getSkyblockPlayer(player));
				 if(calculator.damage > 0)
				   calculator.showDamageTag(target);
				 i++;
				 damage += calculator.damage;


		
	         }

			 }if(i > 0){
				Calculator c = new Calculator();
				c.damage = damage;
				c.setMagic("Implosion");
				c.sendMagicMessage(i, SkyblockPlayer.getSkyblockPlayer(player));
		}
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.4f, 1.2f);
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 0.2f, 2f);
		return false;
	}

	public  void teleport(Player player) {
		Location mainLoc = player.getEyeLocation();
		for(int i=1;i<=10*2;i++) {
			Location loc = player.getLocation();
			Vector dir = loc.getDirection();
			dir.normalize();
			dir.multiply(0.5); //1 blocks a way
			mainLoc.add(dir);

			if(mainLoc.getBlock().isEmpty() || mainLoc.getBlock().isLiquid() || mainLoc.getBlock().isPassable()) {
				player.teleport(mainLoc);
			}else break;}}


}
