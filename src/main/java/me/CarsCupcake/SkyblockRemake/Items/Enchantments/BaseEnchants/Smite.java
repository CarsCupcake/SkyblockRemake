package me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Smite extends CustomEnchantment {
    public Smite() {
        super(Enchantment.DAMAGE_UNDEAD.getKey());
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Increases damage dealt to Skeletons", "§7Zombies, Zombie Pigman, Withers,", "§7and Zombies by §a%per%§7.")
                .addPlaceholder("%per%", (player, itemStack) -> getBoost(ItemHandler.getEnchantmentLevel(this, itemStack)) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Smite";
    }

    @Override
    public int getMaxLevel() {
        return 7;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    public int getBoost(int level) {
        if (level < 5) return level * 10;
        if (level == 5) return 60;
        if (level == 6) return 80;
        if (level == 7) return 100;
        return level * 15;
    }

    @Override
    public List<CustomEnchantment> conflictEnchants() {
        return List.of(SkyblockEnchants.SHARPNESS, SkyblockEnchants.BANE_OF_ARTHROPODS);
    }
}
