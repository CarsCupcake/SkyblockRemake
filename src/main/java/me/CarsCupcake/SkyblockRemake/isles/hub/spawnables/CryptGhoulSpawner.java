package me.CarsCupcake.SkyblockRemake.isles.hub.spawnables;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Spawnable;
import me.CarsCupcake.SkyblockRemake.isles.hub.mobs.CryptGhoul;
import org.bukkit.Location;

public class CryptGhoulSpawner extends Spawnable {
    private static final Location[] locs = {new Location(Main.getMain().getServer().getWorld("world"), -142.5, 56.0, -99.5),new Location(Main.getMain().getServer().getWorld("world"), -146.5, 57.0, -103.5),new Location(Main.getMain().getServer().getWorld("world"), -145.5, 58.0, -112.5),new Location(Main.getMain().getServer().getWorld("world"), -138.5, 59.0, -125.5),new Location(Main.getMain().getServer().getWorld("world"), -125.5, 58.0, -128.5),new Location(Main.getMain().getServer().getWorld("world"), -101.5, 49.0, -116.5),new Location(Main.getMain().getServer().getWorld("world"), -121.5, 42.0, -134.5),new Location(Main.getMain().getServer().getWorld("world"), -120.5, 42.0, -140.5),new Location(Main.getMain().getServer().getWorld("world"), -134.5, 42.0, -136.5),new Location(Main.getMain().getServer().getWorld("world"), -133.5, 51.0, -99.5),new Location(Main.getMain().getServer().getWorld("world"), -103.5, 48.0, -101.5),new Location(Main.getMain().getServer().getWorld("world"), -82.5, 47.0, -103.5),new Location(Main.getMain().getServer().getWorld("world"), -67.5, 49.0, -112.5),new Location(Main.getMain().getServer().getWorld("world"), -60.5, 51.0, -119.5),new Location(Main.getMain().getServer().getWorld("world"), -47.5, 56.0, -134.5),new Location(Main.getMain().getServer().getWorld("world"), -46.5, 57.0, -139.5),new Location(Main.getMain().getServer().getWorld("world"), -100.5, 39.0, -75.5),new Location(Main.getMain().getServer().getWorld("world"), -95.5, 39.0, -81.5),new Location(Main.getMain().getServer().getWorld("world"), -95.5, 39.0, -90.5),new Location(Main.getMain().getServer().getWorld("world"), -106.5, 39.0, -91.5),new Location(Main.getMain().getServer().getWorld("world"), -116.5, 44.0, -89.5)};

    @Override
    public Location[] getSpawnLocations() {
        return locs;
    }

    @Override
    public long frequence() {
        return 10;
    }

    @Override
    public Class<? extends SkyblockEntity> spawnEntity() {
        return CryptGhoul.class;
    }
}
