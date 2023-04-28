package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x1.r;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Direction;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Location2d;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x1.Room1x1;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.HashSet;
import java.util.Set;

public class Andeside2 extends Room1x1 {
    @Override
    public String fileLocation() {
        return "assets/schematics/dungeon/rooms/1x1/1x1-Andesite-2.schematic";
    }

    @Override
    public void init(int rotation, Location2d base) {
        Bukkit.getWorld("world").spawn(relativeToActual(new Location(Bukkit.getWorld("world"), 15, 99, 30), rotation, base), ArmorStand.class);
    }

    @Override
    public Set<Location2d> getNextLocations(Location2d base, int rotation) {
        return new HashSet<>(Set.of(Direction.Left.spinLeft(rotation).move(base.clone()), Direction.Right.spinLeft(rotation).move(base.clone())));
    }

    @Override
    public String getId() {
        return "Andeside-2";
    }
}
