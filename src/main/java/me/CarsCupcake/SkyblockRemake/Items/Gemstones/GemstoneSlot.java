package me.CarsCupcake.SkyblockRemake.Items.Gemstones;

import java.util.ArrayList;

import org.bukkit.NamespacedKey;
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
		item = Main.itemUpdater(item, null);
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
	
}
