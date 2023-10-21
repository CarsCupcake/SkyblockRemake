package me.CarsCupcake.SkyblockRemake.Slayer.spider.entity;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;

public class SpiderListener implements Listener {
    public static final Set<SkyblockPlayer> players = new HashSet<>();

    @EventHandler
    public void onGetStatEvent(GetTotalStatEvent event) {
        if (event.getStat() != Stats.HealthRegen) return;
        if (players.contains(event.getPlayer())) event.addMultiplier(0.5);
    }
}
