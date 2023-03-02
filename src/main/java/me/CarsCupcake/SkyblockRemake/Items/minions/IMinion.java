package me.CarsCupcake.SkyblockRemake.Items.minions;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public interface IMinion {
    int getLevels();
    String[] getHeadStrings();
    String name();

    /**
     * Get the drops for 1 generate
     * @return Hasmap with a bundle Item - Count and a double for the chance
     */
    HashMap<Bundle<ItemManager, Integer>, Double> drops();

    /**
     * Is for the time between the working actions
     * @return Time in ticks
     */
    long[] timeBetweenActions();
    HashMap<EquipmentSlot, ItemStack> getEquipment();
    ItemStack getItemInHand();
    String id();

    HashMap<String, IMinion> minions = new HashMap<>();
}
