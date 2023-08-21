package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Vampirism extends CustomEnchantment {
    public Vampirism() {
        super(new NamespacedKey(Main.getMain(), "vampirism"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Heals for §a%p% §7of your missing", "§7health whenever you kill an", "§7enemy.")
                .addPlaceholder("%p%", (player, itemStack) -> ItemHandler.getEnchantmentLevel(this, itemStack) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Vampirism";
    }

    @Override
    public int getMaxLevel() {
        return 6;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
}
