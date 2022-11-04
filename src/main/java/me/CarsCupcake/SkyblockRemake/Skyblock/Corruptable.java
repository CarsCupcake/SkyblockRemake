package me.CarsCupcake.SkyblockRemake.Skyblock;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public interface Corruptable {
    void corrupt();
    boolean isCorrupted();
    static ArrayList<ItemStack> getCorruptedLoot(SkyblockPlayer player){
        return null;
    }
}
