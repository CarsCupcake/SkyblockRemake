package me.CarsCupcake.SkyblockRemake.Enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;

public class live_steal extends Enchantment{
	
	
	

	public live_steal(NamespacedKey key) {
		super(key);
	}

	@Override
	public boolean isTreasure() {
		return false;
	}
	
	@Override
	public boolean isCursed() {
		return false;
	}
	
	@Override
	public int getStartLevel() {
		return 1;
	}
	
	@Override
	public String getName() {
		return "Live Steal";
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}
	
	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}
	
	@Override
	public boolean conflictsWith(Enchantment arg0) {
		return false;
	}
	
	@Override
	public boolean canEnchantItem(ItemStack item) {
		if(item == null || item.getItemMeta() == null ||item.getItemMeta().getPersistentDataContainer() == null || item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING) == null)
		return false;
		
	if(Items.SkyblockItems.containsKey(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)) ) {
		if(Items.SkyblockItems.get(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "id"), PersistentDataType.STRING)).type == ItemType.Sword) {
			return true;
		}
	}else {
		return false;
	}
	return false;
	}

}
