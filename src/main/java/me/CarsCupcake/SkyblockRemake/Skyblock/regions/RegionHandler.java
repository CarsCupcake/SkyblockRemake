package me.CarsCupcake.SkyblockRemake.Skyblock.regions;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public record RegionHandler(List<Region> regions) implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        if(player == null) return;
        if (player.getRegion() == null || !player.getRegion().isInRegion(player)) {
            if(player.getRegion() != null) player.getRegion().leave(player);
            player.setRegion(null);
            for (Region r : regions())
                if (r.isInRegion(player)) {
                    player.setRegion(r);
                    r.enter(player);
                    break;
                }
        }
        for (Region r : regions()){
            if(r == player.getRegion()) continue;
            if(r.onNear() == null) continue;
            if(r.distanceToBorder(player) < 6) {
                r.near(player);
            }
        }
    }

}
