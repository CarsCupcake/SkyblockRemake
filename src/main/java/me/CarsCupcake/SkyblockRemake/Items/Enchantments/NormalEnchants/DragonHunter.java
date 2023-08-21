package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class DragonHunter extends CustomEnchantment {
    public DragonHunter() {
        super(new NamespacedKey(Main.getMain(), "dragon_hunter"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.getCombat().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Increases damage dealt", "§7to Ender Dragons by §a%per%§7.")
                .addPlaceholder("%per%", (player, itemStack) -> (ItemHandler.getEnchantmentLevel(this, itemStack) * 8) + "%");
    }

    @NotNull
    @Override
    public String getName() {
        return "Dragon Hunter";
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
