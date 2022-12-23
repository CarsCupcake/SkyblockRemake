package me.CarsCupcake.SkyblockRemake;

import java.util.ArrayList;
import java.util.HashMap;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignGUI;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SearchTopic;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.cmd.itemCMD;

@SuppressWarnings("deprecation")
public class ItemsSearch implements Listener{
	private static HashMap<SkyblockPlayer, ArrayList<Inventory>>inventorys = new HashMap<>();
	
	
	@EventHandler
	public void getSearchInput(PlayerChatEvent event) {
		SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
		if(player.isSearching() && player.getSearching() == SearchTopic.CustomItems) {
			buildInventory(player, event.getMessage());
		event.setCancelled(true);	
		}
	}
	public synchronized static void buildInventory(SkyblockPlayer player, String input) {
		player.setSearching(null);
		ArrayList<ItemManager> managers = new ArrayList<>();
		
		
		for(ItemManager manager : itemCMD.customItems) {
			String name = manager.name;
			if(name.toLowerCase().contains(input.toLowerCase()))
				managers.add(manager);
		}
		
		
		
		
		
		
		if(managers.isEmpty()) {
			player.sendMessage("§cNo item with that name found");
			return;}
		
		ArrayList<Inventory> items = new ArrayList<>();
		
		
		int invs =1+  (managers.size() / 45);
		
		
		int list = 0;
		int invCount = 1;
		for(invCount = 1; invCount <= invs; invCount++) {
			Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Custom Items Search: "+ input+ " - Page " + invCount);
			ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
			ItemMeta meta = item.getItemMeta();
			PersistentDataContainer data = meta.getPersistentDataContainer();
			 meta.setDisplayName(" ");
			 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			item.setItemMeta(meta);
			for(int i = 45; i < 53; i++) {
				inv.setItem(i, item);
			}
			if(invCount != invs) {
			item.setType(Material.ARROW);
			
			data.set(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER, invCount);
			meta.setDisplayName("§aNext Page");
			 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			item.setItemMeta(meta);
			inv.setItem(53, item);
			}else {
				data.set(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER, invCount);
				item.setItemMeta(meta);
				inv.setItem(53, item);
			}
			if(invCount != 1) {
				item.setType(Material.ARROW);
			meta.setDisplayName("§aLast Page");
			 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			item.setItemMeta(meta);
			inv.setItem(45, item);
			}
			item.setType(Material.OAK_SIGN);
			meta.setDisplayName("§aSearch");
			 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			item.setItemMeta(meta);
			inv.setItem(49, item);
			int i = 0;
			
			while(i < 45){
			
				if(!(list >= managers.size())) {
					if(!managers.get(list).hasEdition)
					inv.setItem(i, Main.item_updater(managers.get(list).getRawItemStack(),null) );
					else
						i -= 1;
					}
				i++;
			
				
				list++;
			}
			
			items.add(inv);
			
		}
		inventorys.put(player, items);
		player.openInventory(items.get(0));
	
	}
	
	
	@EventHandler
	public void ItemsMenu(InventoryClickEvent event) {
		  if (!event.getView().getTitle().contains("Custom Items Search:"))
			  return;
		  if (event.getCurrentItem() == null)
			  return;
		  if (event.getCurrentItem().getItemMeta() == null)
			  return;
		  
		  SkyblockPlayer player =SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked()) ;
		  event.setCancelled(true);
		  
		  if(event.getClickedInventory().getType() == InventoryType.PLAYER)
			  return;
		  
		  
		  
		  
		  int page = event.getView().getTopInventory().getItem(53).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER); 
		  
		  if(event.getSlot() == 53) {
				 
			  if(page  < inventorys.get(player).size()) {
				  
  player.openInventory( inventorys.get(player).get(page));
			  player.updateInventory();}
			  return;
		  }
		  if(event.getSlot() == 49) {

			  new SignGUI(new SignManager(), e -> Bukkit.getScheduler().runTask(Main.getMain(), ()-> ItemsSearch.buildInventory(SkyblockPlayer.getSkyblockPlayer(player), e.lines()[0] + e.lines()[1])))
					  .withLines("", "", "^^^^^^^^^^^^^^^", "Enter Item Name")
					  .open(SkyblockPlayer.getSkyblockPlayer(player));
		  }
		  if(event.getSlot() == 45) {
			  
			  if(page != 1) {
			
			  player.openInventory( inventorys.get(player).get(page - 2));
			  player.updateInventory();
			  }
			  return;
		  }
		  if(event.getSlot() > 45 ) {
				 return; 
			  }
		  if (event.getCurrentItem().getType() == Material.AIR)
			  return;
		player.getInventory().addItem(Main.item_updater(Items.SkyblockItems.get(ItemHandler.getPDC("id",event.getCurrentItem(),PersistentDataType.STRING)).createNewItemStack(), SkyblockPlayer.getSkyblockPlayer(player)));
		  player.updateInventory();
	
	}

}
