package me.CarsCupcake.SkyblockRemake.Skyblock.hex;

import de.carscupcake.gameoflegends.utils.Inventorys.MultipleGui;
import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.Factory;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.TemplateItems;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignGUI;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public enum HexOption implements Factory<Hex, GUI> {
    Enchantment(new ItemBuilder(Material.ENCHANTING_TABLE).setName("§a§lEnchantments").build()) {

        @Override
        public GUI factor(Hex obj) {
            List<List<CustomEnchantment>> enchantmentPages = new ArrayList<>();
            List<CustomEnchantment> eL = new ArrayList<>();
            for (CustomEnchantment e : obj.getAllwedEnchantments()) {
                if (e == SkyblockEnchants.ENCHANT_GLINT) continue;
                if (e instanceof UltimateEnchant) continue;
                eL.add(e);
                if (eL.size() == 15) {
                    enchantmentPages.add(eL);
                    eL = new ArrayList<>();
                }
            }
            if (!eL.isEmpty())
                enchantmentPages.add(eL);
            List<Inventory> invs = new ArrayList<>();
            for (List<CustomEnchantment> l : enchantmentPages) {
                Inventory inv = HexOption.craftSideView(obj, "Enchant Item").build();
                paintEnchantments(l, inv, obj);
                invs.add(inv);
            }
            return getMultipleGui(obj, invs, enchantmentPages);
        }

        @NotNull
        private MultipleGui getMultipleGui(Hex obj, List<Inventory> invs, List<List<CustomEnchantment>> enchantmentPages) {
            MultipleGui gui = new MultipleGui(invs, 35, 17);
            gui.setGeneralAction((slot, actionType, type) -> {
                for (int i = 0; i < slotList.length; i++) {
                    if (slot == slotList[i]) {
                        if (enchantmentPages.get(gui.getCurrentPage()).size() <= i)
                            return true;
                        enchantmentMenu(obj, enchantmentPages.get(gui.getCurrentPage()).get(i));
                    }
                }
                if (slot == 19) {
                    obj.getPlayer().addItem(obj.getItem(), false);
                    obj.getGui().getInventory().setItem(22, null);
                    obj.update();
                    obj.getGui().showGUI(obj.getPlayer());
                }
                if (slot == 45) {
                    obj.getGui().getInventory().setItem(22, gui.getInventory().getItem(19));
                    obj.update();
                    obj.getGui().showGUI(obj.getPlayer());
                }
                return true;
            });
            return gui;
        }

        public void enchantmentMenu(Hex hex, CustomEnchantment enchantment) {
            int[] slots = getFormation(enchantment.getMaxLevel());
            InventoryBuilder builder = HexOption.craftSideView(hex, "Enchantments");
            builder.setItem(new ItemBuilder(Material.OAK_SIGN).setName("§aSet Custom level").build(), 26);
            for (int i = 1; i <= enchantment.getMaxLevel(); i++) {
                ItemBuilder b = new ItemBuilder(Material.ENCHANTED_BOOK).addEnchant(enchantment, i).setName("§9" + enchantment.getName() + " " + Tools.intToRoman(i)).addItemFlag(ItemFlag.HIDE_ENCHANTS);
                b.addAllLore( enchantment.getLore().makeLore(hex.getPlayer(), b.build()));
                builder.setItem(b.build(), slots[i - 1]);
            }
            GUI gui = new GUI(builder.build());
            gui.setGeneralAction((slot, actionType, type) -> {
                if (slot == 45) {
                    hex.getGui().getInventory().setItem(22, gui.getInventory().getItem(19));
                    hex.update();
                    HexOption.Enchantment.factor(hex).showGUI(hex.getPlayer());
                    return true;
                }
                if (slot == 26) {
                    new SignGUI(new SignManager(), event -> {
                        int level;
                        try {
                            level = Integer.parseInt(event.lines()[0]);
                            Assert.state(level > 0);
                            ItemStack item = hex.getItem();
                            enchant(item, hex, enchantment, level);
                            Main.itemUpdater(item, hex.getPlayer());
                            hex.getGui().getInventory().setItem(22, item);
                            hex.update();
                            HexOption.Enchantment.factor(hex).showGUI(hex.getPlayer());
                        }catch (Exception e) {
                            hex.getPlayer().sendMessage("§cNot a legal number!");
                            gui.showGUI(hex.getPlayer());
                        }
                    }).withLines("", "^^^^^^^^", "Enter Enchantment", "level").open(hex.getPlayer());
                    return true;
                }
                for (int i = 0; i < enchantment.getMaxLevel(); i++) {
                    if (slot == slots[i]) {
                        ItemStack item = hex.getItem();
                        enchant(item, hex, enchantment, i + 1);
                        Main.itemUpdater(item, hex.getPlayer());
                        hex.getGui().getInventory().setItem(22, item);
                        hex.update();
                        HexOption.Enchantment.factor(hex).showGUI(hex.getPlayer());
                        break;
                    }
                }
                return true;
            });
            gui.showGUI(hex.getPlayer());
        }
        private void enchant(ItemStack item, Hex hex, CustomEnchantment enchantment, int level) {
            for (CustomEnchantment e : hex.getAllwedEnchantments()) {
                if (e != enchantment && e.conflictsWith(enchantment) && ItemHandler.hasEnchantment(e, item)) {
                    ItemHandler.removeEnchant(e, item);
                }
            }
            ItemHandler.setEnchant(enchantment, level, item);
        }
        private int[] getFormation(int maxLevel) {
            return switch (maxLevel) {
                case 1 -> new int[]{23};
                case 2 -> new int[]{22, 24};
                case 3 -> new int[]{22, 23, 24};
                case 4 -> new int[]{22, 24, 31, 33};
                case 5 -> new int[]{21, 22, 23, 24, 25};
                case 6 -> new int[]{22, 23, 24, 31, 32, 33};
                case 7 -> new int[]{13, 14, 15, 22, 23, 24, 32};
                case 10 -> new int[]{12, 13, 14, 15, 16, 21, 22, 23, 24, 25};
                default -> throw new UnsupportedOperationException("Illegal number");
            };
        }
        @SuppressWarnings("deprecation")
        private void paintEnchantments(List<CustomEnchantment> enchantments, Inventory inventory, Hex hex) {
            int i = 0;
            for (CustomEnchantment enchantment : enchantments) {
                boolean hasConflict = false;
                for (CustomEnchantment e : hex.getAllwedEnchantments())
                    if (e != enchantment && e.conflictsWith(enchantment)) hasConflict = true;
                ItemBuilder builder = new ItemBuilder(Material.ENCHANTED_BOOK).setName("§a" + enchantment.getName())
                        .addAllLore(enchantment.getLore().makeLore(hex.getPlayer(), new ItemBuilder(Material.STICK).addEnchant(enchantment, 1).build()))
                        .addAllLore(" ", ((ItemHandler.hasEnchantment(enchantment, hex.getItem())) ? "§a" : "§c") + enchantment.getName() + " " + ((ItemHandler.hasEnchantment(enchantment, hex.getItem())) ?
                                        (ItemHandler.getEnchantmentLevel(enchantment, hex.getItem()) +  "  ✔" )
                                        : " ✖"));
                if (hasConflict)
                    builder.addAllLore(" ", "§c§lTHIS CONFLICTS WITH ALREADY SET ENCHANTMENTS!");
                inventory.setItem(slotList[i], builder.build());
                i++;
            }
        }
    },

    UltimateEnchantment(new ItemBuilder(Material.ENCHANTED_BOOK).setName("§a§lUltimate Enchantments").build())  {
        @Override
        public GUI factor(Hex obj) {
            return null;
        }
    };


    @Getter
    private final ItemStack display;
    HexOption(@NotNull ItemStack display) {
        this.display = display;
    }
    protected final int[] slotList = {12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 30, 31, 32, 33, 34};
    private static InventoryBuilder craftSideView(Hex hex, String name) {
        return new InventoryBuilder(6, "The Hex ➜ " + name)
                .fill(TemplateItems.EmptySlot.getItem())
                .setItem(19, hex.getItem())
                .setItem(28, new ItemBuilder(Material.ENCHANTING_TABLE).setName("§aEnchant Item").addAllLore("§7Add and remove enchantments from", "§7the item in the slot above!").build())
                .setItem(45, TemplateItems.BackArrow.getItem());
    }
}
