package me.CarsCupcake.SkyblockRemake.Items;

import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
    public static <T,Z> void setPDC(String str, ItemStack item, PersistentDataType<T,Z> type, Z val){
        ItemMeta meta = item.getItemMeta();


        meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), str), type, val);

        item.setItemMeta(meta);
    }

    public static boolean hasEnchantment(Enchantment enchantment, ItemStack item){
        if(item != null && item.getItemMeta() != null){
            return item.getItemMeta().getEnchants().containsKey(enchantment);
        }
        return false;
    }
}
