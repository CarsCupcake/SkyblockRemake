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

public class Thunderbolt extends CustomEnchantment {
    public Thunderbolt() {
        super(new NamespacedKey(Main.getMain(), "thunderbolt"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Strikes Monsters within §62 §7blocks with", "§7lightning every §63 §7consecutive hits,", "§7dealing §a%p% §7of your damage.")
                .addPlaceholder("%p%", (player, itemStack) -> getBoost(ItemHandler.getEnchantmentLevel(this, itemStack)) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Thunderbolt";
    }

    @Override
    public int getMaxLevel() {
        return 6;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    public int getBoost(int level) {
        if (level < 6) return level * 4;
        if (level == 6) return 25;
        return 8 * 4;
    }

    @Override
    public List<CustomEnchantment> conflictEnchants() {
        return List.of(SkyblockEnchants.THUNDERLORD);
    }
}
