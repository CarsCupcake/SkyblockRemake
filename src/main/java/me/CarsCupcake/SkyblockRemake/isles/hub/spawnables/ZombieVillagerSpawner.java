package me.CarsCupcake.SkyblockRemake.isles.hub.spawnables;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Spawnable;
import me.CarsCupcake.SkyblockRemake.isles.hub.mobs.ZombieVillager;
import me.CarsCupcake.SkyblockRemake.utils.Time;
import org.bukkit.Location;

public class ZombieVillagerSpawner extends Spawnable {
    private static final Location[] locs = {new Location(Main.getMain().getServer().getWorld("world"), -114.5, 71.0, -68.5),new Location(Main.getMain().getServer().getWorld("world"), -122.5, 71.0, -81.5),new Location(Main.getMain().getServer().getWorld("world"), -140.5, 71.0, -89.5),new Location(Main.getMain().getServer().getWorld("world"), -176.5, 74.0, -85.5),new Location(Main.getMain().getServer().getWorld("world"), -165.5, 72.0, -121.5),new Location(Main.getMain().getServer().getWorld("world"), -146.5, 72.0, -144.5),new Location(Main.getMain().getServer().getWorld("world"), -122.5, 72.0, -133.5),new Location(Main.getMain().getServer().getWorld("world"), -100.5, 72.0, -135.5),new Location(Main.getMain().getServer().getWorld("world"), -101.5, 73.0, -164.5),new Location(Main.getMain().getServer().getWorld("world"), -71.5, 79.0, -182.5),new Location(Main.getMain().getServer().getWorld("world"), -48.5, 79.0, -169.5)};

    @Override
    public Location[] getSpawnLocations() {
        return locs;
    }

    @Override
    public long frequence() {
        return 20;
    }

    @Override
    public Class<? extends SkyblockEntity> spawnEntity() {
        return ZombieVillager.class;
    }

    @Override
    protected boolean canSpawn(Location location) {
        return (Time.getInstance().getHour() > 6 && Time.getInstance().getAmpm().equals("pm")) || (Time.getInstance().getHour() < 7 && Time.getInstance().getAmpm().equals("am"));
    }
}
