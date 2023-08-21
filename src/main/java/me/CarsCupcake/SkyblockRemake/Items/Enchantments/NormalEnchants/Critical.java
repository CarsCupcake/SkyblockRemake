package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Critical extends CustomEnchantment {
    public Critical() {
        super(new NamespacedKey(Main.getMain(), "critical"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[0];
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Increases §9☠ Crit Damage §7by", "§a%pers%§7.").addPlaceholder("%pers%", (player, itemStack) ->
                getBoost(ItemHandler.getEnchantmentLevel(this, itemStack)) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Critical";
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }
    public int getBoost(int level) {
        if (level < 6) return level * 10;
        if (level == 6) return 70;
        if (level == 7) return 100;
        return level * 15;
    }
}
