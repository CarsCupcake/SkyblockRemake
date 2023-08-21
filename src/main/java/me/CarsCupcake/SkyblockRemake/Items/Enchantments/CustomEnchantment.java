package me.CarsCupcake.SkyblockRemake.Items.Enchantments;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public abstract class CustomEnchantment extends Enchantment{

	public CustomEnchantment(NamespacedKey key) {
		super(key);
		// TODO Auto-generated constructor stub
	}
	@Override
	public final boolean isTreasure() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public final boolean isCursed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public final @NotNull EnchantmentTarget getItemTarget() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean conflictsWith(@NotNull Enchantment arg0) {
		if(arg0 instanceof CustomEnchantment ce)
			return conflictEnchants().contains(ce);
		return false;
	}
	public List<CustomEnchantment> conflictEnchants() {
		return new ArrayList<>();
	}
	
	@Override
	public boolean canEnchantItem(@NotNull ItemStack arg0) {
		return true;
	}
	public abstract ItemType[] getAllowedTypes();
	@NotNull
	public abstract AbilityLore getLore();

	public static CustomEnchantment toCustomEnchantment(Enchantment enchantment) {
		if(enchantment instanceof CustomEnchantment ce) return ce;
		return SkyblockEnchants.registeredEnchants.get(enchantment.getKey().getKey());
	}

}

