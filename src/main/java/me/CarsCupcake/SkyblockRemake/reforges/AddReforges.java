package me.CarsCupcake.SkyblockRemake.reforges;


import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class AddReforges {
	
	public static ItemStack toItemStack(ItemStack item,ItemRarity rarity, Reforge reforge) {
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "reforge"), PersistentDataType.STRING, reforge.getName());
		item.setItemMeta(meta);
		return getStatPackage(rarity, reforge).applyPackage(item);
	}
	public static ItemStack toItemStack(ItemStack item,ItemRarity rarity, Reforge reforge, ItemRarity oldRarity) {
		return toItemStack(removeOldReforge(item, oldRarity, reforge), rarity, reforge);
	}


public static ItemStack removeOldReforge(ItemStack item,ItemRarity rarity, Reforge reforge) {
	return getStatPackage(rarity, reforge).removePackage(item);
}

public static ReforgeStatPackage getStatPackage(ItemRarity rarity, Reforge reforge){
		switch(rarity){
			case LEGENDARY, SPECIAL -> {
				return reforge.LegendaryStats();
			}
			case RARE -> {
				return reforge.RareStats();
			}
			case COMMON, UNDEFINED -> {
				return reforge.CommonStats();
			}
			case EPIC -> {
				return reforge.EpicStats();
			}
			case UNCOMMON -> {
				return reforge.UncommonStats();
			}
			case DIVINE -> {
				return reforge.DivineStats();
			}
			case MYTHIC, SUPREME, VERY_SPECIAL -> {
				return reforge.MythicStats();
			}
			default -> throw new NullPointerException("Rarity is null");
		}

}

}
