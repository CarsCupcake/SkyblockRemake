package me.CarsCupcake.SkyblockRemake.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignGUI;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.CarsCupcake.SkyblockRemake.Skyblock.SearchTopic;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.cmd.itemCMD;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class ItemsSearch implements Listener {
    private static final HashMap<SkyblockPlayer, ArrayList<Inventory>> inventorys = new HashMap<>();

    public enum Filter implements Predicate<ItemManager> {
        Swords(new ItemType[]{ItemType.Sword, ItemType.Longsword}),
        Bows(new ItemType[]{ItemType.Bow}),
        Wands(new ItemType[]{ItemType.Wand}),
        Tools(new ItemType[]{ItemType.Axe, ItemType.Pickaxe, ItemType.Drill, ItemType.Gauntlet, ItemType.Deployable, ItemType.Hoe}),
        Armor(new ItemType[]{ItemType.Helmet, ItemType.Chestplate, ItemType.Leggings, ItemType.Boots}),
        Accessories(new ItemType[]{ItemType.Accessory}),
        Equipment(new ItemType[]{ItemType.Bracelet, ItemType.Belt, ItemType.Cloak, ItemType.Gloves, ItemType.Necklace}),
        Minion(new ItemType[]{ItemType.Minion}),
        Other();
        static final List<Filter> filters = List.of(Swords, Bows, Wands, Tools, Armor, Accessories, Equipment, Other);
        private final List<ItemType> itemTypes;

        Filter(ItemType[] itemTypes) {
            this.itemTypes = List.of(itemTypes);
        }

        Filter() {
            itemTypes = null;
        }

        public List<ItemType> getItemTypes() {
            if (itemTypes != null) return itemTypes;
            List<ItemType> t = new ArrayList<>();
            for (ItemType type : ItemType.values()) {
                boolean b = false;
                for (Filter f : Filter.values())
                    if (f.itemTypes != null && f.itemTypes.contains(type)) {
                        b = true;
                        break;
                    }
                if (!b) t.add(type);
            }
            return t;
        }

        @Override
        public boolean test(@NotNull ItemManager o) {
            return getItemTypes().contains(o.type);
        }
    }

    @EventHandler
    public void getSearchInput(PlayerChatEvent event) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        if (player.isSearching() && player.getSearching() == SearchTopic.CustomItems) {
            buildInventory(player, event.getMessage());
            event.setCancelled(true);
        }
    }

    public synchronized static void buildInventory(SkyblockPlayer player, String input) {
        buildInventory(player, input, null);
    }

    public synchronized static void buildInventory(SkyblockPlayer player, String input, Filter filter) {
        player.setSearching(null);
        ArrayList<ItemManager> managers = new ArrayList<>();
        for (ItemManager manager : itemCMD.customItems) {
            if (filter != null && !filter.test(manager)) continue;
            StringBuilder name = new StringBuilder();
            if (manager.name.contains("§"))
                for (String split : manager.name.split("§")) {
                    if (split.isEmpty()) continue;
                    name.append(split.substring(1));
                }
            else
                name.append(manager.name);
            if (input.isEmpty()) managers.add(manager);
            else if (name.toString().toLowerCase().contains(input.toLowerCase()))
                managers.add(manager);
        }
        ArrayList<Inventory> items = new ArrayList<>();
        int invs = 1 + (managers.size() / 45);
        int list = 0;
        for (int invCount = 1; invCount <= invs; invCount++) {
            Inventory inv = Bukkit.createInventory(null, 54, "Custom Items" + ((input.isEmpty()) ? " Menu" : (" Search: " + input)) + " - Page " + invCount);
            ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer data = meta.getPersistentDataContainer();
            meta.setDisplayName(" ");
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
            for (int i = 45; i < 53; i++) {
                inv.setItem(i, item);
            }
            if (invCount != invs) {
                item.setType(Material.ARROW);
                data.set(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER, invCount);
                meta.setDisplayName("§aNext Page");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            } else {
                data.set(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER, invCount);
            }
            item.setItemMeta(meta);
            inv.setItem(53, item);
            if (invCount != 1) {
                item.setType(Material.ARROW);
                meta.setDisplayName("§aLast Page");
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                item.setItemMeta(meta);
                inv.setItem(45, item);
            }
            item.setType(Material.OAK_SIGN);
            meta.setDisplayName("§aSearch");
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "search"), PersistentDataType.STRING, input);
            item.setItemMeta(meta);
            inv.setItem(49, item);
            item = new ItemBuilder(Material.HOPPER)
                    .setName("§aFilter")
                    .setGlint(filter != null)
                    .addAllLore(genFilterLore(filter))
                    .addLoreRow("§eClick to change!")
                    .build();
            if (filter != null)
                ItemHandler.setPDC("filter", item, PersistentDataType.STRING, filter.name().toLowerCase());
            inv.setItem(52, item);
            if (filter != null)
                inv.setItem(51, new ItemBuilder(Material.ANVIL).setName("§aReset Filter").build());

            int i = 0;
            while (i < 45) {

                if (!(list >= managers.size())) {
                    if (!managers.get(list).hasEdition)
                        inv.setItem(i, Main.item_updater(managers.get(list).getRawItemStack(), null));
                    else
                        i -= 1;
                }
                i++;


                list++;
            }

            items.add(inv);

        }
        inventorys.put(player, items);
        player.openInventory(items.get(0));

    }

    private static List<String> genFilterLore(Filter f) {
        List<String> lore = new ArrayList<>();
        lore.add(((f == null) ? "§a" : "§7") + "‣ No Filter");
        for (Filter filter : Filter.filters)
            lore.add(((filter != null && filter == f) ? "§a" : "§7") + "‣ " + filter.name());
        return lore;
    }


    @EventHandler
    public void ItemsMenu(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains("Custom Items"))
            return;
        if (event.getCurrentItem() == null)
            return;
        if (event.getCurrentItem().getItemMeta() == null)
            return;

        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked());
        event.setCancelled(true);

        if (event.getClickedInventory().getType() == InventoryType.PLAYER)
            return;


        int page = event.getView().getTopInventory().getItem(53).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER);

        if (event.getSlot() == 53) {
            if (page < inventorys.get(player).size()) {
                player.openInventory(inventorys.get(player).get(page));
                player.updateInventory();
            }
            return;
        }
        if (event.getSlot() == 52) {
            String search = ItemHandler.getOrDefaultPDC("search", event.getView().getTopInventory().getItem(49), PersistentDataType.STRING, "");
            ItemStack item = event.getView().getTopInventory().getItem(52);
            int index = (!ItemHandler.hasPDC("filter", item, PersistentDataType.STRING)) ? -1 :
                    Filter.filters.indexOf(Filter.valueOf(ItemHandler.getPDC("filter", item, PersistentDataType.STRING).substring(0, 1).toUpperCase() + ItemHandler.getPDC("filter", item, PersistentDataType.STRING).substring(1)));
            if (event.isRightClick()) {
                if (index == -1) {
                    buildInventory(player, search, Filter.filters.get(Filter.filters.size() - 1));
                    return;
                }
                if (index == 0) {
                    buildInventory(player, search);
                    return;
                }
                buildInventory(player, search, Filter.filters.get(index - 1));
            } else {
                index++;
                if (index == Filter.filters.size())
                    buildInventory(player, search);
                else
                    buildInventory(player, search, Filter.filters.get(index));
                return;
            }
        }
        if (event.getSlot() == 51)
            buildInventory(player, ItemHandler.getOrDefaultPDC("search", event.getView().getTopInventory().getItem(49), PersistentDataType.STRING, ""));

        if (event.getSlot() == 49) {
            new SignGUI(new SignManager(), e -> Bukkit.getScheduler().runTask(Main.getMain(), () -> ItemsSearch.buildInventory(SkyblockPlayer.getSkyblockPlayer(player), e.lines()[0] + e.lines()[1])))
                    .withLines("", "", "^^^^^^^^^^^^^^^", "Enter Item Name")
                    .open(player);
        }
        if (event.getSlot() == 45) {

            if (page != 1) {

                player.openInventory(inventorys.get(player).get(page - 2));
                player.updateInventory();
            }
            return;
        }
        if (event.getSlot() > 45) {
            return;
        }
        if (event.getCurrentItem().getType() == Material.AIR)
            return;
        player.getInventory().addItem(Main.item_updater(Items.SkyblockItems.get(ItemHandler.getPDC("id", event.getCurrentItem(), PersistentDataType.STRING)).createNewItemStack(), player));
        player.updateInventory();

    }

}
