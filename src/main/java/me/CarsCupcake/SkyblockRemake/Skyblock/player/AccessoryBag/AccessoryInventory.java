package me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag;

import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.MultipleGui;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.TemplateItems;
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
    private int lastPageSize = 54;

    public AccessoryInventory(SkyblockPlayer player) {
        this.player = player;
        int slots = player.getMaxAccessoryBagSlots();
        int invs = 1 + (slots / 45);

        int list = 0;
        int invCount;
        for (invCount = 1; invCount <= invs; invCount++) {
            int invSlots = (slots - list > 45) ? 54 :  (9 * (1 + getNextHigher((slots - list) / 9d)));
            if (invSlots != 54) lastPageSize = invSlots;
            Inventory inv = Bukkit.createInventory(null, invSlots, ChatColor.DARK_GRAY + "Accessory Bag (" + invCount + "/" + invs + ")");
            ItemStack empty = TemplateItems.EmptySlot.getItem();
            ItemHandler.setPDC("border", empty, ItemHandler.BOOLEAN, true);
            for (int i = invSlots - 9; i < invSlots; i++) {
                inv.setItem(i, empty);
            }
            for (int i = 0; i < invSlots - 9; i++) {
                ItemStack it = player.getAccessoryBag().get(list);
                if (it != null) {
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

    private int getNextHigher(double d) {
        int toInt = (int) d;
        if (toInt < d) return toInt + 1;
        return toInt;
    }

    public void open() {
        MultipleGui gui = new MultipleGui(invs, 53, 45);
        gui.addSpecialSlotSwap(invs.size() - 1, lastPageSize - 1, lastPageSize - 9);
        gui.setGeneralAction((slot, actionType, type) -> {
            ItemStack item = gui.getInventory().getItem(slot);
            if (item == null || item.getType() == Material.AIR) return false;
            if (actionType == GUI.GUIActions.Click) {
                if ((gui.getCurrentPage() == gui.getInventories().size() - 1 && (lastPageSize - 9 == 45 || lastPageSize - 1 == 53)) || (slot == 45 || slot == 53)) return true;
                return ItemHandler.getOrDefaultPDC("border", item, ItemHandler.BOOLEAN, false);
            }
            ItemManager base = ItemHandler.getItemManager(player.getInventory().getItem(slot));
            if (base == null) return true;
            return actionType == GUI.GUIActions.PlayerClick && base.type != ItemType.Accessory;
        });
        gui.closeAction(type -> save(gui.getInventories()));
        gui.showGUI(player);
    }
    private void save(List<Inventory> invs) {
        HashMap<Integer, ItemStack> newItems = new HashMap<>();
        int slot = -1;
        for (Inventory inv : invs) {
            for (int i = 0; i < inv.getSize() - 9; i++) {
                slot++;
                if (slot == player.getMaxAccessoryBagSlots()) break;
                ItemStack item = inv.getItem(i);
                if (item == null || item.getType() == Material.AIR) continue;
                ItemManager base = ItemHandler.getItemManager(item);
                if (base == null || base.type != ItemType.Accessory) continue;
                newItems.put(slot, item);
            }
        }
        player.swapAccessoryBag(newItems);
    }
}
