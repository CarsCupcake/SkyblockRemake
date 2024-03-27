package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Telikinesis extends CustomEnchantment {

	public Telikinesis(NamespacedKey key) {
		super(key);
	}

	@Override
	public ItemType[] getAllowedTypes() {
		return new ItemType[0];
	}

	@Override
	public @NotNull AbilityLore getLore() {
		return new AbilityLore(List.of("§7Block or mob drops go directly", "§7into your inventory."));
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public @NotNull String getName() {
		return "Telikinesis";
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

}
