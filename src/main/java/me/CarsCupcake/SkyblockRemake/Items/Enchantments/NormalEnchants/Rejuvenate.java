package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class Rejuvenate extends CustomEnchantment {
    public Rejuvenate() {
        super(new NamespacedKey(Main.getMain(), "rejuvenate"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return new ItemType[0];
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Grants §c+%a%❣ Health Regen")
                .addPlaceholder("%a%", (player, itemStack) -> "" + (ItemHandler.getEnchantmentLevel(this, itemStack) * 2));
    }

    @NotNull
    @Override
    public String getName() {
        return "Rejuvenate";
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
