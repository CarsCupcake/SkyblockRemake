package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminal;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryGUIAction;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class OrderTerminal extends Terminal {
    public OrderTerminal(F7Phase3 phase, int tid) {
        super(phase, tid);
    }

    @Override
    public void open(@NotNull SkyblockPlayer player) {
        this.isOpen = true;

        List<Integer> order = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        Collections.shuffle(order);

        InventoryBuilder builder = new InventoryBuilder(4, "Click in order!").fill(TemplateItems.EmptySlot.getItem());
        int i = 10;
        for (int l : order){
            builder.setItem(new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§a" + l).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).setAmount(l).build(), i);
            i++;
            if(i == 17)
                i += 2;
        }

        GUI gui = new GUI(builder.build());
        gui.closeAction(type -> isOpen = false);
        gui.setCanceled(true);
        gui.setGeneralAction(new InventoryGUIAction() {
            private int current = 0;

            @Override
            public void run(int slot, GUI.GUIActions actionType, ClickType type) {
                    if(actionType != GUI.GUIActions.Click)
                        return;
                    int i;
                    try {
                        i = Integer.parseInt(gui.getInventory().getItem(slot).getItemMeta().getDisplayName().substring(2));
                    }catch (Exception ignored){
                        return;
                    }
                    if(i != current + 1)
                        return;
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
                    current++;
                    gui.getInventory().setItem(slot, new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).setName("§a" + current).setAmount(current).build());
                    if(current == 14) {
                        gui.closeInventory();
                        if(phase != null)
                            phase.solveTerminal(OrderTerminal.this, player);
                    }
            }

        });
        gui.showGUI(player);
    }
}
