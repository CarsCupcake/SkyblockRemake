package me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.lShaped.r;


import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Location2d;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.lShaped.RoomLSh;
import org.bukkit.Location;

public class Melon7 extends RoomLSh {
    @Override
    public String fileLocation() {
        return "assets/schematics/dungeon/rooms/LShaped/L-shape-Melon-7.schematic";
    }

    @Override
    public void init(int rotation, Location2d base) {

    }

    @Override
    public int baseRotation() {
        return 1;
    }

    @Override
    public String getId() {
        return "Melon-7";
    }

    @Override
    public Location rotationCorner(Location l, int rotation) {
        return super.rotationCorner(switch (rotation) {
            case 0 -> new Location(l.getWorld(), l.getX(), l.getY(), l.getZ());
            case 1 -> new Location(l.getWorld(), l.getX(), l.getY(), l.getZ() + 32);
            case 2 -> new Location(l.getWorld(), l.getX() + 32, l.getY(), l.getZ() + 32);
            case 3 -> new Location(l.getWorld(), l.getX() + 32, l.getY(), l.getZ());
            default -> l;
        }, rotation);
    }
}