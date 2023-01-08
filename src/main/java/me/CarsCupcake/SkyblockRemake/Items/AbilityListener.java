package me.CarsCupcake.SkyblockRemake.Items;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.SkyblockDamagePlayerToEntityExecuteEvent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class AbilityListener implements Listener {
	@EventHandler
	public void listener(PlayerInteractEvent event) {
		AbilityManager.abilityTrigger(event);

	}
	@EventHandler
	public void listener(EntityDamageByEntityEvent event) {
		AbilityManager.abilityTrigger(event);
	}
	@EventHandler
	public void a(DamagePrepairEvent event){
		AbilityManager.abilityTrigger(event);
	}
	@EventHandler
	public void a(SkyblockDamagePlayerToEntityExecuteEvent event){
		AbilityManager.abilityTrigger(event);
	}
	
	@EventHandler
	public void armorEquipEvent(InventoryClickEvent event) {

		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(event.getClickedInventory() != null && event.getClickedInventory() instanceof PlayerInventory)
					if((event.getSlot()>35 && event.getSlot() < 40) || (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {

						checkArmor(SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked()));
						new BukkitRunnable() {
							@Override
							public void run() {
								SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked());
								player.getEquipment().setHelmet(Main.item_updater(player.getEquipment().getHelmet() , player));
								player.getEquipment().setChestplate(Main.item_updater(player.getEquipment().getChestplate() , player));
								player.getEquipment().setLeggings(Main.item_updater(player.getEquipment().getLeggings() , player));
								player.getEquipment().setBoots(Main.item_updater(player.getEquipment().getBoots() , player));
								if(event.getCurrentItem() != null)
									event.setCurrentItem(Main.item_updater(event.getCurrentItem(), player));
							}
						}.runTaskLater(Main.getMain(), 2);
					}
			}
		}.runTaskLater(Main.getMain(), 1);
		
	}
	
	public static void checkArmor(SkyblockPlayer player) {
		ArrayList<ItemManager> armorPieces = new ArrayList<>();
		if(player.getEquipment().getHelmet() != null && player.getEquipment().getHelmet().getItemMeta() != null && player.getEquipment().getHelmet().getItemMeta().getPersistentDataContainer() != null) {
			ItemManager manager = Items.SkyblockItems.get(player.getEquipment().getHelmet().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
		armorPieces.add(manager);
		player.setArmorPiece(ItemType.Helmet, manager);
		}
		if(player.getEquipment().getChestplate() != null && player.getEquipment().getChestplate().getItemMeta() != null && player.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer() != null) {
			ItemManager manager = Items.SkyblockItems.get(player.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
		armorPieces.add(manager);
		player.setArmorPiece(ItemType.Chestplate, manager);
		}
		if(player.getEquipment().getLeggings() != null && player.getEquipment().getLeggings().getItemMeta() != null && player.getEquipment().getLeggings().getItemMeta().getPersistentDataContainer() != null) {
			ItemManager manager = Items.SkyblockItems.get(player.getEquipment().getLeggings().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
		armorPieces.add(manager);
		player.setArmorPiece(ItemType.Leggings, manager);
		}
		if(player.getEquipment().getBoots() != null && player.getEquipment().getBoots().getItemMeta() != null && player.getEquipment().getBoots().getItemMeta().getPersistentDataContainer() != null) {
			ItemManager manager = Items.SkyblockItems.get(player.getEquipment().getBoots().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
		armorPieces.add(manager);
		player.setArmorPiece(ItemType.Boots, manager);
		}
		player.bonusAmounts.clear();
		for(ItemManager manager : armorPieces) {
			if(manager == null)
				continue;
			if(manager.bonus != null) {
				
				if(player.bonusAmounts.containsKey(manager.bonus)) {
					player.bonusAmounts.replace(manager.bonus, player.bonusAmounts.get(manager.bonus) + 1);
					
				}
				else
					player.bonusAmounts.put(manager.bonus, 1);
			}
		}
		if(player.equipmentManager.necklace != null)
			if(player.equipmentManager.necklace.getFirst().bonus != null){
				if(player.bonusAmounts.containsKey(player.equipmentManager.necklace.getFirst().bonus)) {
					player.bonusAmounts.replace(player.equipmentManager.necklace.getFirst().bonus, player.bonusAmounts.get(player.equipmentManager.necklace.getFirst().bonus) + 1);
				}
				else
					player.bonusAmounts.put(player.equipmentManager.necklace.getFirst().bonus, 1);
			}

		if(player.equipmentManager.cloak != null)
			if(player.equipmentManager.cloak.getFirst().bonus != null){
				if(player.bonusAmounts.containsKey(player.equipmentManager.cloak.getFirst().bonus)) {
					player.bonusAmounts.replace(player.equipmentManager.cloak.getFirst().bonus, player.bonusAmounts.get(player.equipmentManager.cloak.getFirst().bonus) + 1);
				}
				else
					player.bonusAmounts.put(player.equipmentManager.cloak.getFirst().bonus, 1);
			}

		if(player.equipmentManager.belt != null)
			if(player.equipmentManager.belt.getFirst().bonus != null){
				if(player.bonusAmounts.containsKey(player.equipmentManager.belt.getFirst().bonus)) {
					player.bonusAmounts.replace(player.equipmentManager.belt.getFirst().bonus, player.bonusAmounts.get(player.equipmentManager.belt.getFirst().bonus) + 1);
				}
				else
					player.bonusAmounts.put(player.equipmentManager.belt.getFirst().bonus, 1);
			}

		if(player.equipmentManager.fist != null)
			if(player.equipmentManager.fist.getFirst().bonus != null){
				if(player.bonusAmounts.containsKey(player.equipmentManager.fist.getFirst().bonus)) {
					player.bonusAmounts.replace(player.equipmentManager.fist.getFirst().bonus, player.bonusAmounts.get(player.equipmentManager.fist.getFirst().bonus) + 1);
				}
				else
					player.bonusAmounts.put(player.equipmentManager.fist.getFirst().bonus, 1);
			}
		if(!player.activeBonuses.isEmpty()) {
			for(FullSetBonus bonus : player.activeBonuses)
				bonus.stop();
		player.activeBonuses.clear();	
		}
		
		player.bonusAmounts.forEach((bonus, amount)->{
				if(bonus.getBonus(player).getPieces() <= amount) {
					FullSetBonus bon = bonus.getBonus(player);
					player.activeBonuses.add(bon);
					bon.start();
				}
		});
		
		
	}
	
	
	
	
}
