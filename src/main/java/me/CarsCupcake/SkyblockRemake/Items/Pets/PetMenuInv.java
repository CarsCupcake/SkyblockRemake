package me.CarsCupcake.SkyblockRemake.Items.Pets;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.configs.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Main;

public class PetMenuInv {
    public static Inventory createMenu(SkyblockPlayer player) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GRAY + "Pets");
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        for (int i = 0; i < 46; i += 9) {
            inv.setItem(i, item);
        }
        for (int i = 8; i < 46 + 8; i += 9) {
            inv.setItem(i, item);
        }
        inv.setItem(1, item);
        inv.setItem(2, item);
        inv.setItem(3, item);
        inv.setItem(7, item);
        inv.setItem(6, item);
        inv.setItem(5, item);
        inv.setItem(52, item);
        inv.setItem(46, item);
        item.setType(Material.DIAMOND);
        meta.setDisplayName("§aPet Score Rewards");
        item.setItemMeta(meta);
        inv.setItem(47, item);
        item.setType(Material.ARROW);
        meta.setDisplayName("§aGo Back");
        item.setItemMeta(meta);
        inv.setItem(48, item);
        item.setType(Material.BARRIER);
        meta.setDisplayName("§cClose");
        item.setItemMeta(meta);
        inv.setItem(49, item);
        item.setType(Material.GRAY_DYE);
        meta.setDisplayName("§aConvert Pet to an Item");
        item.setItemMeta(meta);
        inv.setItem(50, item);
        item.setType(Material.STONE_BUTTON);
        meta.setDisplayName("§aHide Pets");
        item.setItemMeta(meta);
        inv.setItem(51, item);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "View and manage all of your");
        lore.add(ChatColor.GRAY + "Pets.");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Level up your pets faster by");
        lore.add(ChatColor.GRAY + "gaining xp in ther favorite");
        lore.add(ChatColor.GRAY + "skill!");
        lore.add(" ");
        if (player.getPetEquip() != null)
            lore.add(ChatColor.GRAY + "Selected pet: " + player.getPetEquip().getPet().getRarity().getPrefix() + getPetName(player));
        else lore.add(ChatColor.GRAY + "Selected pet: " + ChatColor.RED + "None");
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Click to view!");
        int i = 10;
        ConfigFile file = new ConfigFile(player, "pet", true);
        for (String str : file.get().getKeys(false)) {
            if (str == null) continue;
            if (str.equals("equiped")) continue;
            if (!(i > 44)) {
                Pet petOBJ = (Pet) Items.SkyblockItems.get(file.get().getString(str + ".id"));
                if (petOBJ == null) continue;
                ItemStack pet = Main.itemUpdater(petOBJ.getRawItemStack(), SkyblockPlayer.getSkyblockPlayer(player));
                ItemMeta m = pet.getItemMeta();
                PersistentDataContainer data = m.getPersistentDataContainer();
                if (!file.get().getBoolean(str + ".equiped") || player.getPetEquip() == null) {
                    data.set(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER, file.get().getInt(str + ".level"));
                    data.set(new NamespacedKey(Main.getMain(), "currxp"), PersistentDataType.DOUBLE, file.get().getDouble(str + ".currxp"));
                } else {
                    data.set(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER, player.getPetEquip().getLevel());
                    data.set(new NamespacedKey(Main.getMain(), "currxp"), PersistentDataType.DOUBLE, player.getPetEquip().getXp());
                }
                data.set(new NamespacedKey(Main.getMain(), "fileid"), PersistentDataType.INTEGER, Integer.parseInt(str));
                data.set(new NamespacedKey(Main.getMain(), "pet"), PersistentDataType.STRING, "1");

                pet.setItemMeta(m);
                pet = Main.itemUpdater(pet, SkyblockPlayer.getSkyblockPlayer(player));

                m = pet.getItemMeta();
                lore = (ArrayList<String>) m.getLore();
                lore.add(" ");
                if (file.get().getBoolean(str + ".equiped")) lore.add("§cClick to despawn!");
                else lore.add("§eClick to spawn!");
                m.setLore(lore);
                pet.setItemMeta(m);
                inv.setItem(i, pet);
            }
            i++;
            if (i == 17 || i == 26 || i == 35) i += 2;
        }
        return inv;
    }
    public static String getPetName(SkyblockPlayer player) {
        String name = null;

        try {
            Pet pet = player.getPetEquip().getPet();
            name = pet.name;
        } catch (Exception ignored) {
        }
        return name;
    }

}
