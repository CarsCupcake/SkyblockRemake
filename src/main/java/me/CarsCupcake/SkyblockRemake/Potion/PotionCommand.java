package me.CarsCupcake.SkyblockRemake.Potion;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.GUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PotionCommand implements CommandExecutor {
    @Getter
    private static final List<Inventory> invs = new ArrayList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player p))
            return false;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
        GUI gui = new GUI(invs.get(0));
        gui.setCanceled(true);
        final int[] page = {1};
        gui.playerInventoryClickAction(45, type ->  {
            if(page[0] != 1) {
                page[0]--;
                gui.swapInventory(invs.get(page[0] - 1));
            }
        });
        gui.playerInventoryClickAction(53, type -> {


                if(page[0]  < invs.size()) {
                    gui.swapInventory(invs.get(page[0]));
                    page[0]++;
                }

        });
        gui.setGeneralAction((slot, actionType, type) -> {
            if(actionType != GUI.GUIActions.Click)
                return;
            if(slot >= 45)
                return;
            player.addItem(gui.getInventory().getItem(slot));
        });
        gui.showGUI(player);
        return false;
    }

}
