package me.CarsCupcake.SkyblockRemake.Items.Enchantments;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class enchant_glient extends CustomEnchantment{

	public enchant_glient(NamespacedKey key) {
		super(key);
	}

	@Override
	public ItemType[] getAllowedTypes() {
		return ItemType.values();
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public @NotNull String getName() {
		return "non";
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public @NotNull AbilityLore getLore() {
		return new AbilityLore(List.of());
	}
}
