package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.abilities.Maid;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Pristine extends CustomEnchantment {
    public Pristine() {
        super(new NamespacedKey(Main.getMain(), "pristine"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[]{ItemType.Drill, ItemType.Pickaxe, ItemType.Gauntlet};
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Grants +%p% " + Stats.Pristine, "§7which increases the chance to", "§7improve the quality of dropped", "§dFemstones§7.").addPlaceholder("%p%", (player, itemStack) -> ItemHandler.getEnchantmentLevel(Pristine.this, itemStack) + "");
    }

    @NotNull
    @Override
    public String getName() {
        return "Pristine";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
}
