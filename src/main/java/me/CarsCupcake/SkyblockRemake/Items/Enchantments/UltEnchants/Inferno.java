package me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Inferno extends UltimateEnchant {
    public Inferno() {
        super(new NamespacedKey(Main.getMain(), "inferno"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Inferno";
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
        return Tools.combine(ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]), ItemType.Type.Bow.getTypeList().toArray(new ItemType[0]));
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Every §c10th §7hot on a mob", "§7traps it for §c5s §7and deals", "§c%dmg%x §7of that hit's damage", "§7over the trap duration.")
                .addPlaceholder("%dmg%", (player, itemStack) -> String.valueOf(1 + (0.25 * ItemHandler.getEnchantmentLevel(this, itemStack))));
    }
}
