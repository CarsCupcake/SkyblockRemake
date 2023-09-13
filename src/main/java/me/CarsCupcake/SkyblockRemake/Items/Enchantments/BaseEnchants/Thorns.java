package me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

public class Thorns extends CustomEnchantment {
    public Thorns() {
        super(new NamespacedKey(Main.getMain(), Enchantment.THORNS.getKey().getKey()));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Armor.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Grants a §a50% §7chance to", "§7rebound §a%p% §7of damage dealt", "§7back at the attacker.")
                .addPlaceholder("%p%", (player, itemStack) -> (ItemHandler.getEnchantmentLevel(this, itemStack) * 3) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Thorns";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
}
