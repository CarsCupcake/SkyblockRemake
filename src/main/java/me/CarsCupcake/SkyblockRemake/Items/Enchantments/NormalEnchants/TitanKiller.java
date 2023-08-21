package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TitanKiller extends CustomEnchantment {
    public TitanKiller() {
        super(new NamespacedKey(Main.getMain(), "titan_killer"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Increases damage dealt by §a%per%", "§7for every §a100 ❈ Defense §7your target", "§7has up to §a%per%§7.")
                .addPlaceholder("%per%", (player, itemStack) -> Tools.cleanDouble(getPers(ItemHandler.getEnchantmentLevel(this, itemStack))) + "%")
                .addPlaceholder("%per%", (player, itemStack) -> getTarget(ItemHandler.getEnchantmentLevel(this, itemStack)) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Titan Killer";
    }

    @Override
    public int getMaxLevel() {
        return 7;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
    public static double getPers(int level) {
        if (level < 5) return level * 2;
        if(level == 5) return 12;
        if (level == 6) return 16;
        if(level == 7) return 20;
        return level * 3;
    }
    public static double getTarget(int level) {
        if (level < 5) return level * 6;
        if(level == 5) return 40;
        if (level == 6) return 60;
        if(level == 7) return 80;
        return level * 10;
    }

    @Override
    public List<CustomEnchantment> conflictEnchants() {
        return List.of(SkyblockEnchants.GIANT_KILLER);
    }
}
