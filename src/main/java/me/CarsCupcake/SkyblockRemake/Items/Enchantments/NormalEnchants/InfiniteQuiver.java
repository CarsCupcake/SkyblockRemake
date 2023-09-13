package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class InfiniteQuiver extends CustomEnchantment {
    public InfiniteQuiver() {
        super(new NamespacedKey(Main.getMain(), "infinit_quiver"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Bow.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("Save arrows §a%p% §7of the time", "§7when you fire your bow. Disabled", "§7while sneaking.");
    }

    @NotNull
    @Override
    public String getName() {
        return "Infinite Quiver";
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
}
