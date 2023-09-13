package me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Power extends CustomEnchantment {
    public Power() {
        super(ARROW_DAMAGE.getKey());
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Bow.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Increases bow damage by §a%p%")
                .addPlaceholder("%p%", (player, itemStack) -> getBoost(ItemHandler.getEnchantmentLevel(this, itemStack)) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Power";
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
        if (level < 6) return level * 8;
        if (level == 6) return 50;
        if (level == 7) return 65;
        return (level * 5) + 30;
    }
}
