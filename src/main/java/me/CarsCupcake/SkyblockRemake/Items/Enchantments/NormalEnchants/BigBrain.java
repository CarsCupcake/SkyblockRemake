package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class BigBrain extends CustomEnchantment {
    public BigBrain() {
        super(new NamespacedKey(Main.getMain(), "big_brain"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[]{ItemType.Helmet};
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Grants §b+%i% ✎ Intelligence")
                .addPlaceholder("%i%", (player, itemStack) -> "" + getBonus(ItemHandler.getEnchantmentLevel(this, itemStack)));
    }

    @NotNull
    @Override
    public String getName() {
        return "Big Brain";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 3;
    }
    public int getBonus(int level) {
        return level * 5;
    }
}
