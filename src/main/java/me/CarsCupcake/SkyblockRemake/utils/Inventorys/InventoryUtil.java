package me.CarsCupcake.SkyblockRemake.utils.Inventorys;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtil {


    public static List<Integer> sideSlots = new ArrayList<>();


    public static void fillSidesWithItem(ItemStack item , Inventory  inv , int row) {
        int size = inv.getSize();
        int rows = size / row;

        if(rows >= 3) {
            for (int i = 0; i <= 8; i++) {
                inv.setItem(i, item);

                sideSlots.add(i);
            }

            for(int s = 8; s < (inv.getSize() - 9); s += 9) {
                int lastSlot = s + 1;
                inv.setItem(s, item);
                inv.setItem(lastSlot, item);

                sideSlots.add(s);
                sideSlots.add(lastSlot);
            }

            for (int lr = (inv.getSize() - 9); lr < inv.getSize(); lr++) {
                inv.setItem(lr, item);

                sideSlots.add(lr);
            }
        }
    }

    public List<Integer> getNonSideSlots(Inventory inv) {
        List<Integer> availableSlots = new ArrayList<>();

        for (int i = 0; i < inv.getSize(); i++) {
            if(!this.sideSlots.contains(i)) {
                availableSlots.add(i);
            }
        }

        return availableSlots;
    }
}




