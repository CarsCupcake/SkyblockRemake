package me.CarsCupcake.SkyblockRemake.Gemstones;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;

public class GemstoneSlot {
	public SlotType type;
	public Gemstone currGem;
	public GemstoneSlot(SlotType type) {
		this.type = type;
	}
	public void setGem(Gemstone gem) {
		currGem = gem;
	}
	public static ItemStack setSlotPersistentDataContainer(ItemStack item, ArrayList<GemstoneSlot> slots) {
		ItemMeta meta = item.getItemMeta();
		PersistentDataContainer data = meta.getPersistentDataContainer();
		int i = 0;
		for(GemstoneSlot slot : slots) {
			if(slot.currGem != null) {
				data.set(new NamespacedKey(Main.getMain(), "SLOT_" + i), PersistentDataType.STRING, slot.currGem.itemID);
				
			}else {
				if(data.get(new NamespacedKey(Main.getMain(), "SLOT_" + i), PersistentDataType.STRING) != null){
					data.remove(new NamespacedKey(Main.getMain(), "SLOT_" + i));
				}
			}
			i++;
		}
		item.setItemMeta(meta);
		item = Main.item_updater(item, null);
		return item;
	}
	public static ArrayList<GemstoneSlot> getCurrGemstones(ItemManager manager, PersistentDataContainer data){
		ArrayList<GemstoneSlot> slots = new ArrayList<>();
		for(int i = 0; i < manager.gemstoneSlots.size(); i++) {
			if(data.get(new NamespacedKey(Main.getMain(),  "SLOT_" + i), PersistentDataType.STRING) != null) {
				slots.add(new GemstoneSlot(manager.gemstoneSlots.get(i).type));
				slots.get(i).setGem(Gemstone.gemstones.get(data.get(new NamespacedKey(Main.getMain(),  "SLOT_" + i), PersistentDataType.STRING)));
				
			}else {
				slots.add(new GemstoneSlot(manager.gemstoneSlots.get(i).type));
			}
		}
		
		return slots;
	}
	public static ItemStack recomGemstone(ItemManager manager, ItemStack item) {
		if(manager.gemstoneSlots == null && manager.gemstoneSlots.isEmpty())
			return item;
		ArrayList<GemstoneSlot> slots = getCurrGemstones(manager, item.getItemMeta().getPersistentDataContainer());
		ItemMeta meta = item.getItemMeta();
		PersistentDataContainer data= meta.getPersistentDataContainer();
		ItemRarity rarity = ItemRarity.valueOf(data.get(new NamespacedKey(Main.getMain(), "rarity"), PersistentDataType.STRING));
		for(GemstoneSlot slot : slots) {
			if(slot.currGem != null) {
				if(slot.currGem.gemType == GemstoneType.Ruby) {
					data.set(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "health"), PersistentDataType.DOUBLE) + slot.currGem.statBoostRecom(manager.rarity, rarity));
			}
				if(slot.currGem.gemType == GemstoneType.Amber) {
					data.set(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "miningspeed"), PersistentDataType.DOUBLE) + slot.currGem.statBoostRecom(manager.rarity, rarity));
					}
				if(slot.currGem.gemType == GemstoneType.Jade) {
					data.set(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "miningfortune"), PersistentDataType.DOUBLE) + slot.currGem.statBoostRecom(manager.rarity, rarity));
		}
				if(slot.currGem.gemType == GemstoneType.Sapphire) {
					data.set(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "mana"), PersistentDataType.DOUBLE) + slot.currGem.statBoostRecom(manager.rarity, rarity));
		}
				if(slot.currGem.gemType == GemstoneType.Amethyst) {
					data.set(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "def"), PersistentDataType.DOUBLE) + slot.currGem.statBoostRecom(manager.rarity, rarity));
		}
				if(slot.currGem.gemType == GemstoneType.Jasper) {
					data.set(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "strength"), PersistentDataType.DOUBLE) + slot.currGem.statBoostRecom(manager.rarity, rarity));
		}
				if(slot.currGem.gemType == GemstoneType.Topaz) {
					data.set(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE, data.get(new NamespacedKey(Main.getMain(), "pristine"), PersistentDataType.DOUBLE) + slot.currGem.doublestatBoostRecom(manager.rarity, rarity));
		}
			}
		}
		item.setItemMeta(meta);
		return item;
		
	}
	
}
