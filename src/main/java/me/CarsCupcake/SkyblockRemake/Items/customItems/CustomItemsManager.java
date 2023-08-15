package me.CarsCupcake.SkyblockRemake.Items.customItems;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUIAction;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.TemplateItems;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignGUI;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CustomItemsManager {
    private final String id;
    @Getter
    private final HashMap<Stats, Double> stats = new HashMap<>();
    @Setter
    private String name;
    private final Material material;
    @Setter
    private ItemType itemType = ItemType.Non;
    @Setter
    private ItemRarity rarity = ItemRarity.COMMON;

    public CustomItemsManager(String id, Material material) {
        this.id = id;
        this.material = material;
    }

    public ItemManager compile() {
        if (name == null) return null;
        ItemManager manager = new ItemManager(name, id, itemType, material, rarity);
        for (Stats s : stats.keySet())
            manager.setStat(s, stats.get(s));
        return manager;
    }

    public ItemStack craftPreview(boolean rift) {
        ItemBuilder item = new ItemBuilder(material);
        item.setName(rarity.getPrefix() + name);
        for (Stats s : ((rift) ? Stats.riftStatItemDisplayOrder : Stats.statItemDisplayOrder)) {
            if (stats.containsKey(s) && stats.get(s) != 0) {
                double val = stats.get(s);
                item.addLoreRow("§7" + s.getName() + ": " + ((s.isAgressive()) ? "§c" : "§a") + ((val > 0) ? "+" : "-") + val);
            }
        }
        return item.addLoreRow(" ").addLoreRow(rarity.getPrefix() + rarity.getRarityName() + " " + itemType).build();
    }

    public static void loadAll() {

    }

    public static void edit(String id, SkyblockPlayer player) {
        if (Items.SkyblockItems.containsKey(id)) return;
        if (player.getEquipment().getItemInMainHand() == null || player.getEquipment().getItemInMainHand().getType() == Material.AIR) {
            player.sendMessage("§cNot a valid item!");
            return;
        }
        CustomItemsManager manager = new CustomItemsManager(id, player.getEquipment().getItemInMainHand().getType());
        GUI gui = new GUI(new InventoryBuilder(6, "Custom Item")
                .fill(TemplateItems.EmptySlot.getItem())
                .setItem(new ItemBuilder(Material.STONE_BUTTON).setName("§aToggle Rift").addLoreRow("§aCurrent display mode: Normal").build(), 4)
                .setItem(manager.craftPreview(false), 13)
                .setItem(new ItemBuilder(Material.BIRCH_SIGN).setName("§aSet Name").build(), 29)
                .setItem(new ItemBuilder(Material.STONE_AXE).setName("§aSet Type").addLoreRow("§7Currently: " + manager.itemType.name()).build(), 31)
                .setItem(new ItemBuilder(Material.GOLDEN_APPLE).setName("§aSet Stat").build(), 33)
                .setItem(new ItemBuilder(Material.NAME_TAG).setName("§aRarity").build(), 40)
                .setItem(new ItemBuilder(Material.GREEN_CONCRETE).setName("§aConfirm").build(), 49)
                .build());
        gui.setCanceled(true);
        var action = new GUIAction() {
            private boolean b = false;

            @Override
            public void run(ClickType type) {
                b = !b;
                gui.getInventory().setItem(4, new ItemBuilder(Material.STONE_BUTTON).setName("§aToggle Rift").addLoreRow("§aCurrent display mode: " + ((b) ? "Rift" : "Normal")).build());
                gui.getInventory().setItem(13, manager.craftPreview(b));
            }
        };
        gui.inventoryClickAction(4, action);
        gui.inventoryClickAction(29, type -> new SignGUI(new SignManager(), event -> {
            manager.setName(event.lines()[0]);
            gui.getInventory().setItem(13, manager.craftPreview(action.b));
            gui.showGUI(player);
        }).withLines("", "", "^^^^^^^^^^", "Enter the Name").open(player));
        gui.inventoryClickAction(31, type -> {
            InventoryBuilder builder = new InventoryBuilder(6, "Select Item Type");
            int i = 0;
            ItemType[] types = ItemType.values();
            for (ItemType t : types) {
                builder.setItem(i, new ItemBuilder(Material.STONE_AXE).setName("§a" + t.name()).setGlint(t == manager.itemType).build());
                i++;
            }
            GUI otherInv = new GUI(builder.build());
            otherInv.setGeneralAction((slot, actionType, type1) -> {
                if (slot < types.length) {
                    manager.itemType = types[slot];
                    gui.getInventory().setItem(13, manager.craftPreview(action.b));
                    gui.showGUI(player);
                }
                return true;
            });
            otherInv.setCanceled(true);
            otherInv.showGUI(player);
        });
        gui.inventoryClickAction(33, type -> {
            Stats[] stats = Stats.values();
            InventoryBuilder builder = new InventoryBuilder(6, "Select a stat to modify");
            int i = 0;
            for (Stats s : stats) {
                builder.setItem(i, s.itemPreviewBuilder().setName(s.getColor().toString() + s.getSymbol() + s.getName()).build());
                i++;
            }
            GUI gui1 = new GUI(builder.build());
            gui1.setCanceled(true);
            gui1.setGeneralAction((slot, actionType, type1) -> {
                if (slot < stats.length) {
                    new SignGUI(new SignManager(), event -> {
                        double d;
                        try {
                            d = Double.parseDouble(event.lines()[0]);
                        } catch (Exception e) {
                            e.printStackTrace(System.out);
                            player.sendMessage("§cIllegal number!");
                            gui1.showGUI(player);
                            return;
                        }
                        gui.showGUI(player);
                        manager.stats.put(stats[slot], d);
                        gui.getInventory().setItem(13, manager.craftPreview(action.b));
                        player.sendMessage("§aSet " + stats[slot].getName() + " to " + d);
                    }).withLines("", "", "^^^^^^^^", "Enter the value").open(player);
                }
                return true;
            });
            gui1.showGUI(player);
        });
        gui.inventoryClickAction(40, type -> {
            InventoryBuilder builder = new InventoryBuilder(3, "Select a rarity");
            ItemRarity[] raritys = ItemRarity.values();
            int i = 0;
            for (ItemRarity r : raritys) {
                builder.setItem(i, new ItemBuilder(Material.NAME_TAG).setName(r.getPrefix() + r.getRarityName()).build());
                i++;
            }
            GUI guis = new GUI(builder.build());
            guis.setCanceled(true);
            guis.setGeneralAction((slot, actionType, type1) -> {
                if(slot < raritys.length) {
                    manager.rarity = raritys[slot];
                    gui.getInventory().setItem(13, manager.craftPreview(action.b));
                    gui.showGUI(player);
                }
                return true;
            });
            guis.showGUI(player);
        });
        gui.inventoryClickAction(49, type -> {
            gui.closeInventory();
            player.addItem(manager.compile().createNewItemStack(), false);
        });
        gui.showGUI(player);

    }
}
