package me.CarsCupcake.SkyblockRemake.Pets;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Configs.PetMenus;

public class PetMenuListender implements Listener {
	@EventHandler
		public void addToPetMenu(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getItem() != null && event.getItem().getItemMeta() != null && Pet.pets.containsKey(event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING))) {
				Pet pet = Pet.pets.get(event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING));
				Player player = event.getPlayer();
				PetMenus.reload();
				int var = 1;
				if (PetMenus.get().contains(player.getUniqueId().toString()))
					var = PetMenus.get().getConfigurationSection(player.getUniqueId().toString()).getKeys(false).size() + 1;
				PetMenus.get().set(player.getUniqueId().toString() + "." + var + ".id", pet.itemID);
				PetMenus.get().set(player.getUniqueId().toString() + "." + var + ".level", event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER));
				PetMenus.get().set(player.getUniqueId().toString() + "." + var + ".currxp", event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "currxp"), PersistentDataType.DOUBLE));
				PetMenus.get().set(player.getUniqueId().toString() + "." + var + ".equiped", false);
				PetMenus.save();
				PetMenus.reload();
				event.getItem().setAmount(0);
				player.updateInventory();
			}
		}
	}
	@EventHandler
	public void inventoryClickEvent(InventoryClickEvent event) {
		if(event.getView().getTitle() == null || !event.getView().getTitle().contains("Pets")) {
			return;
		}
		event.setCancelled(true);
		if(event.getClickedInventory() != null &&event.getClickedInventory().getType() == InventoryType.PLAYER) {
			return;
		}
		Inventory inv = event.getView().getTopInventory();
		ItemStack item = inv.getItem(event.getSlot());
		if(item != null && item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer() != null) {
			PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
			if(data.get(new NamespacedKey(Main.getMain(), "pet"), PersistentDataType.STRING) != null ) {
				int petfile = data.get(new NamespacedKey(Main.getMain(), "fileid"), PersistentDataType.INTEGER);
				PetMenus.reload();
				if(petfile == PetMenus.get().getInt(event.getWhoClicked().getUniqueId().toString() + ".equiped")) {
					//despawn pet
					event.getWhoClicked().sendMessage("§cPet got despawned");
					event.getWhoClicked().closeInventory();
					PetMenus.get().set(event.getWhoClicked().getUniqueId().toString() + ".equiped", 0);
					PetMenus.get().set(event.getWhoClicked().getUniqueId() + "." + petfile + ".equiped", false);
					PetMenus.save();
					Main.petstand.get((Player) event.getWhoClicked()).remove();
					Main.petstand.remove((Player) event.getWhoClicked());
					
				}else {
					//pet not the spawned pet/no pet equiped
					
					//check if the player has a pet equiped
					if(0 != PetMenus.get().getInt(event.getWhoClicked().getUniqueId().toString() + ".equiped")) {
						//despawn old pet spawn new pet
						event.getWhoClicked().closeInventory();
						
						PetMenus.get().set(event.getWhoClicked().getUniqueId() + "." + PetMenus.get().getInt(event.getWhoClicked().getUniqueId().toString() + ".equiped") + ".equiped", false);PetMenus.get().set(event.getWhoClicked().getUniqueId().toString() + ".equiped", petfile);
						PetMenus.get().set(event.getWhoClicked().getUniqueId() + "." + petfile + ".equiped", true);
						event.getWhoClicked().sendMessage("§aSpawned new Pet");PetMenus.save();
						Main.petstand.get((Player) event.getWhoClicked()).remove();
						new PetFollowRunner((Player)event.getWhoClicked(), Pet.pets.get(PetMenus.get().getString(event.getWhoClicked().getUniqueId() + "." + petfile + ".id")), petfile);
						
					}else {
						//spawn new pet
						event.getWhoClicked().closeInventory();
						PetMenus.get().set(event.getWhoClicked().getUniqueId().toString() + ".equiped", petfile);
						PetMenus.get().set(event.getWhoClicked().getUniqueId() + "." + petfile + ".equiped", true);
						event.getWhoClicked().sendMessage("§aSpawned new Pet");PetMenus.save();
						
						new PetFollowRunner((Player)event.getWhoClicked(), Pet.pets.get(PetMenus.get().getString(event.getWhoClicked().getUniqueId() + "." + petfile + ".id")), petfile);
						
					}
					
				}
				
				PetMenus.reload();
			}
		}
	
		
	}
}
