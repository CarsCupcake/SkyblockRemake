package me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class LastStand extends UltimateEnchant {
    public LastStand() {
        super(new NamespacedKey(Main.getMain(), "LAST_STAND"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Last_Stand";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Armor.getTypeList().toArray(new ItemType[0]);
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Gain §a+%def%% ❈ Defense §7when", "§7hit while below §c40%❤§7.")
                .addPlaceholder("%def%", (player, itemStack) -> String.valueOf(5* ItemHandler.getEnchantmentLevel(this, itemStack)));
    }
}
