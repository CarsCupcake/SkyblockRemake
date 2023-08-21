package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Prosecute extends CustomEnchantment {
    public Prosecute() {
        super(new NamespacedKey(Main.getMain(), "prosecute"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[0];
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("Increases damage dealt by §a%pers%", "§7for each percent of §c♥ Health", "§7your target has.")
                .addPlaceholder("%pers%", (player, itemStack) -> getPers(ItemHandler.getEnchantmentLevel(this, itemStack)) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Prosecute";
    }

    @Override
    public int getMaxLevel() {
        return 6;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public List<CustomEnchantment> conflictEnchants() {
        return List.of(SkyblockEnchants.EXECUTE);
    }

    public double getPers(int level) {
        if (level < 5) return level * 0.1;
        if(level == 5) return 0.7;
        if(level == 6) return 1;
        return level * 0.15;
    }
}
