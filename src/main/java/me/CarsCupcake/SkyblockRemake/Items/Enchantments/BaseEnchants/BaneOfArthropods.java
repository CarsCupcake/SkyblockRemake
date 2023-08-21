package me.CarsCupcake.SkyblockRemake.Items.Enchantments.BaseEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

public class BaneOfArthropods extends CustomEnchantment {
    public BaneOfArthropods() {
        super(Enchantment.DAMAGE_ARTHROPODS.getKey());
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[0];
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Increases damage dealt to", "§7Spiders, Silverfish, and", "§7Cave Spiders by §a%per%%§7.").addPlaceholder("%per%", (player, itemStack) -> String.valueOf(getAmount(ItemHandler.getEnchantmentLevel(this, itemStack))));
    }

    @NotNull
    @Override
    public String getName() {
        return "Bane of Arthropods";
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
        if (level < 5) return level * 10;
        return level * 20;
    }
}
