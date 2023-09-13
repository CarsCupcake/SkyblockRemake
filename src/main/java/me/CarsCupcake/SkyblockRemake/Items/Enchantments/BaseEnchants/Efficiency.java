package me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

public class Efficiency extends CustomEnchantment {
    public Efficiency() {
        super(Enchantment.DIG_SPEED.getKey());
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Tool.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Grants §6+%a% Mining Speed§7.")
                .addPlaceholder("%a%", (player, itemStack) -> (10 +(ItemHandler.getEnchantmentLevel(this, itemStack) * 20)) + "");
    }

    @NotNull
    @Override
    public String getName() {
        return "Efficiency";
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
