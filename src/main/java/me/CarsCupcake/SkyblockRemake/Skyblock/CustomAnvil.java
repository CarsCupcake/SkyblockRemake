package me.CarsCupcake.SkyblockRemake.Skyblock;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Gemstones.GemstoneSlot;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.reforges.AddReforges;
import me.CarsCupcake.SkyblockRemake.reforges.registerReforge;




public class CustomAnvil implements Listener{
@EventHandler
public void SpawnEggUse(PlayerInteractEvent event) {
	if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		if(event.getClickedBlock().getType() == Material.ANVIL) {
			event.setCancelled(true);
			event.getPlayer().openInventory(AnvilInventory.CustomAnvilInventory());
		
		}
	}}
@EventHandler
public void AnvilOnClick(InventoryClickEvent event) {
	
	if(event.getView().getTitle() == null || (!event.getView().getTitle().contains("Anvil") || event.getView().getTitle().contains("Drill"))) {
		
		return;
	}
	event.setCancelled(true);
	final int slot;
	if(event.getClickedInventory().getType() != InventoryType.PLAYER) {
		slot = event.getSlot();
	}else {
		slot = -1;
	}
	if(slot != 29 && slot != 33 && slot != -1)
	event.setCancelled(true);
	else
		event.setCancelled(false);
	Inventory inv = event.getView().getTopInventory();
	if(slot != 13 && !event.getView().getTopInventory().getItem(13).equals( AnvilInventory.DenieButton()) && !((inv.getItem(33) != null && inv.getItem(33).getType() != Material.AIR) && (inv.getItem(29) != null && inv.getItem(29).getType() != Material.AIR))) {
		event.setCancelled(true);
		return;
	}
	if(slot == 22 && !event.getView().getTopInventory().getItem(13).equals( AnvilInventory.DenieButton()) && ((inv.getItem(33) != null && inv.getItem(33).getType() != Material.AIR) && (inv.getItem(29) != null && inv.getItem(29).getType() != Material.AIR))) {
		
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
		inv.setItem(24, AnvilInventory.DenieSacrifice());
		inv.setItem(14, AnvilInventory.DenieSacrifice());
		inv.setItem(15, AnvilInventory.DenieSacrifice());
		inv.setItem(20, AnvilInventory.DenieUpgrade());
		inv.setItem(11, AnvilInventory.DenieUpgrade());
		inv.setItem(12, AnvilInventory.DenieUpgrade());
		inv.setItem(22, AnvilInventory.CombineButton());
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
	if(slot == 13 && !event.getView().getTopInventory().getItem(13).equals(AnvilInventory.DenieButton()) && !((inv.getItem(33) != null && inv.getItem(33).getType() != Material.AIR) && (inv.getItem(29) != null && inv.getItem(29).getType() != Material.AIR)) ) {
		event.getView().getTopInventory().setItem(13, Main.item_updater(event.getView().getTopInventory().getItem(13), SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())));
		event.setCancelled(false);
		
		inv.setItem(29, new ItemStack(Material.AIR));
		inv.setItem(33, new ItemStack(Material.AIR));
		inv.setItem(24, AnvilInventory.DenieSacrifice());
		inv.setItem(14, AnvilInventory.DenieSacrifice());
		inv.setItem(15, AnvilInventory.DenieSacrifice());
		inv.setItem(20, AnvilInventory.DenieUpgrade());
		inv.setItem(11, AnvilInventory.DenieUpgrade());
		inv.setItem(12, AnvilInventory.DenieUpgrade());
		inv.setItem(22, AnvilInventory.CombineButton());
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
				inv.setItem(13, AnvilInventory.DenieButton());
				
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
	if(!event.getView().getTitle().contains("Anvil")  || event.getView().getTitle().contains("Drill")) {
		return;
	}
	Inventory inv = event.getView().getTopInventory();
	if(inv.getItem(29) != null && inv.getItem(29).getType() != Material.AIR)
		event.getPlayer().getInventory().addItem(inv.getItem(29));
	if(inv.getItem(33) != null && inv.getItem(33).getType() != Material.AIR)
		event.getPlayer().getInventory().addItem(inv.getItem(33));
	if(inv.getItem(13) != null && !inv.getItem(13).equals(AnvilInventory.DenieButton()) && (inv.getItem(33) == null || inv.getItem(33).getType() == Material.AIR) && (inv.getItem(29) == null || inv.getItem(29).getType() == Material.AIR)) {
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
	}else {
		inv.setItem(20, AnvilInventory.DenieUpgrade());
		inv.setItem(11, AnvilInventory.DenieUpgrade());
		inv.setItem(12, AnvilInventory.DenieUpgrade());
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
	inv.setItem(24, AnvilInventory.DenieSacrifice());
	inv.setItem(14, AnvilInventory.DenieSacrifice());
	inv.setItem(15, AnvilInventory.DenieSacrifice());
	
}
if(itemInSlot1 && itemInSlot2) {
	
	if(Items.SkyblockItems.containsKey(inv.getItem(29).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)) 
			&& Items.SkyblockItems.containsKey(inv.getItem(33).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING))) {
		
		if(checkCombine(Items.SkyblockItems.get(inv.getItem(29).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)), Items.SkyblockItems.get(inv.getItem(33).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)), inv.getItem(29), inv.getItem(33))){
		
		
		
		ItemStack item = inv.getItem(22);
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		
		ArrayList<String> lore = (ArrayList<String>) AnvilInventory.CombineButton().getItemMeta().getLore();
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
			inv.setItem(24, AnvilInventory.DenieSacrifice());
			inv.setItem(14, AnvilInventory.DenieSacrifice());
			inv.setItem(15, AnvilInventory.DenieSacrifice());
			inv.setItem(20, AnvilInventory.DenieUpgrade());
			inv.setItem(11, AnvilInventory.DenieUpgrade());
			inv.setItem(12, AnvilInventory.DenieUpgrade());
			inv.setItem(22, AnvilInventory.CombineButton());
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
			inv.setItem(13, AnvilInventory.DenieButton());
		}
	}else {
		inv.setItem(24, AnvilInventory.DenieSacrifice());
		inv.setItem(14, AnvilInventory.DenieSacrifice());
		inv.setItem(15, AnvilInventory.DenieSacrifice());
		inv.setItem(20, AnvilInventory.DenieUpgrade());
		inv.setItem(11, AnvilInventory.DenieUpgrade());
		inv.setItem(12, AnvilInventory.DenieUpgrade());
		ItemStack item = inv.getItem(45);
		inv.setItem(22, AnvilInventory.CombineButton());
		item.setType(Material.RED_STAINED_GLASS_PANE);
		inv.setItem(45, item);
		inv.setItem(46, item);
		inv.setItem(47, item);
		inv.setItem(48, item);
		
		inv.setItem(50, item);
		inv.setItem(51, item);
		inv.setItem(52, item);
		inv.setItem(53, item);
		inv.setItem(13, AnvilInventory.DenieButton());
	}
}else {
	
	ItemStack item = inv.getItem(45);
	inv.setItem(22, AnvilInventory.CombineButton());
	item.setType(Material.RED_STAINED_GLASS_PANE);
	inv.setItem(45, item);
	inv.setItem(46, item);
	inv.setItem(47, item);
	inv.setItem(48, item);
	
	inv.setItem(50, item);
	inv.setItem(51, item);
	inv.setItem(52, item);
	inv.setItem(53, item);
	inv.setItem(13, AnvilInventory.DenieButton());
}
	return inv;
}



