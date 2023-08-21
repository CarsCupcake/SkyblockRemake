package me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class FatalTempo extends UltimateEnchant {
    public FatalTempo() {
        super(new NamespacedKey(Main.getMain(), "FATAL_TEMPO"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Fatal Tempo";
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
        return Tools.combine(Tools.combine(ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]), ItemType.Type.Bow.getTypeList().toArray(new ItemType[0])),
                new ItemType[]{ItemType.FishingRod});
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Attacking increases your §c⫽", "§cFerocity §7by §c%level%0% §7per hit,", "§7capped at §c200% §7for 3 seconds", "§7after your last attack.");
    }
}
