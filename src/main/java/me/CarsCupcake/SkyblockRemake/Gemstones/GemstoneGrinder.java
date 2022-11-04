package me.CarsCupcake.SkyblockRemake.Gemstones;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockServer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;



public class GemstoneGrinder implements Listener {

	@EventHandler
	public void SpawnEggUse(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock().getType() == Material.END_PORTAL_FRAME) {
				event.setCancelled(true);
				if(SkyblockServer.getServer().getType() == ServerType.End)
					return;
				event.getPlayer().openInventory(GemstoneGrinderInv.getInv());
			
			}
		}}
	
	
	@EventHandler
	public void GemstoneGrinderOnClick(InventoryClickEvent event) {
if(event.getView().getTitle() == null || !event.getView().getTitle().contains("Gemstone Grinder")) {
		
		return;
	}
	
	if(event.getClickedInventory().getType() != InventoryType.PLAYER) {
		if(event.getSlot() != 13)
		event.setCancelled(true);
	}else {
		if(event.getWhoClicked().getInventory().getItem(event.getSlot()) != null && event.getWhoClicked().getInventory().getItem(event.getSlot()).getItemMeta() != null){
		if((Items.SkyblockItems.get(event.getWhoClicked().getInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)).gemstoneSlots ==null || Items.SkyblockItems.get(event.getWhoClicked().getInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)).gemstoneSlots.isEmpty())) {
			event.setCancelled(true);
			if(event.getView().getTopInventory().getItem(13) == null)
			event.getWhoClicked().sendMessage("§c§lARE YOU DUMB?!\n§r§cYou can only put items with gemstone slots in ... idiot");
			else {
				if(Items.SkyblockItems.get(event.getWhoClicked().getInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)).type == ItemType.Gemstone){
					ItemManager manager = Items.SkyblockItems.get(event.getView().getTopInventory().getItem(13).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));

					Gemstone gem = Gemstone.gemstones.get(event.getWhoClicked().getInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
					ArrayList<GemstoneSlot> slots = GemstoneSlot.getCurrGemstones(manager, event.getView().getTopInventory().getItem(13).getItemMeta().getPersistentDataContainer());
					boolean freeSlot = false;
					Gemstone addedgem = null; 
					ArrayList<GemstoneSlot> newSlots = new ArrayList<>();
					for(GemstoneSlot s : slots) {
						
						if(s.currGem == null && !freeSlot) {
							
							ArrayList<GemstoneType> types = new ArrayList<>();
							for(GemstoneType t : s.type.getValidTypes()) {
								types.add(t);
							}
							if(types.contains(gem.gemType)) {
								freeSlot = true;
								addedgem = gem;
								s.setGem(gem);
								newSlots.add(s);
								ItemStack it = event.getWhoClicked().getInventory().getItem(event.getSlot());
								it.setAmount(it.getAmount() -1);
								event.getView().getBottomInventory().setItem(event.getSlot(), it);
								
							}else {
								newSlots.add(s);	
							}
						}else {
							newSlots.add(s);
						}
					}
					
					if(freeSlot) {
						
						ItemStack i = event.getView().getTopInventory().getItem(13);

						
						
						
						i = GemstoneSlot.setSlotPersistentDataContainer(i, newSlots);
						ItemMeta meta = i.getItemMeta();
						PersistentDataContainer data = meta.getPersistentDataContainer();
						if(addedgem.gemType == GemstoneType.Ruby) {
							ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
							if(data.get(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.DOUBLE) != null) {
								data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.DOUBLE) + addedgem.getStatBoost(rarity));
								
							}else {
								data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.INTEGER, addedgem.getStatBoost(rarity));
								
							}
						}
						if(addedgem.gemType == GemstoneType.Amber) {
							ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
							if(data.get(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE) != null) {
								data.set(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE) + addedgem.getStatBoost(rarity));
								
							}else {
								data.set(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE,(double) addedgem.getStatBoost(rarity));
								
							}
						}
						if(addedgem.gemType == GemstoneType.Jade) {
							ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
							if(data.get(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.DOUBLE) != null) {
								data.set(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.DOUBLE) + addedgem.getStatBoost(rarity));
								
							}else {
								data.set(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.DOUBLE, (double)addedgem.getStatBoost(rarity));
								
							}
						}
						if(addedgem.gemType == GemstoneType.Sapphire) {
							ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
							if(data.get(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.DOUBLE) != null) {
								data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.DOUBLE) + addedgem.getStatBoost(rarity));
								
							}else {
								data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.DOUBLE, (double)addedgem.getStatBoost(rarity));
								
							}
						}
						if(addedgem.gemType == GemstoneType.Amethyst) {
							ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
							if(data.get(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.DOUBLE) != null) {
								data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.DOUBLE) + addedgem.getStatBoost(rarity));
								
							}else {
								data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.DOUBLE,(double) addedgem.getStatBoost(rarity));
								
							}
						}
						
						if(addedgem.gemType == GemstoneType.Jasper) {
							ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
							if(data.get(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.DOUBLE) != null) {
								data.set(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.DOUBLE) + addedgem.getStatBoost(rarity));
								
							}else {
								data.set(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.DOUBLE,(double) addedgem.getStatBoost(rarity));
								
							}
						}
						if(addedgem.gemType == GemstoneType.Topaz) {
							ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
							if(data.get(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE) != null) {
								data.set(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE) + addedgem.getDoubleStatBoost(rarity));
								
							}else {
								data.set(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE,(double) addedgem.getDoubleStatBoost(rarity));
								
							}
						}
						i.setItemMeta(meta);
						i = Main.item_updater(i, SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked()));
						event.getView().getTopInventory().setItem(13, i);
						
					}
					
					
				}else {
					event.getWhoClicked().sendMessage("§c§lARE YOU DUMB?!\n§r§cYou can only put items with gemstone slots in ... idiot");
				}
			}
			
		}
		}
	}
	
	if(event.getSlot() > 27 && event.getSlot() < 35 && event.getClickedInventory().getType() != InventoryType.PLAYER) {
		if(event.getView().getTopInventory().getItem(13) != null) {
			int gemslot =  event.getSlot() - 28;
			ItemManager manager = Items.SkyblockItems.get(event.getView().getTopInventory().getItem(13).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
			if(manager.gemstoneSlots.size() > gemslot) {
				ArrayList<GemstoneSlot> slots = GemstoneSlot.getCurrGemstones(manager, (event.getView().getTopInventory().getItem(13).getItemMeta().getPersistentDataContainer()));
				if(slots.get(gemslot).currGem != null) {
					event.getWhoClicked().getInventory().addItem(event.getInventory().getItem(event.getSlot()));
					slots.set(gemslot, new GemstoneSlot(manager.gemstoneSlots.get(gemslot).type));
					ItemStack item = event.getView().getTopInventory().getItem(13);
					 item = GemstoneSlot.setSlotPersistentDataContainer(item, slots);
					
					
					Gemstone gem = Gemstone.gemstones.get(event.getInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
					ItemMeta meta = item.getItemMeta();
					PersistentDataContainer data = meta.getPersistentDataContainer();
					if(gem.gemType == GemstoneType.Ruby) {
						ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
						data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.INTEGER, data.get(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.INTEGER) - gem.getStatBoost(rarity));
						System.out.println(data.get(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.INTEGER) - gem.getStatBoost(rarity));
					}
					if(gem.gemType == GemstoneType.Amber) {
						ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
						data.set(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.INTEGER, data.get(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.INTEGER) - gem.getStatBoost(rarity));
						System.out.println(data.get(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.INTEGER) - gem.getStatBoost(rarity));
					}
					if(gem.gemType == GemstoneType.Jade) {
						ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
						data.set(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.INTEGER, data.get(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.INTEGER) - gem.getStatBoost(rarity));
						System.out.println(data.get(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.INTEGER) - gem.getStatBoost(rarity));
					}
					if(gem.gemType == GemstoneType.Sapphire) {
						ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
						data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.INTEGER, data.get(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.INTEGER) - gem.getStatBoost(rarity));
						System.out.println(data.get(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.INTEGER) - gem.getStatBoost(rarity));
					}
					if(gem.gemType == GemstoneType.Amethyst) {
						ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
						data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.INTEGER, data.get(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.INTEGER) - gem.getStatBoost(rarity));
						System.out.println(data.get(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.INTEGER) - gem.getStatBoost(rarity));
					}
					if(gem.gemType == GemstoneType.Jasper) {
						ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
						data.set(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.INTEGER, data.get(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.INTEGER) - gem.getStatBoost(rarity));
						System.out.println(data.get(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.INTEGER) - gem.getStatBoost(rarity));
					}
					if(gem.gemType == GemstoneType.Topaz) {
						ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
						data.set(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE) - gem.getDoubleStatBoost(rarity));
						System.out.println(data.get(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE) - gem.getDoubleStatBoost(rarity));
					}
					item.setItemMeta(meta);
					item = Main.item_updater(item, SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked()));
					event.getView().getTopInventory().setItem(13, item);
				}
			}
		}
	}
	
	if(event.getSlot() == 49)
		event.getWhoClicked().closeInventory();

	new BukkitRunnable(){
		@Override
		public void run() {
			Inventory inv = event.getView().getTopInventory();

			if(inv.getItem(13) != null) {
				ItemManager manager = Items.SkyblockItems.get(inv.getItem(13).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
				
				
				ArrayList<GemstoneSlot> slots = GemstoneSlot.getCurrGemstones(manager, inv.getItem(13).getItemMeta().getPersistentDataContainer());
				
				int r = 0;

			    for(int i = 28; i < 28 + 7; i++) {
			    	if(r < manager.gemstoneSlots.size()) {
			    		ItemStack item = slots.get(r).type.getGlassPane();
			    		
			    		if(slots.get(r).currGem != null) {
			    		item = slots.get(r).currGem.getRawItemStack();
			    		item = Main.item_updater(item, SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked()));
			    		}else {
			    			ItemMeta meta = item.getItemMeta();
			    			meta.setDisplayName(slots.get(r).type.getPrefix() + slots.get(r).type.getSymbol() + " " + slots.get(r).type.toString() + " Gemstone Slot");
			    			item.setItemMeta(meta);
			    		}
			    		inv.setItem(i, item);
			    	}
			    	r++;
			    }
			}else {
				for(int i = 28; i != 35; i++) {
					inv.setItem(i, GemstoneGrinderInv.NoItemSlot());
				}
			}
			
			event.getView().getTopInventory().setContents(inv.getContents());
			
	    }
	}.runTaskLater(Main.getMain(), 2L);
	
	}
	
	@EventHandler
	public void returnItems(InventoryCloseEvent event) {
		if(!event.getView().getTitle().contains("Gemstone Grinder")) {
			return;
		}
		if(event.getView().getTopInventory().getItem(13) != null) {
			event.getPlayer().getInventory().addItem(event.getView().getTopInventory().getItem(13));
		}
	}
	
}
