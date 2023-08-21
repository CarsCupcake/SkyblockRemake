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

public class Execute extends CustomEnchantment {
    public Execute() {
        super(new NamespacedKey(Main.getMain(), "execute"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Increases damage dealt by", "§a%pers% §7for each percent of health", "§7missing on your target.")
                .addPlaceholder("%pers%", (player, itemStack) -> getPers(ItemHandler.getEnchantmentLevel(this, itemStack)) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Execute";
    }

    @Override
    public int getMaxLevel() {
        return 6;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
    public double getPers(int level) {
        if (level < 6) return level * 0.2;
        if (level == 6) return 1.25;
        return level * 0.25;
    }

    @Override
    public List<CustomEnchantment> conflictEnchants() {
        return List.of(SkyblockEnchants.PROSECUTE);
    }
}
