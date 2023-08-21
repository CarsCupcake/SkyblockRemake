package me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

public class Sharpness extends CustomEnchantment {
    public Sharpness() {
        super(Enchantment.DAMAGE_ALL.getKey());
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[0];
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Increases melee damage dealt by", "§a%dmg%%").addPlaceholder("%dmg%", (player, itemStack) -> String.valueOf(getAmount(ItemHandler.getEnchantmentLevel(this, itemStack))));
    }

    @NotNull
    @Override
    public String getName() {
        return "Sharpness";
    }

    @Override
    public int getMaxLevel() {
        return 7;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
    public static int getAmount(int level) {
        if(Tools.isInRange(4, 8, level)) {
            return switch (level) {
                case 5 -> 30;
                case 6 -> 45;
                case 7 -> 65;
                default -> throw new IllegalStateException("Unexpected value: " + level);
            };
        }
        return level * 5;
    }
}
