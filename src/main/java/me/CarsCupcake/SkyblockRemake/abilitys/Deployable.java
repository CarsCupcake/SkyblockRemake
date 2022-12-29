package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


public interface Deployable extends AbilityManager<PlayerInteractEvent> {
    HashMap<SkyblockPlayer,Deployable> deployables = new HashMap<>();
    void start(Location loc);
    void stop();
    void addStatBoost(PlayerMoveEvent event);
}
