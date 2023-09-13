package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Reflection extends CustomEnchantment {
    public Reflection() {
        super(new NamespacedKey(Main.getMain(), "reflection"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[]{ItemType.Chestplate};
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Grants §b+%mana%✎ Intelligence§7.", "§7Grants §f+%def%❂ True Defense", "§7When damaged by an arrow, deal §b%m% §7your ", "§b✎ Intelligence §7to its shooter.")
                .addPlaceholder("%mana%", (player, itemStack) -> "" +(ItemHandler.getEnchantmentLevel(this, itemStack) * 2))
                .addPlaceholder("%def%", (player, itemStack) -> "" +(ItemHandler.getEnchantmentLevel(this, itemStack)))
                .addPlaceholder("%m%", (player, itemStack) -> "" +getMult(ItemHandler.getEnchantmentLevel(this, itemStack)));
    }

    @NotNull
    @Override
    public String getName() {
        return "Reflection";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
    public int getMult(int level) {
        return switch (level) {
            case 1 -> 2;
            case 2 -> 5;
            case 3 -> 10;
            case 4 -> 20;
            default -> 30;
        };
    }
}
