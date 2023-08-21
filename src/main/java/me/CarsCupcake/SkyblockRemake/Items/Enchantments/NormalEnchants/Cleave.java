package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Cleave extends CustomEnchantment {
    public Cleave() {
        super(new NamespacedKey(Main.getMain(), "cleave"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Deals §a%pers% §7of your damage", "§7dealt to other monsters within", "§a%block% §7blocks of the target.")
                .addPlaceholder("%pers%", (player, itemStack) -> getPersentage(ItemHandler.getEnchantmentLevel(this, itemStack)) + "%")
                .addPlaceholder("%block%", (player, itemStack) -> getBlockRange(ItemHandler.getEnchantmentLevel(this, itemStack)) + "");
    }

    @NotNull
    @Override
    public String getName() {
        return "Cleave";
    }

    @Override
    public int getMaxLevel() {
        return 6;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    public int getPersentage(int level) {
        if (level == 6) return 20;
        return level * 3;
    }
    public double getBlockRange(int level) {
        return (level * 0.3) + 3;
    }
}
