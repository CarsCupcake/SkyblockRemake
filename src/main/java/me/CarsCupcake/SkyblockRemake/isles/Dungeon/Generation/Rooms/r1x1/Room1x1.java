package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x1;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Direction;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Location2d;
import org.bukkit.Location;

import java.util.Set;

public abstract class Room1x1 implements IRoom {
    @Override
    public Set<Location2d> getNextLocations(Location2d base, int rotation) {
        return Set.of(Direction.Up.move(base.clone()), Direction.Down.move(base.clone()), Direction.Left.move(base.clone()), Direction.Right.move(base.clone()));
    }



    @Override
    public Location rotationCorner(Location l, int rotation) {
        return switch (rotation){
            case 1 -> new Location(l.getWorld(), l.getX(), l.getY(), l.getZ() + 30);
            case 2 -> new Location(l.getWorld(), l.getX() + 30, l.getY(), l.getZ() + 30);
            case 3 -> new Location(l.getWorld(), l.getX() + 30, l.getY(), l.getZ());
            default -> l;
        };
    }
}