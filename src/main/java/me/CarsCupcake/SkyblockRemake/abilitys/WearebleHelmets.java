package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;

public class WearebleHelmets implements Listener{
@SuppressWarnings("deprecation")
@EventHandler 
public void inventoryClickEvent(InventoryClickEvent event) {

	if(!event.getView().getTitle().equals("Crafting")) {

		return;}
	
	if(event.getClickedInventory() != null &&event.getClickedInventory().getType() != null && (event.getClickedInventory().getType() == InventoryType.CREATIVE|| event.getClickedInventory().getType() == InventoryType.PLAYER) ) {

		if(event.getClick() == ClickType.LEFT || (event.getClick() == ClickType.CREATIVE && event.isLeftClick())) {
			event.setCancelled(true);

			if(event.getSlot() == 39) {
				if(event.getCursor() != null) {
					if(event.getCursor().getItemMeta() != null && event.getCursor().getItemMeta().getPersistentDataContainer() != null && event.getCursor().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING) != null) {
						ItemManager manager = Items.SkyblockItems.get(event.getCursor().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
						if(manager.type == ItemType.Helmet && manager.material != Material.LEATHER_HELMET && manager.material != Material.IRON_HELMET && manager.material != Material.GOLDEN_HELMET && manager.material != Material.DIAMOND_HELMET && manager.material != Material.NETHERITE_HELMET&& manager.material != Material.PLAYER_HEAD) {

							ItemStack currItem= event.getCursor().clone();
							if(event.getWhoClicked().getEquipment().getHelmet() != null)
								event.setCursor(event.getWhoClicked().getEquipment().getHelmet());
							else
								event.setCursor(null);

							Player player = (Player)event.getWhoClicked();
							player.getEquipment().setHelmet(currItem);

							player.updateInventory();
							player.getEquipment().getHelmet();
							return;
							
						}
					}
				}
			}
			event.setCancelled(false);
		}else {
			if((event.getClick() == ClickType.SHIFT_LEFT || (event.getClick() == ClickType.CREATIVE && event.isShiftClick())) && event.getView().getTitle().equals("Crafting") && event.getInventory().getType() == InventoryType.CRAFTING) {
				
				
				
				event.setCancelled(true);
				ItemStack item = event.getWhoClicked().getInventory().getItem(event.getSlot());
				if(item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer() != null &&item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING) != null) {
					ItemManager manager = Items.SkyblockItems.get(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
					if(manager.type == ItemType.Helmet && manager.material != Material.LEATHER_HELMET && manager.material != Material.IRON_HELMET && manager.material != Material.GOLDEN_HELMET && manager.material != Material.DIAMOND_HELMET && manager.material != Material.NETHERITE_HELMET&& manager.material != Material.PLAYER_HEAD)
				{
				if(event.getWhoClicked().getEquipment().getHelmet() == null ) {
					event.getWhoClicked().getEquipment().setHelmet(event.getWhoClicked().getInventory().getItem(event.getSlot()));
					event.getWhoClicked().getInventory().setItem(event.getSlot(), null);
				}
				}
			}
				event.setCancelled(false);
				}
		}
		
		if(event.getCurrentItem() != null) {
			event.setCancelled(true);
			
			event.setCurrentItem(Main.item_updater(event.getCurrentItem(), SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())));
			
			event.setCancelled(false);
		}
		
		}
	
}
}
