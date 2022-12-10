package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminals;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.Terminal;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.InventoryBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.TemplateItems;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorTerminal extends Terminal {
    private static final ArrayList<Integer> slots = new ArrayList<>(List.of(12,13,14,21,22,23,30,31,32));

    public ColorTerminal(F7Phase3 phase, int terminalId) {
        super(phase, terminalId);
    }

    @Override
    public void open(@NotNull SkyblockPlayer player) {
        isOpen = true;
        InventoryBuilder builder = new InventoryBuilder(5, "Change all to same color!").fill(TemplateItems.EmptySlot.getItem());
        for(int i : slots){
            Random r = new Random();
            int low = 1;
            int high = 5;
            int target = r.nextInt(high-low) + low;
            Material m;
            switch (target){
                case 1 -> m = Material.RED_STAINED_GLASS_PANE;
                case 2 -> m = Material.ORANGE_STAINED_GLASS_PANE;
                case 3 -> m = Material.YELLOW_STAINED_GLASS_PANE;
                case 4 -> m = Material.GREEN_STAINED_GLASS_PANE;
                case 5 -> m = Material.BLUE_STAINED_GLASS_PANE;
                default -> throw new RuntimeException("Illegal Number: " + target);
            }
            builder.setItem(new ItemBuilder(m).setName(" ").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build(), i);
        }
        GUI gui = new GUI(builder.build());
        gui.setCanceled(true);
        gui.closeAction(type -> isOpen = false);
        gui.setGeneralAction((slot, actionType, type) -> {
            if(actionType != GUI.GUIActions.Click)
                return;
            if(!slots.contains(slot))
                return;
            gui.getInventory().getItem(slot).setType(getNextColor(gui.getInventory().getItem(slot).getType()));
            if(isSame(gui.getInventory())){
                gui.closeInventory();
                finish(player);
            }
        });
        gui.showGUI(player);
    }

    private Material getNextColor(Material m){
        switch (m){
            case BLUE_STAINED_GLASS_PANE -> {
                return Material.RED_STAINED_GLASS_PANE;
            }
            case RED_STAINED_GLASS_PANE -> {
                return Material.ORANGE_STAINED_GLASS_PANE;
            }
            case ORANGE_STAINED_GLASS_PANE -> {
                return Material.YELLOW_STAINED_GLASS_PANE;
            }
            case YELLOW_STAINED_GLASS_PANE -> {
                return Material.GREEN_STAINED_GLASS_PANE;
            }
            case GREEN_STAINED_GLASS_PANE -> {
                return Material.BLUE_STAINED_GLASS_PANE;
            }
            default -> throw new IllegalArgumentException("Wrong Material: " + m);
        }
    }
    private boolean isSame(Inventory inventory){
        Material first = null;
        boolean t = true;
        for (int i : slots){
            if(t)
                first = inventory.getItem(i).getType();
            else
                if(first != inventory.getItem(i).getType())
                    return false;

            t = false;
        }
        return true;
    }
}
