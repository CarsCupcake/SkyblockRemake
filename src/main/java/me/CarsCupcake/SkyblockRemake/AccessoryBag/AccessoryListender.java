package me.CarsCupcake.SkyblockRemake.AccessoryBag;


import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Configs.AccessoryBag;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;


public class AccessoryListender implements Listener{
@EventHandler
public void accessoryBagSiteSwapping(InventoryClickEvent event) {
	 if (!event.getView().getTitle().contains("Accessory Bag ("))
		  return;
	  if (event.getCurrentItem() == null)
		  return;
	  if (event.getCurrentItem().getItemMeta() == null)
		  return;
	  
	  Player player = (Player) event.getWhoClicked();
	  
	  
	  if(event.getClickedInventory().getType() == InventoryType.PLAYER) {
		  if(Items.SkyblockItems.get( event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING) ).type == ItemType.Accessory)
		  return;
		  else
			  event.setCancelled(true);
		  player.sendMessage("§c§lHey! §r§cYou are only able to put Accessorys in!");
		  return;
		  }
	  
	  if(event.getView().getTopInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING) != null && event.getView().getTopInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING).equals("true")) {
		  event.setCancelled(true);
	  }
	  
	  
	  int page = event.getView().getTopInventory().getItem(53).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER); 
	  
	  if(event.getSlot() == 53) {
		  event.setCancelled(true);
		  if(page  < AccessoryInventory.activeInvs.get(player).invs.size()) {
			  saveInventory(player, page, event.getView().getTopInventory());
player.openInventory(AccessoryInventory.activeInvs.get(player).invs.get(page));
		  player.updateInventory();}
		  return;
	  }
	  
	  if(event.getSlot() == 45) {
		  event.setCancelled(true);
		  if(page != 1) {
		
			  
		  saveInventory(player, page, event.getView().getTopInventory());
		  player.openInventory(AccessoryInventory.activeInvs.get(player).invs.get(page - 2));
		  player.updateInventory();
		  }
		  return;
	  }
	  if(event.getSlot() > 45 ) {
		  event.setCancelled(true);
			 
		  }
	 
	  
}



private void saveInventory(Player player, int page, Inventory inv) {
	int startSlot = (45 * page) - 45;
	int invslot=0;
	AccessoryBag.reload();
	for(int i = startSlot; i < startSlot + 45; i++) {
		String baseString = player.getUniqueId() + ".SLOT_" + i;
		if(inv.getItem(invslot) != null && !( inv.getItem(invslot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING) != null && inv.getItem(invslot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING).equals("true")) 
			) {
			String id = inv.getItem(invslot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING);
			Integer recomed = inv.getItem(invslot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "recomed"), PersistentDataType.INTEGER);
			AccessoryBag.get().set(baseString, true);
			AccessoryBag.get().set(baseString + ".id", id);
			if(recomed == null || recomed == 0) {
				AccessoryBag.get().set(baseString + ".recom", false);
			}else {
				AccessoryBag.get().set(baseString + ".recom", true);
			}
		}else {
			if(inv.getItem(invslot) != null && inv.getItem(invslot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING) != null && inv.getItem(invslot).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "border"), PersistentDataType.STRING).equals("true")) 
			 {
				
			}else
			AccessoryBag.get().set(baseString, false);
		}
		
		invslot++;
	}
	AccessoryBag.save();
	AccessoryBag.reload();
	Main.calculateMagicalPower(player);
}

@EventHandler
public void saveInventoryOnCLose(InventoryCloseEvent event) {
	 if (!event.getView().getTitle().contains("Accessory Bag ("))
		  return;
	
	  
	  Player player = (Player) event.getPlayer();
	  int page = event.getView().getTopInventory().getItem(53).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER); 
	  saveInventory(player, page, event.getView().getTopInventory());
}




}
