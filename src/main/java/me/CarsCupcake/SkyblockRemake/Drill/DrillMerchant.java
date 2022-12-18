package me.CarsCupcake.SkyblockRemake.Drill;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;


public class DrillMerchant implements Listener{
	
	@EventHandler
	public void AnvilOnClick(InventoryClickEvent event) {
		
		if(event.getView().getTitle() == null || !event.getView().getTitle().contains("Drill Anvil")) {
			
			return;
		}
		event.setCancelled(true);
		final int slot;
		if(event.getClickedInventory() != null &&event.getClickedInventory().getType() != InventoryType.PLAYER) {
			slot = event.getSlot();
		}else {
			slot = -1;
		}
		if(slot != 29 && slot != 33 && slot != -1)
		event.setCancelled(true);
		else
			event.setCancelled(false);
		Inventory inv = event.getView().getTopInventory();
		if(slot != 13 && !event.getView().getTopInventory().getItem(13).equals( DrillMechanicInv.DenieAnvil()) && !((inv.getItem(33) != null && inv.getItem(33).getType() != Material.AIR) && (inv.getItem(29) != null && inv.getItem(29).getType() != Material.AIR))) {
			event.setCancelled(true);
			return;
		}
		if(slot == 22 && !event.getView().getTopInventory().getItem(13).equals( DrillMechanicInv.DenieAnvil()) && ((inv.getItem(33) != null && inv.getItem(33).getType() != Material.AIR) && (inv.getItem(29) != null && inv.getItem(29).getType() != Material.AIR))) {
			
			ArrayList<String> lore = new ArrayList<>();
			ItemMeta meta;
			ItemStack finalItem = finalItem(Items.SkyblockItems.get(inv.getItem(29).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)), Items.SkyblockItems.get(inv.getItem(33).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)), inv.getItem(29), inv.getItem(33),(Player) event.getWhoClicked());
			lore = (ArrayList<String>) finalItem.getItemMeta().getLore();
			lore.add("§8§m-----------------");
			lore.add("§aThis is the item you will get.");
			lore.add("§aClick the §cANVIL BELOW §ato");
			lore.add("§acombine.");
			meta = finalItem.getItemMeta();
			meta.setLore(lore);
			finalItem.setItemMeta(meta);
			inv.setItem(13, finalItem);
			inv.setItem(29, new ItemStack(Material.AIR));
			inv.setItem(33, new ItemStack(Material.AIR));
			inv.setItem(24, DrillMechanicInv.Denieother());
			inv.setItem(14, DrillMechanicInv.Denieother());
			inv.setItem(15, DrillMechanicInv.Denieother());
			inv.setItem(20, DrillMechanicInv.DenieDrill());
			inv.setItem(11, DrillMechanicInv.DenieDrill());
			inv.setItem(12, DrillMechanicInv.DenieDrill());
			inv.setItem(22,DrillMechanicInv.DenieAnvil());
			ItemStack item = inv.getItem(45);
			item.setType(Material.RED_STAINED_GLASS_PANE);
			inv.setItem(45, item);
			inv.setItem(46, item);
			inv.setItem(47, item);
			inv.setItem(48, item);
			Player player = (Player) event.getWhoClicked();
			player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
			inv.setItem(50, item);
			inv.setItem(51, item);
			inv.setItem(52, item);
			inv.setItem(53, item);
			return;
			
		}
		if(slot == 13 && !event.getView().getTopInventory().getItem(13).equals(DrillMechanicInv.DenieAnvil()) && !((inv.getItem(33) != null && inv.getItem(33).getType() != Material.AIR) && (inv.getItem(29) != null && inv.getItem(29).getType() != Material.AIR)) ) {
			event.getView().getTopInventory().setItem(13, Main.item_updater(event.getView().getTopInventory().getItem(13), SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())));
			event.setCancelled(false);
			
			inv.setItem(29, new ItemStack(Material.AIR));
			inv.setItem(33, new ItemStack(Material.AIR));
			inv.setItem(24, DrillMechanicInv.Denieother());
			inv.setItem(14, DrillMechanicInv.Denieother());
			inv.setItem(15, DrillMechanicInv.Denieother());
			inv.setItem(20, DrillMechanicInv.DenieDrill());
			inv.setItem(11, DrillMechanicInv.DenieDrill());
			inv.setItem(12, DrillMechanicInv.DenieDrill());
			inv.setItem(22, DrillMechanicInv.DrillCombine());
			ItemStack item = inv.getItem(45);
			item.setType(Material.RED_STAINED_GLASS_PANE);
			inv.setItem(45, item);
			inv.setItem(46, item);
			inv.setItem(47, item);
			inv.setItem(48, item);
			
			inv.setItem(50, item);
			inv.setItem(51, item);
			inv.setItem(52, item);
			inv.setItem(53, item);
			new BukkitRunnable() {
				
				@Override
				public void run() {
					inv.setItem(13, DrillMechanicInv.DenieAnvil());
					
				}
			}.runTaskLater(Main.getMain(), 1L);
			
			
			return;
		}
		if(slot == 49)
			event.getWhoClicked().closeInventory();
		new BukkitRunnable(){
			@Override
			public void run() {
				event.getView().getTopInventory().setContents(checkAnvil(event.getView().getTopInventory(), (Player) event.getWhoClicked(), slot).getContents());
		    }
		}.runTaskLater(Main.getMain(), 5L);
	}
	@EventHandler
	public void returnItems(InventoryCloseEvent event) {
		if(event.getView().getTitle() == null || !event.getView().getTitle().contains("Drill Anvil")) {
			return;
		}
		Inventory inv = event.getView().getTopInventory();
		if(inv.getItem(29) != null && inv.getItem(29).getType() != Material.AIR)
			event.getPlayer().getInventory().addItem(inv.getItem(29));
		if(inv.getItem(33) != null && inv.getItem(33).getType() != Material.AIR)
			event.getPlayer().getInventory().addItem(inv.getItem(33));
		if(inv.getItem(13) != null && !inv.getItem(13).equals(DrillMechanicInv.DenieAnvil()) && (inv.getItem(33) == null || inv.getItem(33).getType() == Material.AIR) && (inv.getItem(29) == null || inv.getItem(29).getType() == Material.AIR)) {
			event.getPlayer().getInventory().addItem(Main.item_updater(inv.getItem(13), SkyblockPlayer.getSkyblockPlayer((Player) event.getPlayer())));
		}
		
	}







	public static Inventory checkAnvil(Inventory inv, Player player, int slot) {
		boolean itemInSlot1 = false;
		boolean itemInSlot2 = false;
		
	if(inv.getItem(29) != null && inv.getItem(29).getType() != Material.AIR) {
			
			ItemStack i = inv.getItem(20);
			i.setType(Material.LIME_STAINED_GLASS_PANE);
			i = inv.getItem(11);
			i.setType(Material.LIME_STAINED_GLASS_PANE);
			i = inv.getItem(12);
			i.setType(Material.LIME_STAINED_GLASS_PANE);
			inv.setItem(20, i);
			itemInSlot1 = true;
			 i = inv.getItem(29);
			if(Items.SkyblockItems.get(i.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)).type == ItemType.Drill) {
				PersistentDataContainer data = i.getItemMeta().getPersistentDataContainer();
				if(data.get(new NamespacedKey(Main.getMain(), "fueltank"), PersistentDataType.STRING) != null) {
					inv.setItem(9,Main.item_updater(DrillPart.parts.get(data.get(new NamespacedKey(Main.getMain(), "fueltank"), PersistentDataType.STRING)).getRawItemStack(),SkyblockPlayer.getSkyblockPlayer(player)));
				}else {
					inv.setItem(9, DrillMechanicInv.NoDrillFuelTank());
				}
				
				if(data.get(new NamespacedKey(Main.getMain(), "drillengine"), PersistentDataType.STRING) != null) {
					inv.setItem(18,Main.item_updater(DrillPart.parts.get(data.get(new NamespacedKey(Main.getMain(), "drillengine"), PersistentDataType.STRING)).getRawItemStack(), SkyblockPlayer.getSkyblockPlayer(player)));
				}else {
					inv.setItem(18, DrillMechanicInv.NoDrillDrillEngin());
				}
			}
			
		}else {
			inv.setItem(20, DrillMechanicInv.DenieDrill());
			inv.setItem(11, DrillMechanicInv.DenieDrill());
			inv.setItem(12, DrillMechanicInv.DenieDrill());
			
			inv.setItem(9, DrillMechanicInv.NoDrillFuelTank());
			inv.setItem(18, DrillMechanicInv.NoDrillDrillEngin());
			inv.setItem(27, DrillMechanicInv.NoDrillUpgradeModule());
			
		}
	if(inv.getItem(33) != null && inv.getItem(33).getType() != Material.AIR) {
		
		ItemStack i = inv.getItem(24);
		i.setType(Material.LIME_STAINED_GLASS_PANE);
		i = inv.getItem(14);
		i.setType(Material.LIME_STAINED_GLASS_PANE);
		i = inv.getItem(15);
		i.setType(Material.LIME_STAINED_GLASS_PANE);
		inv.setItem(24, i);
		itemInSlot2 = true;
	}else {
		inv.setItem(24, DrillMechanicInv.Denieother());
		inv.setItem(14, DrillMechanicInv.Denieother());
		inv.setItem(15, DrillMechanicInv.Denieother());
		
	}
	if(itemInSlot1 && itemInSlot2) {
		
		if(Items.SkyblockItems.containsKey(inv.getItem(29).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)) 
				&& Items.SkyblockItems.containsKey(inv.getItem(33).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING))) {
			
			if(checkCombine(Items.SkyblockItems.get(inv.getItem(29).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)), Items.SkyblockItems.get(inv.getItem(33).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)), inv.getItem(29), inv.getItem(33))){
			
			
			
			ItemStack item = inv.getItem(22);
			ItemMeta meta = item.getItemMeta();
			meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
			
			ArrayList<String> lore = (ArrayList<String>) DrillMechanicInv.DrillCombine().getItemMeta().getLore();
			lore.add("");
			lore.add("§7Click to combine");
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.removeItem(inv.getItem(22));
			inv.setItem(22, item);
			
			item = inv.getItem(45);
			item.setType(Material.LIME_STAINED_GLASS_PANE);
			inv.setItem(45, item);
			inv.setItem(46, item);
			inv.setItem(47, item);
			inv.setItem(48, item);
			
			inv.setItem(50, item);
			inv.setItem(51, item);
			inv.setItem(52, item);
			inv.setItem(53, item);
			ItemStack finalItem = finalItem(Items.SkyblockItems.get(inv.getItem(29).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)), Items.SkyblockItems.get(inv.getItem(33).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)),new ItemStack( inv.getItem(29)), new ItemStack(inv.getItem(33)),player);
			lore = (ArrayList<String>) finalItem.getItemMeta().getLore();
			lore.add("§8§m-----------------");
			lore.add("§aThis is the item you will get.");
			lore.add("§aClick the §cANVIL BELOW §ato");
			lore.add("§acombine.");
			meta = finalItem.getItemMeta();
			meta.setLore(lore);
			finalItem.setItemMeta(meta);
			inv.setItem(13, finalItem);
			}else {
				inv.setItem(24, DrillMechanicInv.Denieother());
				inv.setItem(14, DrillMechanicInv.Denieother());
				inv.setItem(15, DrillMechanicInv.Denieother());
				inv.setItem(20, DrillMechanicInv.DenieDrill());
				inv.setItem(11, DrillMechanicInv.DenieDrill());
				inv.setItem(12, DrillMechanicInv.DenieDrill());
				inv.setItem(22, DrillMechanicInv.DrillCombine());
				ItemStack item = inv.getItem(45);
				item.setType(Material.RED_STAINED_GLASS_PANE);
				inv.setItem(45, item);
				inv.setItem(46, item);
				inv.setItem(47, item);
				inv.setItem(48, item);
				
				inv.setItem(50, item);
				inv.setItem(51, item);
				inv.setItem(52, item);
				inv.setItem(53, item);
				inv.setItem(13, DrillMechanicInv.DenieAnvil());
			}
		}else {
			inv.setItem(24, DrillMechanicInv.Denieother());
			inv.setItem(14, DrillMechanicInv.Denieother());
			inv.setItem(15, DrillMechanicInv.Denieother());
			inv.setItem(20, DrillMechanicInv.DenieDrill());
			inv.setItem(11, DrillMechanicInv.DenieDrill());
			inv.setItem(12, DrillMechanicInv.DenieDrill());
			ItemStack item = inv.getItem(45);
			inv.setItem(22, DrillMechanicInv.DrillCombine());
			item.setType(Material.RED_STAINED_GLASS_PANE);
			inv.setItem(45, item);
			inv.setItem(46, item);
			inv.setItem(47, item);
			inv.setItem(48, item);
			
			inv.setItem(50, item);
			inv.setItem(51, item);
			inv.setItem(52, item);
			inv.setItem(53, item);
			inv.setItem(13, DrillMechanicInv.DenieAnvil());
		}
	}else {
		
		ItemStack item = inv.getItem(45);
		inv.setItem(22, DrillMechanicInv.DrillCombine());
		item.setType(Material.RED_STAINED_GLASS_PANE);
		inv.setItem(45, item);
		inv.setItem(46, item);
		inv.setItem(47, item);
		inv.setItem(48, item);
		
		inv.setItem(50, item);
		inv.setItem(51, item);
		inv.setItem(52, item);
		inv.setItem(53, item);
		inv.setItem(13, DrillMechanicInv.DenieAnvil());
	}
		return inv;
	}



	public static boolean checkCombine(ItemManager manager1, ItemManager manager2, ItemStack item1, ItemStack item2) {
		PersistentDataContainer data = item1.getItemMeta().getPersistentDataContainer();
		if( manager1.type == ItemType.Drill && ((manager2.type == ItemType.FuelTank && data.get(new NamespacedKey(Main.getMain(), "fueltank"), PersistentDataType.STRING) == null ) || (manager2.type == ItemType.DrillEngine && data.get(new NamespacedKey(Main.getMain(), "drillengine"), PersistentDataType.STRING) == null ) || (manager2.type == ItemType.UpgradeModule && data.get(new NamespacedKey(Main.getMain(), "upgrademodule"), PersistentDataType.STRING) == null )|| manager2.type == ItemType.DrillFuel)){
			
			
			return true;
		}
		return false;
	}
	public final static ItemStack finalItem(ItemManager manager1, ItemManager manager2, final ItemStack item1, final ItemStack item2, Player player) {
		
		
		if(manager2.type == ItemType.DrillFuel) {
			ItemStack item = item1.clone();
			
			ItemMeta meta = item.getItemMeta();
			PersistentDataContainer data = meta.getPersistentDataContainer();
			
			int currfuel = data.get(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER);
			int maxfuel = data.get(new NamespacedKey(Main.getMain(), "maxfuel"), PersistentDataType.INTEGER);
			int addedfuel = Integer.parseInt(manager2.customDataContainer.get("refuel"));
			
			if(currfuel + addedfuel> maxfuel) {
				currfuel = maxfuel;
			}else
				currfuel = currfuel + addedfuel;
				
			data.set(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER, currfuel);
			item.setItemMeta(meta);
			return Main.item_updater(item,SkyblockPlayer.getSkyblockPlayer(player));
			
			
			
			
			
		}
		if(manager2.type == ItemType.FuelTank) {
			ItemStack item = item1.clone();
						
						ItemMeta meta = item.getItemMeta();
						PersistentDataContainer data = meta.getPersistentDataContainer();
						int newFuel = DrillPart.parts.get(manager2.itemID).applyfuelcap;
						data.set(new NamespacedKey(Main.getMain(), "fuel"), PersistentDataType.INTEGER, newFuel);
						data.set(new NamespacedKey(Main.getMain(), "maxfuel"), PersistentDataType.INTEGER, newFuel);
						data.set(new NamespacedKey(Main.getMain(), "fueltank"), PersistentDataType.STRING, manager2.itemID);
						item.setItemMeta(meta);
						return Main.item_updater(item,SkyblockPlayer.getSkyblockPlayer(player));
					}
		if(manager2.type == ItemType.DrillEngine) {
			ItemStack item = item1.clone();
						
						ItemMeta meta = item.getItemMeta();
						PersistentDataContainer data = meta.getPersistentDataContainer();
						int newFuel = DrillPart.parts.get(manager2.itemID).applyminingspeed;
						double currMiningspeed = data.get(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE);
						data.set(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE, currMiningspeed +  newFuel);
						data.set(new NamespacedKey(Main.getMain(), "drillengine"), PersistentDataType.STRING, manager2.itemID);
						item.setItemMeta(meta);
						return Main.item_updater(item,SkyblockPlayer.getSkyblockPlayer(player));
					}
		
		return item1;
		
	}


	}
