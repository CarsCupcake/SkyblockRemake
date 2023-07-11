package me.CarsCupcake.SkyblockRemake.isles.rift.entitys.spawningAreas;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Spawnable;
import me.CarsCupcake.SkyblockRemake.isles.rift.entitys.crux.Shy;
import org.bukkit.Location;

public class ShySpawning extends Spawnable {
    @Override
    public Location[] getSpawnLocations() {
        return new Location[]{new Location(Main.getMain().getServer().getWorld("world_the_end"), -132.5, 72.0, 78.5), new Location(Main.getMain().getServer().getWorld("world_the_end"), -107.5, 72.0, 85.5), new Location(Main.getMain().getServer().getWorld("world_the_end"), -111.5, 68.0, 108.5), new Location(Main.getMain().getServer().getWorld("world_the_end"), -94.5, 67.0, 123.5), new Location(Main.getMain().getServer().getWorld("world_the_end"), -73.5, 67.0, 132.5), new Location(Main.getMain().getServer().getWorld("world_the_end"), -70.5, 65.0, 159.5), new Location(Main.getMain().getServer().getWorld("world_the_end"), -112.5, 71.0, 164.5), new Location(Main.getMain().getServer().getWorld("world_the_end"), -142.5, 67.0, 145.5), new Location(Main.getMain().getServer().getWorld("world_the_end"), -162.5, 69.0, 140.5), new Location(Main.getMain().getServer().getWorld("world_the_end"), -162.5, 73.0, 109.5), new Location(Main.getMain().getServer().getWorld("world_the_end"), -44.5, 71.0, 134.5), new Location(Main.getMain().getServer().getWorld("world_the_end"), -55.5, 67.0, 174.5)};
    }

    @Override
    public long frequence() {
        return 30;
    }

    @Override
    public Class<? extends SkyblockEntity> spawnEntity() {
        return Shy.class;
    }
}
