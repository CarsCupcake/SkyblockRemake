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

public class GiantKiller extends CustomEnchantment {
    public GiantKiller() {
        super(new NamespacedKey(Main.getMain(), "giant_killer"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Increases damage dealt by §a%per%", "§7for each percent of extra", "§7health that your target has", "§7above you up to §a%target%§7.")
                .addPlaceholder("%per%", (player, itemStack) -> Tools.cleanDouble(getPers(ItemHandler.getEnchantmentLevel(this, itemStack))) + "%")
                .addPlaceholder("%per%", (player, itemStack) -> getTarget(ItemHandler.getEnchantmentLevel(this, itemStack)) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Giant Killer";
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
        if (level < 5) return level * 0.1;
        if(level == 5) return 0.6;
        if (level == 6) return 0.9;
        if(level == 7) return 1.2;
        return level * 0.2;
    }
    public static double getTarget(int level) {
        if (level < 5) return level * 5;
        if(level == 5) return 30;
        if (level == 6) return 45;
        if(level == 7) return 65;
        return level * 10;
    }

    @Override
    public List<CustomEnchantment> conflictEnchants() {
        return List.of(SkyblockEnchants.TITAN_KILLER);
    }
}
