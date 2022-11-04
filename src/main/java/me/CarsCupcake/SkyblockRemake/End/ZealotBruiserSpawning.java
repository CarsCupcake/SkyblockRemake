package me.CarsCupcake.SkyblockRemake.End;

import me.CarsCupcake.SkyblockRemake.End.Entitys.Zealot;
import me.CarsCupcake.SkyblockRemake.End.Entitys.ZealotBruiser;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Spawnable;
import org.bukkit.Location;

import java.util.TreeMap;

public class ZealotBruiserSpawning extends Spawnable {
    private static final Location[] locs = {new Location(Main.getMain().getServer().getWorld("world"), -557.5, 62.0, -222.5),new Location(Main.getMain().getServer().getWorld("world"), -565.5, 67.0, -210.5),new Location(Main.getMain().getServer().getWorld("world"), -569.5, 69.0, -206.5),new Location(Main.getMain().getServer().getWorld("world"), -574.5, 72.0, -200.5),new Location(Main.getMain().getServer().getWorld("world"), -599.5, 55.0, -202.5),new Location(Main.getMain().getServer().getWorld("world"), -585.5, 55.0, -207.5),new Location(Main.getMain().getServer().getWorld("world"), -594.5, 55.0, -220.5),new Location(Main.getMain().getServer().getWorld("world"), -588.5, 52.0, -234.5),new Location(Main.getMain().getServer().getWorld("world"), -571.5, 51.0, -237.5),new Location(Main.getMain().getServer().getWorld("world"), -553.5, 56.0, -238.5),new Location(Main.getMain().getServer().getWorld("world"), -542.5, 54.0, -239.5),new Location(Main.getMain().getServer().getWorld("world"), -536.5, 50.0, -248.5),new Location(Main.getMain().getServer().getWorld("world"), -524.5, 43.0, -266.5),new Location(Main.getMain().getServer().getWorld("world"), -509.5, 38.0, -273.5),new Location(Main.getMain().getServer().getWorld("world"), -520.5, 38.0, -288.5),new Location(Main.getMain().getServer().getWorld("world"), -525.5, 38.0, -300.5),new Location(Main.getMain().getServer().getWorld("world"), -516.5, 39.0, -304.5),new Location(Main.getMain().getServer().getWorld("world"), -525.5, 39.0, -317.5),new Location(Main.getMain().getServer().getWorld("world"), -522.5, 39.0, -254.5),new Location(Main.getMain().getServer().getWorld("world"), -531.5, 38.0, -243.5),new Location(Main.getMain().getServer().getWorld("world"), -531.5, 38.0, -226.5),new Location(Main.getMain().getServer().getWorld("world"), -519.5, 38.0, -236.5)};
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
        return 240;
    }

    @Override
    public Class<? extends SkyblockEntity> spawnEntity() {
        return ZealotBruiser.class;
    }
}
