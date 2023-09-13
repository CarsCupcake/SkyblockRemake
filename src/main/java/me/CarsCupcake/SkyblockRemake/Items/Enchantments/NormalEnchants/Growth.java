package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Growth extends CustomEnchantment {
    public Growth() {
        super(new NamespacedKey(Main.getMain(), "growth"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Armor.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Greants §a%a% §c❤ Health§7.")
                .addPlaceholder("%a%", (player, itemStack) -> "" + getBoost(ItemHandler.getEnchantmentLevel(this, itemStack)));
    }

    @NotNull
    @Override
    public String getName() {
        return "Growth";
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
        return level * 15;
    }
}
