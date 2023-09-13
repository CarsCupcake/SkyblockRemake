package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Piercing extends CustomEnchantment {
    public Piercing() {
        super(new NamespacedKey(Main.getMain(), "piercing"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[0];
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Arrows travel through enemies.", "§7The extra targets hit take §a%p%", "§7of the damage.")
                .addPlaceholder("%p%", (player, itemStack) -> (ItemHandler.getEnchantmentLevel(this, itemStack) * 25) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Piercing";
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
