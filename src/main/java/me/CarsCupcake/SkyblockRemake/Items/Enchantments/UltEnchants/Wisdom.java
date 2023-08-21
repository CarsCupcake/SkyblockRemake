package me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Wisdom extends UltimateEnchant {
    public Wisdom() {
        super(new NamespacedKey(Main.getMain(), "Wisdom"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Wisdom";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Armor.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Gain §b%level% ✎ Intelligence §7for every §b5 §7levels of", "§7exp you have on you. Capped at §b%l2%0 ✎", "§bIntelligence§7.")
                .addPlaceholder("%level%", (player, itemStack) -> String.valueOf(ItemHandler.getEnchantmentLevel(this, itemStack)))
                .addPlaceholder("%l2%", (player, itemStack) -> String.valueOf(2 * ItemHandler.getEnchantmentLevel(this, itemStack)));
    }
}
