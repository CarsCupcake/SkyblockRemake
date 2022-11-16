package me.CarsCupcake.SkyblockRemake.Items;

import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class ItemHandler {
    public static <T, Z>  boolean hasPDC(String str, ItemStack item, PersistentDataType<T,Z> type){
        if(item != null && item.getItemMeta() != null){
            return item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), str), type);
        }
        return false;
    }
    public static <T,Z> Z getPDC(String str, ItemStack item, PersistentDataType<T, Z> type) throws NullPointerException {
        return item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), str), type);
    }
}
