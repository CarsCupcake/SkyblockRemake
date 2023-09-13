package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Snipe extends CustomEnchantment {
    public Snipe() {
        super(new NamespacedKey(Main.getMain(), "snipe"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[0];
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Arrows deal §a%l% §7damage for", "§7every §610 §7blocks traveled.")
                .addPlaceholder("%l%", (player, itemStack) -> ItemHandler.getEnchantmentLevel(this, itemStack) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Snipe";
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
}
