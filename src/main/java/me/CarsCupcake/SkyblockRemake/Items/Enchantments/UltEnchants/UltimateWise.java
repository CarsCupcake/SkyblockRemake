package me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class UltimateWise extends UltimateEnchant {
    public UltimateWise() {
        super(new NamespacedKey(Main.getMain(), "Ultimate_Wise"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Ultimate Wise";
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
    public double getPersentage(int level){
        if(level > 10)
            return 1;
        if(level < 1)
            return 0;
        return level * 0.1;
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.values();
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Reduces the ability mana cost of", "§7this item by §a%level%0%§7.")
                .addPlaceholder("%level%", (player, itemStack) -> String.valueOf(ItemHandler.getEnchantmentLevel(this, itemStack)));
    }
}
