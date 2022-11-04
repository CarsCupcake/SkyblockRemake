package me.CarsCupcake.SkyblockRemake.Enchantments;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Set;

public abstract class UltimateEnchant extends Enchantment {
    public UltimateEnchant(@NotNull NamespacedKey key) {
        super(key);
    }
    public static Bundle<ArrayList<UltimateEnchant>, ArrayList<Enchantment>> splitEnchants(Set<Enchantment> enchantments){
        ArrayList<UltimateEnchant> ultimateEnchants = new ArrayList<>();
        ArrayList<Enchantment> normalEnchants = new ArrayList<>();

        for(Enchantment enchantment : enchantments)
            if(isUltEnchant(enchantment))
                ultimateEnchants.add((UltimateEnchant) enchantment);
            else
               normalEnchants.add(enchantment);

        return new Bundle<>(ultimateEnchants, normalEnchants);
    }
    public static ArrayList<Enchantment> orderEnchants(Bundle<ArrayList<UltimateEnchant>, ArrayList<Enchantment>> bundle){
        ArrayList<Enchantment> enchantments = new ArrayList<>();
        enchantments.addAll(bundle.getFirst());
        enchantments.addAll(bundle.getLast());
        return enchantments;
    }
    public static boolean isUltEnchant(Enchantment enchantment){
        return (SkyblockEnchants.ultEnchantIDs.contains(enchantment.getKey().getKey()) || enchantment instanceof UltimateEnchant);
    }

}
