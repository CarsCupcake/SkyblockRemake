package me.CarsCupcake.SkyblockRemake.Items.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.Items.AbilityLore;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Syphon extends CustomEnchantment {
    public Syphon() {
        super(new NamespacedKey(Main.getMain(), "syphon"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Syphon";
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
    public @NotNull AbilityLore getLore() {
        return new AbilityLore(List.of("§7Heals for §a%pers%% of your", "§7max health per §9100 ☠ Crit Damage", "§7you deal per hit, up to §91,000 ☠ Crit Damage§7."))
                .addPlaceholder("%pers%", (player, itemStack) -> ((ItemHandler.getEnchantmentLevel(this, itemStack) * 0.1) + 0.1) + "");
    }

    @Override
    public ItemType[] getAllowedTypes() {
        return ItemType.Type.Sword.getTypeList().toArray(new ItemType[0]);
    }
}
