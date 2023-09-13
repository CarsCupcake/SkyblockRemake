package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Experience extends CustomEnchantment {
    public Experience() {
        super(new NamespacedKey(Main.getMain(), "experience"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return Tools.combine(ItemType.Type.Tool
                .getTypeList().toArray(new ItemType[0]),
                ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]));
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Grants a §a%per% §7chance", "§7for mobs and ores to", "§7drop double experience.")
                .addPlaceholder("%per%", (player, itemStack) -> Tools.cleanDouble(ItemHandler.getEnchantmentLevel(this, itemStack) * 12.5) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Experience";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
}
