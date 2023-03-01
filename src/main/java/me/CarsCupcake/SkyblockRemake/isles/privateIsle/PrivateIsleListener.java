package me.CarsCupcake.SkyblockRemake.isles.privateIsle;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PrivateIsleListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        if(!PrivateIslandManager.baseLocations.containsValue(player))
            return;
        if(event.getTo().distance(PrivateIslandManager.baseLocations.get(player)) > 120){
            player.teleport(PrivateIslandManager.baseLocations.get(player));
            player.sendMessage("§cYou can't go to far from your isle");
        }
    }
}
