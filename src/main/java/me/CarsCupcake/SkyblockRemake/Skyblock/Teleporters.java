package me.CarsCupcake.SkyblockRemake.Skyblock;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class Teleporters implements Listener {
    private static final ArrayList<ITeleporter> t = new ArrayList<>();
public static void registerTeleporter(ITeleporter teleporter){
    t.add(teleporter);
}
@EventHandler
    public void onMove(PlayerMoveEvent event){
    if(event.isCancelled())
        return;
    for(ITeleporter teleporter : t) {
        if (event.getPlayer().getLocation().distance(teleporter.baseLocation()) <= 0.5)
            event.getPlayer().teleport(teleporter.teleportTo());
    }

}
}
