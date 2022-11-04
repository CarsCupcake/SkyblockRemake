package me.CarsCupcake.SkyblockRemake.Enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;




public abstract class CustomEnchantment extends Enchantment{

	public CustomEnchantment(NamespacedKey key) {
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
	public boolean canEnchantItem(ItemStack arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}

