package me.CarsCupcake.SkyblockRemake.Skyblock.hex;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.CustomEnchantment;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.Factory;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.MultipleGui;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.TemplateItems;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignGUI;
import me.CarsCupcake.SkyblockRemake.utils.SignGUI.SignManager;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
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
                Inventory inv = HexOption.craftSideView(obj, "Enchant Item", this).build();
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
                    if (actionType == GUI.GUIActions.Close) {
                        return false;
                    }
                    if (slot == slotList[i]) {
                        if (enchantmentPages.get(gui.getCurrentPage()).size() <= i)
                            return true;
                        obj.setReturnOnClose(false);
                        enchantmentMenu(obj, enchantmentPages.get(gui.getCurrentPage()).get(i));
                    }
                }
                if (slot == 19) {
                    obj.setReturnOnClose(false);
                    obj.getPlayer().addItem(obj.getItem(), false);
                    obj.getGui().getInventory().setItem(22, null);
                    obj.update();
                    obj.getGui().showGUI(obj.getPlayer());
                }
                if (slot == 45) {
                    obj.setReturnOnClose(false);
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
            InventoryBuilder builder = HexOption.craftSideView(hex, "Enchantments", this);
            builder.setItem(new ItemBuilder(Material.OAK_SIGN).setName("§aSet Custom level").build(), 26);
            for (int i = 1; i <= enchantment.getMaxLevel(); i++) {
                ItemBuilder b = new ItemBuilder(Material.ENCHANTED_BOOK).addEnchant(enchantment, i)
                        .setName("§9" + enchantment.getName() + " " + Tools.intToRoman(i)).addItemFlag(ItemFlag.HIDE_ENCHANTS);
                b.addAllLore( enchantment.getLore().makeLore(hex.getPlayer(), b.build()));
                builder.setItem(b.build(), slots[i - 1]);
            }
            GUI gui = new GUI(builder.build());
            gui.setGeneralAction((slot, actionType, type) -> {
                if (actionType == GUI.GUIActions.Close) {
                    return false;
                }
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
                                        (ItemHandler.getEnchantmentLevel(enchantment, hex.getItem()) +  "✔" )
                                        : "✖"));
                if (hasConflict)
                    builder.addAllLore(" ", "§c§lTHIS CONFLICTS WITH ALREADY SET ENCHANTMENTS!");
                inventory.setItem(slotList[i], builder.build());
                i++;
            }
        }
    },

    UltimateEnchantment(new ItemBuilder(Material.ENCHANTED_BOOK).setName("§a§lUltimate Enchantments").build()) {

        @Override
        public GUI factor(Hex obj) {
            List<List<CustomEnchantment>> enchantmentPages = new ArrayList<>();
            List<CustomEnchantment> eL = new ArrayList<>();
            for (CustomEnchantment e : obj.getAllwedEnchantments()) {
                if (e == SkyblockEnchants.ENCHANT_GLINT) continue;
                if (!(e instanceof UltimateEnchant)) continue;
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
                Inventory inv = HexOption.craftSideView(obj, "Enchant Item", this).build();
                paintEnchantments(l, inv, obj);
                invs.add(inv);
            }
            return getMultipleGui(obj, invs, enchantmentPages);
        }

        @NotNull
        private MultipleGui getMultipleGui(Hex obj, List<Inventory> invs, List<List<CustomEnchantment>> enchantmentPages) {
            MultipleGui gui = new MultipleGui(invs, 35, 17);
            gui.setGeneralAction((slot, actionType, type) -> {
                if (actionType == GUI.GUIActions.Close) {
                    return false;
                }
                for (int i = 0; i < slotList.length; i++) {
                    if (slot == slotList[i]) {
                        if (enchantmentPages.get(gui.getCurrentPage()).size() <= i)
                            return true;
                        obj.setReturnOnClose(false);
                        enchantmentMenu(obj, enchantmentPages.get(gui.getCurrentPage()).get(i));
                    }
                }
                if (slot == 19) {
                    obj.setReturnOnClose(false);
                    obj.getPlayer().addItem(obj.getItem(), false);
                    obj.getGui().getInventory().setItem(22, null);
                    obj.update();
                    obj.getGui().showGUI(obj.getPlayer());
                }
                if (slot == 45) {
                    obj.setReturnOnClose(false);
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
            InventoryBuilder builder = HexOption.craftSideView(hex, "Ultimate Enchantments", this);
            builder.setItem(new ItemBuilder(Material.OAK_SIGN).setName("§aSet Custom level").build(), 26);
            for (int i = 1; i <= enchantment.getMaxLevel(); i++) {
                ItemBuilder b = new ItemBuilder(Material.ENCHANTED_BOOK).addEnchant(enchantment, i)
                        .setName("§a§l" + enchantment.getName() + " " + Tools.intToRoman(i)).addItemFlag(ItemFlag.HIDE_ENCHANTS);
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
                        HexOption.UltimateEnchantment.factor(hex).showGUI(hex.getPlayer());
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
                for (Bundle<CustomEnchantment, Integer> e : ItemHandler.getEnchantments(hex.getItem()))
                    if (e.getFirst() != enchantment && e.getFirst().conflictsWith(enchantment)) hasConflict = true;
                ItemBuilder builder = new ItemBuilder(Material.ENCHANTED_BOOK).setName("§a" + enchantment.getName())
                        .addAllLore(enchantment.getLore().makeLore(hex.getPlayer(), new ItemBuilder(Material.STICK).addEnchant(enchantment, 1).build()))
                        .addAllLore(" ", ((ItemHandler.hasEnchantment(enchantment, hex.getItem())) ? "§a" : "§c") + enchantment.getName() + " " + ((ItemHandler.hasEnchantment(enchantment, hex.getItem())) ?
                                (ItemHandler.getEnchantmentLevel(enchantment, hex.getItem()) +  " ✔" )
                                : "✖"));
                if (hasConflict)
                    builder.addAllLore(" ", "§c§lTHIS CONFLICTS WITH ALREADY SET ENCHANTMENTS!");
                inventory.setItem(slotList[i], builder.build());
                i++;
            }
        }
    },

    Books(new ItemBuilder(Material.BOOK).setName("§aBooks").addAllLore("§7Knowledge is §6power§7! Apply", "§7special books to your item to", "§7upgrade it!").build()) {
        //Hotpo: 21 Fbo: 22
        @Override
        public GUI factor(Hex obj) {
            GUI gui = new GUI(craftSideView(obj, "Books", this)
                    .setItem(21, hotPotatoBookShowItem(obj))
                    .setItem(22, fumingPotatoBookShowItem(obj))
                    .build());
            gui.inventoryClickAction(21, type -> {
                int hotP = hotpotatobooks(obj.getItem());
                if (hotP > 9) {
                    obj.getPlayer().sendMessage("§cYou already have the maximum amount of Hot Potato Books applied!");
                    return;
                }
                applyHotp(obj.getItem(), hotP + 1);
                obj.getPlayer().playSound(obj.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                gui.getInventory().setItem(21, hotPotatoBookShowItem(obj));
                gui.getInventory().setItem(22, fumingPotatoBookShowItem(obj));
                Main.itemUpdater(obj.getItem(), obj.getPlayer());
                gui.getInventory().setItem(19, obj.getItem());
            });
            gui.inventoryClickAction(22, type -> {
                int hotP = hotpotatobooks(obj.getItem());
                if (hotP > 14) {
                    obj.getPlayer().sendMessage("§cYou already have the maximum amount of Potato Books applied!");
                    return;
                }
                applyHotp(obj.getItem(), hotP + 1);
                obj.getPlayer().playSound(obj.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                gui.getInventory().setItem(21, hotPotatoBookShowItem(obj));
                gui.getInventory().setItem(22, fumingPotatoBookShowItem(obj));
                Main.itemUpdater(obj.getItem(), obj.getPlayer());
                gui.getInventory().setItem(19, obj.getItem());
            });
            return gui;
        }

        public ItemStack hotPotatoBookShowItem(Hex obj) {
            return new ItemBuilder(Material.BOOK).setGlint(true)
                    .setName(ItemRarity.EPIC.getPrefix() + "Hot Potato Book")
                    .addAllLore("§7When applied to armor, grants §a+2" + Stats.Defense.symbol, "§aDefense §7and §c+4" + Stats.Health + "§7.", "§7 ", "§7When applied to weapons, grants",
                            "§c+2" + Stats.Strength + " §7and §c+2" + Stats.WeaponDamage + "§7.", "§8 ", "§7This can be applied to an item up to", "§a10 §7times!")
                    .addLoreIf(() -> hotpotatobooks(obj.getItem()) > 9, "§cYou have already applied the", "§cmaximum number of Hot Potato Books", "§cto this item! Use Fuming Potato Books",
                            "§cto continue to upgrade this item!")
                    .build();
        }

        public ItemStack fumingPotatoBookShowItem(Hex obj) {
            return new ItemBuilder(Material.BOOK).setGlint(true)
                    .setName(ItemRarity.EPIC.getPrefix() + "Fuming Potato Book")
                    .addAllLore("§7When applied to armor, grants §a+2" + Stats.Defense.symbol, "§aDefense §7and §c+4" + Stats.Health + "§7.", "§7 ", "§7When applied to weapons, grants",
                            "§c+2" + Stats.Strength + " §7and §c+2" + Stats.WeaponDamage + "§7.", "§8 ", "§7This book bypasses the " + ItemRarity.EPIC.getPrefix() + "Hot Potato",ItemRarity.EPIC.getPrefix()+
                                    "Book §7limit of §a10§7, allowing you to upgrade an item up to §a15 §7times!")
                    .addLoreIf(() -> hotpotatobooks(obj.getItem()) > 14, "§cYou have already applied the maximum number", "§cof Potato Books")
                    .build();
        }

        public int hotpotatobooks(ItemStack item) {
            return ItemHandler.getOrDefaultPDC("potatobooks", item, PersistentDataType.INTEGER, 0);
        }

        public void applyHotp(ItemStack item, int level) {
            ItemHandler.setPDC("potatobooks", item, PersistentDataType.INTEGER, level);
        }
    };
    private static InventoryBuilder craftSideView(Hex hex, String name, HexOption opt) {
        return new InventoryBuilder(6, "The Hex ➜ " + name)
                .fill(TemplateItems.EmptySlot.getItem())
                .setItem(19, hex.getItem())
                .setItem(28, opt.display)
                .setItem(45, TemplateItems.BackArrow.getItem());
    }

    @Getter
    private final ItemStack display;
    HexOption(@NotNull ItemStack display) {
        this.display = display;
    }
    protected final int[] slotList = {12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 30, 31, 32, 33, 34};

}
