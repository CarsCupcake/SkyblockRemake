package me.CarsCupcake.SkyblockRemake.AccessoryBag.Powers;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;

public class MaxwellListender implements Listener{
@EventHandler
public void invClick(InventoryClickEvent event) {
	 if (!event.getView().getTitle().contains("Accessory Bag Thaumatologist"))
		  return;
	 event.setCancelled(true);
	  if (event.getCurrentItem() == null)
		  return;
	  if (event.getCurrentItem().getItemMeta() == null)
		  return;
	  
	  Player player = (Player) event.getWhoClicked();
	  
	  
	  if(event.getClickedInventory().getType() == InventoryType.PLAYER)
		  return;
	  
	  
	  if(event.getCurrentItem().getItemMeta().getPersistentDataContainer() != null && event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "power"), PersistentDataType.STRING)
			  != null && Powers.powers.containsKey(event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "power"), PersistentDataType.STRING))){
				 Powers power = Powers.powers.get(event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "power"), PersistentDataType.STRING));
				 if(Powers.activepower.get(player) == power)
					 return;
				 power.setActive(player);
				 player.openInventory(MaxwellInv.getMainInventory(player));
				 player.updateInventory();
				 return;
			  }
	  
	  
	  if(event.getSlot() == 50) {
		  player.openInventory(MaxwellInv.LearnPowerFromStone(player));
		  player.updateInventory();
		  return;
	  }
	  
}
}
