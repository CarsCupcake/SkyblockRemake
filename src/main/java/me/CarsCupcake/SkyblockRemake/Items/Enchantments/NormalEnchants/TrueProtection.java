package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class TrueProtection extends CustomEnchantment {
    public TrueProtection() {
        super(new NamespacedKey(Main.getMain(), "true_protection"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[] {ItemType.Chestplate};
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Grants §f%am% ❁ True Defense")
                .addPlaceholder("%am%", (player, itemStack) -> "+" + (5 * ItemHandler.getEnchantmentLevel(this, itemStack)));
    }

    @NotNull
    @Override
    public String getName() {
        return "True Protection";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
}
