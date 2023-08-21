package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class EnderSlayer extends CustomEnchantment {
    public EnderSlayer() {
        super(new NamespacedKey(Main.getMain(), "ender_slayer"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Increases damage dealt to Ender", "§7Dragons and Endermen by §a%per%§7.")
                .addPlaceholder("%per%", (player, itemStack) -> getStatBoost(ItemHandler.getEnchantmentLevel(this, itemStack)) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Ender Slayer";
    }

    @Override
    public int getMaxLevel() {
        return 7;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
    public int getStatBoost(int level) {
        if(level < 5) return level * 15;
        if(level == 5) return 80;
        if(level == 6) return 100;
        if(level == 7) return 130;
        return 20 * level;
    }
}
