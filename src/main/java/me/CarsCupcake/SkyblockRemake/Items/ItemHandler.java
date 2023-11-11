package me.CarsCupcake.SkyblockRemake.Items;

import lombok.SneakyThrows;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.ReflectionUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

public class ItemHandler {
    public static PersistentDataType<Byte, Boolean> BOOLEAN = new PersistentDataType<>() {
        @NotNull
        @Override
        public Class<Byte> getPrimitiveType() {
            return Byte.TYPE;
        }

        @NotNull
        @Override
        public Class<Boolean> getComplexType() {
            return Boolean.TYPE;
        }

        @NotNull
        @Override
        public Byte toPrimitive(@NotNull Boolean aBoolean, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
            return (byte)((aBoolean) ? 1 : 0);
        }

        @NotNull
        @Override
        public Boolean fromPrimitive(@NotNull Byte aByte, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
            Assert.state(aByte < 2, "Byte is in a illegal state!");
            return aByte == 1;
        }
    };
    public static <T, Z> boolean hasPDC(String str, ItemStack item, PersistentDataType<T, Z> type) {
        if (item != null && item.getItemMeta() != null) {
            return item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), str), type);
        }
        return false;
    }

    public static <T, Z> Z getPDC(String str, ItemStack item, PersistentDataType<T, Z> type) throws NullPointerException {
        return item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), str), type);
    }

    public static <T, Z> Z getOrDefaultPDC(String str, ItemStack item, PersistentDataType<T, Z> type, Z def) throws NullPointerException {
        return item.getItemMeta().getPersistentDataContainer().getOrDefault(new NamespacedKey(Main.getMain(), str), type, def);
    }

    public static <T, Z> void setPDC(String str, ItemStack item, PersistentDataType<T, Z> type, Z val) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), str), type, val);
        item.setItemMeta(meta);
    }

    public static void removePDC(String str, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().remove(new NamespacedKey(Main.getMain(), str));
        item.setItemMeta(meta);
    }

    public static boolean hasEnchantment(Enchantment enchantment, ItemStack item) {
        if (item != null && item.getItemMeta() != null) {
            return item.getItemMeta().getEnchants().containsKey(enchantment);
        }
        return false;
    }

    public static boolean hasEnchantment(Enchantment enchantment, Projectile item) {
        for (String s : item.getScoreboardTags())
            if (s.startsWith(enchantment.getKey().getKey())) return true;
        return false;
    }

    public static int getEnchantmentLevel(Enchantment enchantment, Projectile item) {
        for (String s : item.getScoreboardTags())
            if (s.startsWith(enchantment.getKey().getKey())) return Integer.parseInt(s.split(":")[1]);
        return 0;
    }

    public static int getEnchantmentLevel(Enchantment enchantment, ItemStack item) {
        if (item == null || !item.hasItemMeta()) return 0;
        if (!hasEnchantment(enchantment, item)) return 0;
        return item.getItemMeta().getEnchants().get(enchantment);
    }
    public static void setEnchant(@NotNull Enchantment enchantment, int level, ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, level, true);
        item.setItemMeta(meta);
    }
    public static void removeEnchant(@NotNull Enchantment enchantment, ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.removeEnchant(enchantment);
        item.setItemMeta(meta);
    }

    @SneakyThrows
    public static void registerAll(Object obj) {
        Assert.notNull(obj, "Class has to be not null!");
        for (Method method : obj.getClass().getMethods())
            if (method.getAnnotation(me.CarsCupcake.SkyblockRemake.Items.ItemRegistration.ItemHandler.class) != null) {
                if (method.isBridge() || method.isSynthetic()) {
                    continue;
                }
                Assert.isTrue(method.getReturnType() == ItemManager.class, "Returntype is wrong!");

                ReflectionUtils.makeAccessible(method);

                ItemManager m = (ItemManager) method.invoke(obj, (Object) null);
                Items.SkyblockItems.put(m.itemID, m);
                System.out.println("Added " + m.itemID);

            }
        for (Method method : obj.getClass().getDeclaredMethods())
            if (method.getAnnotation(me.CarsCupcake.SkyblockRemake.Items.ItemRegistration.ItemHandler.class) != null) {
                if (method.isBridge() || method.isSynthetic()) {
                    continue;
                }
                Assert.isTrue(method.getReturnType() == ItemManager.class, "Returntype is wrong!");

                ReflectionUtils.makeAccessible(method);

                ItemManager m = (ItemManager) method.invoke(obj, (Object) null);
                Items.SkyblockItems.put(m.itemID, m);
                System.out.println("Added " + m.itemID);

            }
    }
    public static ItemManager getItemManager(ItemStack item) {
        if(item == null || !item.hasItemMeta()) return null;
        return Items.SkyblockItems.get(getPDC("id", item, PersistentDataType.STRING));
    }
    public static void dungeonize(ItemStack item, boolean b) {
        Assert.isTrue(getItemManager(item).isDungenoizanble(), "Item is not dungeonizable!");
        setPDC("dungeon", item, BOOLEAN, b);
    }
    public static boolean isDungeonItem(ItemStack item) {
        if (getItemManager(item).isDungeonItem) return true;
        if (!hasPDC("dungeon", item, BOOLEAN)) return false;
        return getPDC("dungeon", item, BOOLEAN);
    }

}
