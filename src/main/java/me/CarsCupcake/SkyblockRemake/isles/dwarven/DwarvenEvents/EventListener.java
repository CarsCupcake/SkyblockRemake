package me.CarsCupcake.SkyblockRemake.isles.dwarven.DwarvenEvents;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockScoreboard;

public class EventListener implements Listener {
    @EventHandler
    public void GoneWithTheWind(PlayerTurnEvent event) {
        if (DwarvenEvent.ActiveEvent != null && DwarvenEvent.ActiveEvent.getEvent() == DwarvenEvents.GoneWithTheWind) {
            SkyblockScoreboard.updateScoreboard(event.getPlayer());
        }
    }
}
