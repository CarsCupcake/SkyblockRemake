package me.CarsCupcake.SkyblockRemake.Collections;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class CollectHandler implements Listener {
    @EventHandler
    public void onPickup(EntityPickupItemEvent event){
        if(!(event.getEntity() instanceof Player))
            return;

        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) event.getEntity());
    }
}
