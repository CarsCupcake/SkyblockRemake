package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class SmartyPants extends CustomEnchantment {
    public SmartyPants() {
        super(new NamespacedKey(Main.getMain(), "smarty_pants"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[0];
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Grants §b%a% ✎ Intelligence")
                .addPlaceholder("%a%", (player, itemStack) -> "+" + (ItemHandler.getEnchantmentLevel(this, itemStack) * 5));
    }

    @NotNull
    @Override
    public String getName() {
        return "Smarty Pants";
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
