package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminal;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PanesTerminal extends Terminal {
    private final static List<Integer> SLOTS = Arrays.asList(11, 12, 13, 14, 15, 20, 21, 22, 23, 24, 29, 30, 31, 32, 33);


    public PanesTerminal(F7Phase3 phase, int terminalId) {
        super(phase, terminalId);
    }

    @Override
    public void open(@NotNull SkyblockPlayer player) {
        isOpen = true;
        InventoryBuilder builder = new InventoryBuilder(5, "Correct all the panes!").fill(TemplateItems.EmptySlot.getItem());


        for (int i : SLOTS){


            double r = new Random().nextDouble();
            builder.setItem(new ItemBuilder((r < 0.2) ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE).setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build(), i);
        }

        GUI gui = new GUI(builder.build());
        gui.setCanceled(true);
        gui.setGeneralAction((slot, actionType, type) -> {
            if(actionType != GUI.GUIActions.Click)
                return;
            if(!SLOTS.contains(slot))
                return;
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
            if(gui.getInventory().getItem(slot).getType() == Material.RED_STAINED_GLASS_PANE){
                gui.getInventory().getItem(slot).setType(Material.LIME_STAINED_GLASS_PANE);
                if(isDone(gui.getInventory())){
                    gui.closeInventory();
                    if(PanesTerminal.this.phase != null)
                        PanesTerminal.this.phase.solveTerminal(PanesTerminal.this, player);
                }
            }else
                gui.getInventory().getItem(slot).setType(Material.RED_STAINED_GLASS_PANE);
        });
        gui.closeAction(type -> isOpen = false);
        gui.showGUI(player);
    }
    private boolean isDone(Inventory inv){
        for (int i : SLOTS){
            if(inv.getItem(i).getType() == Material.RED_STAINED_GLASS_PANE)
                return false;
        }
        return true;
    }
}
