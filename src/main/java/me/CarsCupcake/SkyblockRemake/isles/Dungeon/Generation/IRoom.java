package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation;

import org.bukkit.Location;

import java.util.Set;

public interface IRoom {
    String fileLocation();
    void init(int rotation);
    Set<Location2d> getNextLocations(Location2d base, int rotation);
    Location getOffset(Location location, int rotation);
}
