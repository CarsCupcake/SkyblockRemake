package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x1.r;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Dungeon;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.DungeonEnemys;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Direction;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Location2d;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x1.Room1x1;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.HashSet;
import java.util.Set;

public class Andeside2 extends Room1x1 {
    private static final Location[] archers = {new Location(Main.getMain().getServer().getWorld("world"), 13.5, 69.0, 12.5),new Location(Main.getMain().getServer().getWorld("world"), 17.5, 69.0, 13.5),new Location(Main.getMain().getServer().getWorld("world"), 14.5, 69.0, 15.5),new Location(Main.getMain().getServer().getWorld("world"), 15.5, 69.0, 17.5),new Location(Main.getMain().getServer().getWorld("world"), 17.5, 69.0, 19.5),new Location(Main.getMain().getServer().getWorld("world"), 20.5, 69.0, 19.5),new Location(Main.getMain().getServer().getWorld("world"), 21.5, 69.0, 16.5),new Location(Main.getMain().getServer().getWorld("world"), 21.5, 69.0, 13.5),new Location(Main.getMain().getServer().getWorld("world"), 19.5, 69.0, 22.5)};
    private static final Location[] melee = {new Location(Main.getMain().getServer().getWorld("world"), 4.5, 71.0, 12.5),new Location(Main.getMain().getServer().getWorld("world"), 3.5, 69.0, 15.5),new Location(Main.getMain().getServer().getWorld("world"), 7.5, 69.0, 17.5),new Location(Main.getMain().getServer().getWorld("world"), 9.5, 69.0, 15.5),new Location(Main.getMain().getServer().getWorld("world"), 26.5, 69.0, 14.5),new Location(Main.getMain().getServer().getWorld("world"), 23.5, 69.0, 13.5),new Location(Main.getMain().getServer().getWorld("world"), 22.5, 69.0, 16.5),new Location(Main.getMain().getServer().getWorld("world"), 22.5, 69.0, 18.5),new Location(Main.getMain().getServer().getWorld("world"), 20.5, 69.0, 16.5)};
    @Override
    public String fileLocation() {
        return "assets/schematics/dungeon/rooms/1x1/1x1-Andesite-2.schematic";
    }

    @Override
    public void init(int rotation, Location2d base) {
        for (Location l : archers) DungeonEnemys.getFloorPool().spawnArcher(l, this, rotation, base, Dungeon.MobAttributes.Star);
        for (Location l : melee) DungeonEnemys.getFloorPool().spawnMelee(l, this, rotation, base, Dungeon.MobAttributes.Star);
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
