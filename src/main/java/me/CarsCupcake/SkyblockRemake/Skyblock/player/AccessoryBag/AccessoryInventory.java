package me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.MultipleGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccessoryInventory {
    public ArrayList<Inventory> invs = new ArrayList<>();
    public static HashMap<Player, AccessoryInventory> activeInvs = new HashMap<>();
    private final SkyblockPlayer player;

    public AccessoryInventory(SkyblockPlayer player) {
        this.player = player;
        int slots = player.getMaxAccessoryBagSlots();
        int invs = 1 + (slots / 45);

        int list = 0;
        int invCount;
        for (invCount = 1; invCount <= invs; invCount++) {
            Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Accessory Bag (" + invCount + "/" + invs + ")");
            for (int i = 0; i < 45; i++) {
                if (list < slots && player.getAccessoryBag().size() > list) {
                    ItemStack it = player.getAccessoryBag().get(list);
                    inv.setItem(i, Main.itemUpdater(it, player));
                } else {
                    if (list >= slots) {
                        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                        ItemMeta meta = item.getItemMeta();
                        PersistentDataContainer data = meta.getPersistentDataContainer();
                        data.set(new NamespacedKey(Main.getMain(), "border"), ItemHandler.BOOLEAN, true);
                        meta.setDisplayName(" ");
                        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        item.setItemMeta(meta);
                        inv.setItem(i, item);
                    }
                }
                list++;


            }
            this.invs.add(inv);
        }
        activeInvs.put(player, this);
    }

    public void open() {
        MultipleGui gui = new MultipleGui(invs, 45, 53);
        gui.setGeneralAction((slot, actionType, type) -> {
            if (actionType == GUI.GUIActions.Click) ItemHandler.getOrDefaultPDC("border", gui.getInventory().getItem(slot), ItemHandler.BOOLEAN, false);
            return actionType == GUI.GUIActions.PlayerClick && ItemHandler.getItemManager(player.getInventory().getItem(slot)).type != ItemType.Accessory;
        });
        gui.closeAction(type -> save(gui.getInventories()));
        gui.showGUI(player);
    }
    private void save(List<Inventory> invs) {
        List<ItemStack> newItems = new ArrayList<>();
        for (Inventory inv : invs) {
            for (ItemStack item : inv) {
                if (item == null || item.getType() == Material.AIR) continue;
                if (ItemHandler.getOrDefaultPDC("border", item, ItemHandler.BOOLEAN, false)) continue;
                newItems.add(item);
            }
        }
        player.swapAccessoryBag(newItems);
    }
}
