package me.CarsCupcake.SkyblockRemake.Items;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;

public class SpawnEggEvents implements Listener {

	
	@EventHandler
	public void SpawnEggUse(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getHand() != null && event.getHand().equals(EquipmentSlot.HAND)) {
				
				if(event.getItem() != null 
						&&  event.getItem().getItemMeta() != null 
						&& event.getItem().getItemMeta().getPersistentDataContainer() != null
						&& event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING) != null) {
					PersistentDataContainer data = event.getItem().getItemMeta().getPersistentDataContainer();
					if(!event.getPlayer().getName().equals("CarsCupcake"))
						return;
					Location spawnLocation;
					if(event.getClickedBlock().isPassable()) {
					
					 spawnLocation = event.getClickedBlock().getLocation().add(0.5, 0, 0.5);
					}else {
						 spawnLocation = event.getClickedBlock().getRelative(event.getBlockFace()).getLocation().add(0.5, 0.5, 0.5);
					}
					switch(data.get(new NamespacedKey(Main.getMain(), "type"), PersistentDataType.STRING)) {
					case "RevT1":
						event.setCancelled(true);
						SpawnEggEntitys.SummonT1Rev(spawnLocation,event.getPlayer().getName());
						break;
					case "RevT2":
						event.setCancelled(true);
						SpawnEggEntitys.SummonT2Rev(spawnLocation,event.getPlayer().getName());
						break;
					case "RevT3":
						event.setCancelled(true);
						SpawnEggEntitys.SummonT3Rev(spawnLocation,event.getPlayer().getName());
						break;
					case "RevT4":
						event.setCancelled(true);
						SpawnEggEntitys.SummonT4Rev(spawnLocation,event.getPlayer().getName());
						break;
					case "RevMiniT3":
						event.setCancelled(true);
						SpawnEggEntitys.SummonRevT3MiniBoss1(spawnLocation);
						break;
					case "RevMiniT4_1":
						event.setCancelled(true);
						SpawnEggEntitys.SummonRevT4MiniBoss1(spawnLocation);
						break;
					case "RevMiniT4_2":
						event.setCancelled(true);
						SpawnEggEntitys.SummonRevT4MiniBoss2(spawnLocation);
						break;
					case "VoidT2":
						event.setCancelled(true);
						SpawnEggEntitys.SummonVoidT2(spawnLocation, event.getPlayer().getName());
						break;
					}
				}
			}
		}
	}
}
