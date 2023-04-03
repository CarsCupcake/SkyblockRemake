package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x2;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Location2d;
import org.bukkit.Location;

import java.util.Set;

public abstract class Room1x2 implements IRoom {
    @Override
    public Set<Location2d> getNextLocations(Location2d base, int rotation) {
       //TODO: FINISH!
        throw new  UnsupportedOperationException("Not Done");
    }

    @Override
    public Location getOffset(Location location, int rotation) {
        //TODO: FINISH!
        throw new  UnsupportedOperationException("Not Done");
    }
    @Override
    public Location rotationCorner(Location l, int rotation) {
        return switch (rotation){
            case 1 -> new Location(l.getWorld(), l.getX(), l.getY(), l.getZ() + 30);
            case 2 -> new Location(l.getWorld(), l.getX() + 61, l.getY(), l.getZ() + 30);
            case 3 -> new Location(l.getWorld(), l.getX() + 61, l.getY(), l.getZ());
            default -> l;
        };
    }
}
