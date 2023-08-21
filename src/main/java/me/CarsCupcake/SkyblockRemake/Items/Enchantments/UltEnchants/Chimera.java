package me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Chimera extends UltimateEnchant {
    public Chimera() {
        super(new NamespacedKey(Main.getMain(), "chimera"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Chimera";
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
        return ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Copies §a%pers%% §7of your active", "§7pet's stats.").addPlaceholder("%pers", (player, itemStack) -> String.valueOf(20* ItemHandler.getEnchantmentLevel(this, itemStack)));
    }
}
