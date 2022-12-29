package me.CarsCupcake.SkyblockRemake.abilitys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class UntiedChopstick implements AbilityManager<EntityDamageByEntityEvent> {

	private static final HashMap<SkyblockPlayer, Integer> hits = new HashMap<>();

	@Override
	public boolean executeAbility(EntityDamageByEntityEvent event) {
		SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player)event.getDamager());
		if(!hits.containsKey(player)) {
			hits.put(player, 0);
		
		}
		
		hits.replace(player, hits.get(player) + 1);
		if(hits.get(player) >= 5) {
			hits.put(player, 0);
			player.launchProjectile(Arrow.class);
			
			ArrayList<LivingEntity> entitys = getNearest2Entitys(player);
			
			if(entitys.size() >= 2) {
				LivingEntity entity1 = entitys.get(0);
				LivingEntity entity2 = entitys.get(1);
				
				Vector dir1 = entity1.getEyeLocation().toVector().subtract(player.getEyeLocation().toVector());
				Vector dir2 = entity2.getEyeLocation().toVector().subtract(player.getEyeLocation().toVector());
				
				player.launchProjectile(Arrow.class, dir1);
				player.launchProjectile(Arrow.class, dir2);
				
				
			}else
				if(entitys.size() == 1) {
					LivingEntity entity1 = entitys.get(0);
					Vector dir1 = entity1.getEyeLocation().toVector().subtract(player.getEyeLocation().toVector());
					player.launchProjectile(Arrow.class, dir1);
					player.launchProjectile(Arrow.class);
				}else
					if(entitys.size() == 0) {
						player.launchProjectile(Arrow.class);player.launchProjectile(Arrow.class);
					}
			
		}
		
		
		
		return false;
	}
	private ArrayList<LivingEntity> getNearest2Entitys(SkyblockPlayer player){
		LinkedHashMap<LivingEntity,Double> entitys = new LinkedHashMap<>();
		ArrayList<Entity> alrentity = new ArrayList<>();
		ArrayList<LivingEntity> finalEntities = new ArrayList<>();
		List<Double> list = new ArrayList<>();
		for(Entity entity : player.getNearbyEntities(15, 15, 15)) {
			if(entity instanceof LivingEntity && entity.getType() != EntityType.ARMOR_STAND && !(entity instanceof Player) && !alrentity.contains(entity)) {
				alrentity.add(entity);
				entitys.put( (LivingEntity)entity,entity.getLocation().distance(player.getLocation()));
			}
		}
		
		  for (Map.Entry< LivingEntity,Double> entry : entitys.entrySet()) {
	            list.add(entry.getValue());
	        }
	        Collections.sort(list, new Comparator<Double>() {
	        	public int compare(Double o1, Double o2) {
	        		return (o1).compareTo(o2);
	        	}
	        });
	        
	        for (Double str : list) {
	            for (Entry<LivingEntity, Double> entry : entitys.entrySet()) {
	                if (entry.getValue().equals(str)) {
	                    finalEntities.add(entry.getKey());
	                }
	            }
	        }
              


				
				
	        
		
	
		
		return finalEntities;
	}
	

}
