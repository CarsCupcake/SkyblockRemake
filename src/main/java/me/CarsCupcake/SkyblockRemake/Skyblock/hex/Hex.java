package me.CarsCupcake.SkyblockRemake.Skyblock.hex;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.TemplateItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public final class Hex {
    private static final Set<ItemType> hexable = new HashSet<>(ItemType.Type.getAvaidable());
    private final static int[] slots = {12, 13, 14, 21, 23, 30, 31, 32};
    private final static int[] upgradeSlots = {15, 16, 24, 25, 33, 34, 17, 26, 35};
    private GUI gui;
    @Setter
    private ItemStack item = null;
    private final SkyblockPlayer player;
    private final List<CustomEnchantment> allwedEnchantments = new ArrayList<>();
    private final List<HexOption> avaidableOptions = new ArrayList<>();
    @Setter
    private boolean returnOnClose = true;

    public Hex(SkyblockPlayer player) {
        this.player = player;
    }

    public void openLandingPage() {
        gui = new GUI(new InventoryBuilder(6, "The Hex").fill(TemplateItems.EmptySlot.getItem()).setItem(22, new ItemStack(Material.AIR)).setItems(getHexSurrounding(), slots).build());
        gui.setGeneralAction((slot, actionType, type) -> {
            if (actionType == GUI.GUIActions.PlayerClick && !type.isShiftClick()) return false;
            if (actionType == GUI.GUIActions.Close) {
                player.addItem(item, false);
                return false;
            }
            if (slot == 22 && actionType == GUI.GUIActions.Click) {
                Bukkit.getScheduler().runTaskLater(Main.getMain(), this::update, 1);
                return false;
            }
            int i = 0;
            for (int l : upgradeSlots) {
                if (l == slot && avaidableOptions.size() > i) {
                    GUI gui = avaidableOptions.get(i).factor(Hex.this);
                    gui.setCanceled(true);
                    gui.closeAction(type1 -> {
                        if (player.getGui() == null) getPlayer().addItem(Main.itemUpdater(item, player), false);
                        else returnOnClose = true;
                    });
                    gui.inventoryClickAction(19, type1 -> openLandingPage());
                    gui.showGUI(player);
                    break;
                }
                i++;
            }
            return true;
        });
        gui.showGUI(player);
    }

    public void update() {
        item = gui.getInventory().getItem(22);
        if (item != null && !item.hasItemMeta()) item = null;
        avaidableOptions.clear();
        if (item == null || item.getType() == Material.AIR) {
            for (int i : upgradeSlots)
                gui.getInventory().setItem(i, TemplateItems.EmptySlot.getItem());
            for (int i : slots)
                gui.getInventory().setItem(i, getHexSurrounding());
            return;
        }
        if (!hexable.contains(ItemHandler.getItemManager(item).type)) {
            for (int i : upgradeSlots)
                gui.getInventory().setItem(i, TemplateItems.EmptySlot.getItem());
            for (int i : slots)
                gui.getInventory().setItem(i, new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§cThe Hex is displeased").addLoreRow("§7You cannot modify this item").build());
            return;
        }
        boolean hasUlt = false;
        boolean hasNormal = false;
        for (int i : slots)
            gui.getInventory().setItem(i, getHexSurrounding());
        allwedEnchantments.clear();
        ItemManager manager = ItemHandler.getItemManager(item);
        for (CustomEnchantment enchants : SkyblockEnchants.registeredEnchants.values()) {
            for (ItemType type : enchants.getAllowedTypes()) {
                if (manager.type == type) {
                    allwedEnchantments.add(enchants);
                    break;
                }
            }
            if (!hasUlt) {
                if (enchants instanceof UltimateEnchant) hasUlt = true;
            }
            if (!hasNormal) {
                if (!(enchants instanceof UltimateEnchant)) hasNormal = true;
            }
        }
        if (hasNormal) {
            avaidableOptions.add(HexOption.Enchantment);
        }
        if (hasUlt) {
            avaidableOptions.add(HexOption.UltimateEnchantment);
        }
        if (manager.isCanHaveHotPotatoBooks()) {
            avaidableOptions.add(HexOption.Books);
        }
        int i = 0;
        for (HexOption opt : avaidableOptions) {
            gui.getInventory().setItem(upgradeSlots[i], opt.getDisplay());
            i++;
        }
    }

    public ItemStack getHexSurrounding() {
        if (item == null || item.getType() == Material.AIR) {
            return new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setName("§d§kGive your hope to The Hex!").addAllLore("§7Upgrade an item with a variety", "§7of bells and whistles, all in", "§7one place!", "", "§7§8The Hex - Your one-stop shop", "§8for personal refinement!", "§d§kGive your hope to The Hex!").build();
        }
        //TODO: add details
        return new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setName("§d§kGive your hope to The Hex!").addAllLore("§7Upgrade an item with a variety", "§7of bells and whistles, all in", "§7one place!", "", "§7§8The Hex - Your one-stop shop", "§8for personal refinement!", "§d§kGive your hope to The Hex!").build();

    }
}
