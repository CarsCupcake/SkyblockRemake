package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r2x2;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Location2d;
import org.bukkit.Location;

import java.util.Set;

public abstract class Room2x2 extends IRoom {
    @Override
    public Set<Location2d> getNextLocations(Location2d base, int rotation) {
       //TODO: FINISH!
        throw new  UnsupportedOperationException("Not Done");
    }

    @Override
    public Location rotationCorner(Location l, int rotation) {
        switch (rotation) {
            case 1 -> {
                return l.add(0,0,62);
            }
            case 2 -> {
                return l.add(62, 0, 62);
            }
            case 3 -> {
                return l.add(62, 0, 0);
            }
        }
        return l;
    }
}
