package me.CarsCupcake.SkyblockRemake.Potion;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PotionListener implements Listener {
    public PotionListener(){


        ArrayList<Potion> managers =   new ArrayList<>(Potion.getRegistered());
        managers.sort(Comparator.comparing(Potion::getName));

        int invs =1+  (managers.size() / 45);


        int list = 0;
        for(int invCount = 1; invCount <= invs; invCount++) {
            Inventory inv = Bukkit.createInventory(null, 54, "Potions Menu - Page " + invCount);
            ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer data = meta.getPersistentDataContainer();
            meta.setDisplayName(" ");
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
            for(int i = 45; i < 53; i++) {
                inv.setItem(i, item);
            }
            if(invCount != invs) {
                item.setType(Material.ARROW);

                data.set(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER, invCount);
                meta.setDisplayName("§aNext Page");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                inv.setItem(53, item);
            }else {
                data.set(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER, invCount);
                item.setItemMeta(meta);
                inv.setItem(53, item);
            }
            if(invCount != 1) {
                item.setType(Material.ARROW);
                meta.setDisplayName("§aLast Page");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                inv.setItem(45, item);
            }
            item.setType(Material.OAK_SIGN);
            meta.setDisplayName("§aSearch");
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
            inv.setItem(49, item);
            int i = 0;

            while(i < 45){


                if(!(list >= managers.size())) {
                    inv.setItem(i, Main.item_updater(managers.get(list).createNewItemStack(),null) );

                }




                i += 1;
                list++;

            }

            PotionCommand.getInvs().add(inv);

        }
    }

    @EventHandler
    public void onDrinking(PlayerItemConsumeEvent event) {
        if(event.getItem().getType() != Material.POTION)
            return;
        ItemStack item = event.getItem();
        if(!ItemHandler.hasPDC("potion", item, PersistentDataType.TAG_CONTAINER))
            return;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        for(NamespacedKey key : ItemHandler.getPDC("potion", item, PersistentDataType.TAG_CONTAINER).getKeys()){
            PersistentDataContainer c = ItemHandler.getPDC("potion", item, PersistentDataType.TAG_CONTAINER).get(key, PersistentDataType.TAG_CONTAINER);
            PotionEffect effect = PotionEffect.getPotionEffects().get(key.getKey());
            System.out.println(c.get(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER));
            effect.applyEffect(player, c.get(new NamespacedKey(Main.getMain(), "level"), PersistentDataType.INTEGER), c.get(new NamespacedKey(Main.getMain(), "duration"), PersistentDataType.LONG));
        }
    }
}