public static boolean checkCombine(ItemManager manager1, ItemManager manager2, ItemStack item1, ItemStack item2) {
	
	if( manager2.type == ItemType.EnchantBook) {
		final Material old_material = item1.getType();
		if(manager1.type == ItemType.Sword)
			item1.setType(Material.WOODEN_SWORD);
		if(manager1.type == ItemType.Pickaxe || manager1.type == ItemType.Drill)
			item1.setType(Material.WOODEN_PICKAXE);
		for(Enchantment enchant : item2.getEnchantments().keySet()){
			
			if(!enchant.canEnchantItem(item1)) {
				item1.setType(old_material);
				return false;
			}
		}
		item1.setType(old_material);
		return true;
	}
	if((manager1.type == ItemType.Helmet || manager1.type == ItemType.Chestplate || manager1.type == ItemType.Leggings || manager1.type == ItemType.Boots || manager1.type == ItemType.Sword || manager1.type == ItemType.Bow) && manager2.type == ItemType.PotatoBook) {
		return true;
	}
	if(manager2.type == ItemType.Recom) {
		if(item1.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "recomed"), PersistentDataType.INTEGER) == 0) {
			return true;
		}else {
			return false;
		}
	}
	return false;
}
public final static ItemStack finalItem(ItemManager manager1, ItemManager manager2, final ItemStack item1, final ItemStack item2, Player player) {
	
	if( manager2.type == ItemType.EnchantBook) {
		ItemStack item = new ItemStack(item1);
		final Material old_material = item1.getType();
System.out.println(old_material.toString());
if(manager1.type == ItemType.Sword)
		item.setType(Material.WOODEN_SWORD);
if(manager1.type == ItemType.Pickaxe || manager1.type == ItemType.Drill)
	item.setType(Material.WOODEN_PICKAXE);
		item2.getEnchantments().forEach((enchant, level)->{
			ItemMeta meta = item.getItemMeta();
			if(enchant.canEnchantItem(item)) {
				if(item1.getEnchantments() != null) {
				if(item1.getEnchantments().containsKey(enchant) && item1.getEnchantments().get(enchant) == level && item1.getEnchantments().get(enchant) != enchant.getMaxLevel()) {
					meta.addEnchant(enchant, level+1, false);
				}else {
					
					if(item1.getEnchantments().containsKey(enchant) &&item1.getEnchantments().get(enchant) > level) {
						meta.addEnchant(enchant, item1.getEnchantments().get(enchant), true);
					}else
						meta.addEnchant(enchant, level, true);
				}
				}else {
					meta.addEnchant(enchant, level, true);
				}
			}
			item.setItemMeta(meta);
		});
		
		item1.setType(Material.STICK);
		item.setType(Material.STICK); 
		final ItemStack  FINAL_ITEM = new ItemStack(Main.item_updater(item, SkyblockPlayer.getSkyblockPlayer((Player) SkyblockPlayer.getSkyblockPlayer(player))));
	
	return FINAL_ITEM;
	}
	if((manager1.type == ItemType.Helmet || manager1.type == ItemType.Chestplate || manager1.type == ItemType.Leggings || manager1.type == ItemType.Boots || manager1.type == ItemType.Sword || manager1.type == ItemType.Bow) && manager2.type == ItemType.PotatoBook) {
		ItemStack item = new ItemStack(item1);
		int PotatoBookAmount = 0;
		try {
			PotatoBookAmount =	item1.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "potatobooks"), PersistentDataType.INTEGER);
		}catch (Exception e) {
			
		}
		ItemMeta meta = item.getItemMeta();
			PersistentDataContainer data = meta.getPersistentDataContainer();
		if(manager1.type == ItemType.Helmet || manager1.type == ItemType.Chestplate || manager1.type == ItemType.Leggings || manager1.type == ItemType.Boots) {
			
			
			
			data.set(new NamespacedKey(Main.getMain(), "potatobooks"),  PersistentDataType.INTEGER, PotatoBookAmount +1);
			try {
				data.set(new NamespacedKey(Main.getMain(), "health"),  PersistentDataType.INTEGER, data.get(new NamespacedKey(Main.getMain(), "health"),  PersistentDataType.INTEGER) +4);
				}catch (Exception e) {
					data.set(new NamespacedKey(Main.getMain(), "health"),  PersistentDataType.INTEGER, 4);
				}
				try {
					data.set(new NamespacedKey(Main.getMain(), "def"),  PersistentDataType.INTEGER, data.get(new NamespacedKey(Main.getMain(), "def"),  PersistentDataType.INTEGER) +2);
				}catch (Exception e) {
					data.set(new NamespacedKey(Main.getMain(), "def"),  PersistentDataType.INTEGER, 2);
				}
		}else {
			data.set(new NamespacedKey(Main.getMain(), "potatobooks"),  PersistentDataType.INTEGER, PotatoBookAmount +1);
			try {
				data.set(new NamespacedKey(Main.getMain(), "dmg"),  PersistentDataType.STRING,(Integer.parseInt( data.get(new NamespacedKey(Main.getMain(), "dmg"),  PersistentDataType.STRING)) +2) + "");
			}catch (Exception e) {
				data.set(new NamespacedKey(Main.getMain(), "dmg"),  PersistentDataType.STRING, "2");
			}
			try {
				data.set(new NamespacedKey(Main.getMain(), "strength"),  PersistentDataType.INTEGER, data.get(new NamespacedKey(Main.getMain(), "strength"),  PersistentDataType.INTEGER) +2);
			}catch (Exception e) {
				data.set(new NamespacedKey(Main.getMain(), "strength"),  PersistentDataType.INTEGER, 2);
			}
		}
		
		item.setItemMeta(meta);
final ItemStack  FINAL_ITEM = new ItemStack(Main.item_updater(item, null));
	
	return FINAL_ITEM;
	}
	if(manager2.type == ItemType.Recom) {
		ItemStack item = new ItemStack(item1);
		ItemMeta meta = item.getItemMeta();
		PersistentDataContainer data = meta.getPersistentDataContainer();
		data.set(new NamespacedKey(Main.getMain(), "recomed"), PersistentDataType.INTEGER, 1);
		data.set(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING, manager1.rarity.getNext().toString());
		item.setItemMeta(meta);
		if(data.get(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING) != null && registerReforge.reforges.containsKey(data.get(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING)))
		 item = AddReforges.toItemStack(item, manager1.rarity.getNext(), registerReforge.reforges.get(data.get(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING)), manager1.rarity);
	
		item = GemstoneSlot.recomGemstone(manager1, item);
		final ItemStack  FINAL_ITEM = new ItemStack(Main.item_updater(item,null));
		
		return FINAL_ITEM;
		
		
	}
	return item1;
	
}


}