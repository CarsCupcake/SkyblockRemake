package me.CarsCupcake.SkyblockRemake.End;

import me.CarsCupcake.SkyblockRemake.End.Entitys.Zealot;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Spawnable;
import org.bukkit.Location;

import java.util.TreeMap;

public class ZealotSpawning extends Spawnable {
    private static final Location[] locs = {new Location(Main.getMain().getServer().getWorld("world"), -634.5, 5, -276.5), new Location(Main.getMain().getServer().getWorld("world"), -649.5, 5, -271.5), new Location(Main.getMain().getServer().getWorld("world"), -635.5, 5, -264.5),new Location(Main.getMain().getServer().getWorld("world"), -624.5, 7, -248.5),new Location(Main.getMain().getServer().getWorld("world"), -635.5, 7, -236.5),
            new Location(Main.getMain().getServer().getWorld("world"), -650.5, 5, -224.5),new Location(Main.getMain().getServer().getWorld("world"), -665.5, 5, -233.5),new Location(Main.getMain().getServer().getWorld("world"), -673.5, 5, -225.5),
            new Location(Main.getMain().getServer().getWorld("world"), -705.5, 5, -234.5),new Location(Main.getMain().getServer().getWorld("world"), -711.5, 5, -251.5),new Location(Main.getMain().getServer().getWorld("world"), -690.5, 5, -265.5),
            new Location(Main.getMain().getServer().getWorld("world"), -714.5, 5, -289.5),new Location(Main.getMain().getServer().getWorld("world"), -731.5, 6, -309.5),new Location(Main.getMain().getServer().getWorld("world"), -699.5, 5, -323.5),new Location(Main.getMain().getServer().getWorld("world"), -666.5, 10, -314.5),new Location(Main.getMain().getServer().getWorld("world"), -640.5, 7, -305.5),new Location(Main.getMain().getServer().getWorld("world"), -624.5, 5, -319.5)};
    @Override
    public Location[] getSpawnLocations() {
        return locs;
    }
    public static Location getNearest(Location location){
        TreeMap<Double, Location> locationTreeMap = new TreeMap<>();
        for(Location l : locs)
            locationTreeMap.put(l.distance(location), l);
        return locationTreeMap.firstEntry().getValue();
    }


    @Override
    public long frequence() {
        return 200;
    }

    @Override
    public Class<? extends SkyblockEntity> spawnEntity() {
        return Zealot.class;
    }
}
