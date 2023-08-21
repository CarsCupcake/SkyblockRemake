package me.CarsCupcake.SkyblockRemake.Items.Enchantments;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class UltimateEnchant extends CustomEnchantment {
    public UltimateEnchant(@NotNull NamespacedKey key) {
        super(key);
    }
    public static Bundle<ArrayList<UltimateEnchant>, ArrayList<CustomEnchantment>> splitEnchants(Set<CustomEnchantment> enchantments){
        ArrayList<UltimateEnchant> ultimateEnchants = new ArrayList<>();
        ArrayList<CustomEnchantment> normalEnchants = new ArrayList<>();

        for(CustomEnchantment enchantment : enchantments) {
            if(enchantment == null) continue;
            if (isUltEnchant(enchantment))
                ultimateEnchants.add((UltimateEnchant) enchantment);
            else
                normalEnchants.add(enchantment);
        }

        return new Bundle<>(ultimateEnchants, normalEnchants);
    }
    public static ArrayList<CustomEnchantment> orderEnchants(Bundle<ArrayList<UltimateEnchant>, ArrayList<CustomEnchantment>> bundle){
        ArrayList<CustomEnchantment> enchantments = new ArrayList<>();
        enchantments.addAll(bundle.getFirst());
        enchantments.addAll(bundle.getLast());
        return enchantments;
    }
    public static boolean isUltEnchant(CustomEnchantment enchantment){
        return (SkyblockEnchants.ultEnchantIDs.contains(enchantment.getKey().getKey()) || enchantment instanceof UltimateEnchant);
    }

    @Override
    public List<CustomEnchantment> conflictEnchants() {
        List<CustomEnchantment> enchants = new ArrayList<>();
        for (String s : SkyblockEnchants.ultEnchantIDs) {
            enchants.add(SkyblockEnchants.registeredEnchants.get(s));
        }
        return enchants;
    }
}
