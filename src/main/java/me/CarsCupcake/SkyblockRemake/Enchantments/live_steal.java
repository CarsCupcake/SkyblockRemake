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
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isTreasure() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isCursed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int getStartLevel() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Live Steal";
	}
	
	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	@Override
	public EnchantmentTarget getItemTarget() {
		// TODO Auto-generated method stub
		return EnchantmentTarget.WEAPON;
	}
	
	@Override
	public boolean conflictsWith(Enchantment arg0) {
		// TODO Auto-generated method stub
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
