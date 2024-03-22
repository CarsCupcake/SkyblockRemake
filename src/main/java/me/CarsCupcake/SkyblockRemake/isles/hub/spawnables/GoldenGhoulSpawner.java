package me.CarsCupcake.SkyblockRemake.isles.hub.spawnables;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Spawnable;
import me.CarsCupcake.SkyblockRemake.isles.hub.mobs.GoldenGhoul;
import org.bukkit.Location;

public class GoldenGhoulSpawner extends Spawnable {
    @Override
    public Location[] getSpawnLocations() {
        return new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -101.5, 39.0, -85.5),new Location(Main.getMain().getServer().getWorld("world"), -126.5, 42.0, -136.5)};
    }

    @Override
    public long frequence() {
        return 20;
    }

    @Override
    public Class<? extends SkyblockEntity> spawnEntity() {
        return GoldenGhoul.class;
    }
}
