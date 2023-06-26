package me.CarsCupcake.SkyblockRemake.Skyblock.regions;

import org.bukkit.Location;

public interface IBorder {
    double distance(Location location);
    boolean inTheBorder(Location location);

}
