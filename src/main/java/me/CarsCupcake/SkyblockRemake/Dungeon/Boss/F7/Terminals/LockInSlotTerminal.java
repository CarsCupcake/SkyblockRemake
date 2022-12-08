package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminal;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class LockInSlotTerminal extends Terminal {
    private static final ItemStack activeRowItem = new ItemBuilder(Material.LIME_TERRACOTTA).setName("§aLock In Slot").addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
            .addLoreRow("§7Click this buttone when the Red")
            .addLoreRow("§7slot lines up with the Purple")
            .addLoreRow("§7line!")
            .build();
    private static final ItemStack deactivatedRowItem = new ItemBuilder(Material.RED_TERRACOTTA).setName("§aRow Not Active").addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
            .addLoreRow("§7Click this buttone when the Red")
            .addLoreRow("§7slot lines up with the Purple")
            .addLoreRow("§7line!")
            .build();

    public LockInSlotTerminal(F7Phase3 phase, int terminalId) {
        super(phase, terminalId);
    }

    @Override
    public void open(@NotNull SkyblockPlayer player) {
        isOpen = true;
        InventoryBuilder builder = new InventoryBuilder(6, "Click the button on time!").fill(TemplateItems.EmptySlot.getItem()).fill(new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE)
                .setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build(), 19, 19 + 4).fill(new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE)
                .setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build(), 28, 28 + 4).fill(new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE)
                .setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build(), 37, 37 + 4).fill(new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                .setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build(), 11, 14).setItem(new ItemBuilder(Material.LIME_STAINED_GLASS_PANE)
                .setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build(), 10).setItem(activeRowItem, 16).setItem(deactivatedRowItem, 25).setItem(deactivatedRowItem, 34).setItem(deactivatedRowItem, 43);


     Random r = new Random();
        int low = 1;
        int high = 5;


        BukkitRunnable run = new BukkitRunnable() {
            private int slot = 1;
            private int target = r.nextInt(high - low) + low;
            private boolean dir = true;
            private int layer = 9;
            private GUI gui;
            private int penalty = 0;

            public BukkitRunnable startGame() {
                gui = new GUI(builder.build());
                gui.closeAction(type -> {
                    isOpen = false;
                    cancel();
                });
                gui.setGeneralAction((s, actionType, type) -> {
                    if (actionType == GUI.GUIActions.Click)
                        if (layer + 7 == s) {
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                            if (slot == target) {
                                layer += 9;
                                if (layer > 41) {
                                    gui.closeInventory();
                                    if (phase != null)
                                        phase.solveTerminal(LockInSlotTerminal.this, player);
                                } else {
                                    target = r.nextInt(high - low) + low;
                                    for (int i = layer - 9 + 1; i < layer - 9 + 5 + 1; i++)
                                        gui.getInventory().setItem(i, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE)
                                                .setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
                                    for (int i = layer + 1; i < layer + 5 + 1; i++)
                                        gui.getInventory().setItem(i, new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                                                .setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
                                    gui.getInventory().setItem(layer + 7, activeRowItem);
                                    gui.getInventory().setItem(layer - 9 + 7, deactivatedRowItem);
                                    gui.getInventory().getItem(layer + slot).setType(Material.LIME_STAINED_GLASS_PANE);
                                    updateHelper();
                                }

                            } else
                                penalty = 4;
                        }


                });
                gui.setCanceled(true);
                gui.showGUI(player);
                updateHelper();
                this.runTaskTimer(Main.getMain(), 10, 10);
                return this;
            }

            @Override
            public void run() {
                if (penalty > 0) {
                    penalty--;
                    return;
                }
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 0.2f, 2);
                if (dir) {
                    slot++;
                    gui.getInventory().getItem(layer + slot - 1).setType(Material.RED_STAINED_GLASS_PANE);
                    gui.getInventory().getItem(layer + slot).setType(Material.LIME_STAINED_GLASS_PANE);
                    if (slot == 5)
                        dir = false;
                } else {
                    slot--;
                    gui.getInventory().getItem(layer + slot + 1).setType(Material.RED_STAINED_GLASS_PANE);
                    gui.getInventory().getItem(layer + slot).setType(Material.LIME_STAINED_GLASS_PANE);
                    if (slot == 1)
                        dir = true;
                }

            }

            private void updateHelper() {
                for (int i = 1; i < 5; i++)
                    gui.getInventory().setItem(i, TemplateItems.EmptySlot.getItem());
                for (int i = 45 + 1; i < 45 + 5; i++)
                    gui.getInventory().setItem(i, TemplateItems.EmptySlot.getItem());
                gui.getInventory().setItem(target, new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE)
                        .setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
                gui.getInventory().setItem(target + 45, new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE)
                        .setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
            }
        }.startGame();

    }


}
