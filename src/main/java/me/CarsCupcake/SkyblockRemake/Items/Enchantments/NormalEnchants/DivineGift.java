package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class DivineGift extends CustomEnchantment {
    public DivineGift() {
        super(new NamespacedKey(Main.getMain(), "divine_gift"));
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return Tools.combine(ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]), ItemType.Type.Bow.getTypeList().toArray(new ItemType[0]));
    }

    @Override
    public @NotNull AbilityLore getLore() {
        return new AbilityLore("§7Grants §b+%val% ✯ Magic Find")
                .addPlaceholder("%val%", (player, itemStack) -> "" + (ItemHandler.getEnchantmentLevel(this,itemStack) * 2));
    }

    @NotNull
    @Override
    public String getName() {
        return "Divine Gift";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }
}
