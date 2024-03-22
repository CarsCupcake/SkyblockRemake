package me.CarsCupcake.SkyblockRemake.isles.hub.spawnables;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Spawnable;
import me.CarsCupcake.SkyblockRemake.isles.hub.mobs.GraveyardZombie;
import org.bukkit.Location;

public class GraveyardZombieSpawner extends Spawnable {
    private static final Location[] locs = {new Location(Main.getMain().getServer().getWorld("world"), -105.5, 71.0, -61.5),new Location(Main.getMain().getServer().getWorld("world"), -112.5, 71.0, -61.5),new Location(Main.getMain().getServer().getWorld("world"), -114.5, 71.0, -66.5),new Location(Main.getMain().getServer().getWorld("world"), -117.5, 71.0, -73.5),new Location(Main.getMain().getServer().getWorld("world"), -124.5, 71.0, -75.5),new Location(Main.getMain().getServer().getWorld("world"), -121.5, 71.0, -82.5),new Location(Main.getMain().getServer().getWorld("world"), -117.5, 71.0, -89.5),new Location(Main.getMain().getServer().getWorld("world"), -110.5, 72.0, -103.5),new Location(Main.getMain().getServer().getWorld("world"), -101.5, 72.0, -111.5),new Location(Main.getMain().getServer().getWorld("world"), -99.5, 72.0, -127.5),new Location(Main.getMain().getServer().getWorld("world"), -93.5, 72.0, -145.5),new Location(Main.getMain().getServer().getWorld("world"), -119.5, 72.0, -141.5),new Location(Main.getMain().getServer().getWorld("world"), -145.5, 72.0, -122.5),new Location(Main.getMain().getServer().getWorld("world"), -158.5, 72.0, -93.5),new Location(Main.getMain().getServer().getWorld("world"), -173.5, 74.0, -86.5),new Location(Main.getMain().getServer().getWorld("world"), -163.5, 72.0, -136.5),new Location(Main.getMain().getServer().getWorld("world"), -68.5, 79.0, -184.5),new Location(Main.getMain().getServer().getWorld("world"), -43.5, 80.0, -173.5)};

    @Override
    public Location[] getSpawnLocations() {
        return locs;
    }

    @Override
    public long frequence() {
        return 10 * 20;
    }

    @Override
    public Class<? extends SkyblockEntity> spawnEntity() {
        return GraveyardZombie.class;
    }
}
