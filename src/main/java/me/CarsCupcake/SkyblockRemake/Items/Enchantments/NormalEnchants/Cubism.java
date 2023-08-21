package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Cubism extends CustomEnchantment {
    public Cubism() {
        super(new NamespacedKey(Main.getMain(), "cubism"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return Tools.combine(ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]), ItemType.Type.Bow.getTypeList().toArray(new ItemType[0]));
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Increases damage dealt", "§7to Magma Cubes, Creepers,", "§7and Slimes by §a%pers%§7.")
                .addPlaceholder("%pers%",(player, itemStack) -> getPers(ItemHandler.getEnchantmentLevel(this, itemStack)) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Cubism";
    }

    @Override
    public int getMaxLevel() {
        return 6;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
    public int getPers(int level) {
        if(level < 5) return level * 10;
        if (level == 5) return 60;
        if(level == 6) return 80;
        return level * 15;
    }
}
