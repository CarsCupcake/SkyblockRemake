package me.CarsCupcake.SkyblockRemake.utils.loot;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;

public interface Loot {
    void consume(SkyblockPlayer killer, Location dropLocation, boolean toPlayer);
    String name();
}
