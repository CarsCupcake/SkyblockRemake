package me.CarsCupcake.SkyblockRemake.Skyblock.regions;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public interface IBorder {
    double distance(Location location);
    boolean inTheBorder(Location location);
    Vector getNearestPoint(Location location);

}
