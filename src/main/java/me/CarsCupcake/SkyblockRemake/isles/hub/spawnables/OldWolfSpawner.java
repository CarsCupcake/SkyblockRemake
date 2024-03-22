package me.CarsCupcake.SkyblockRemake.isles.hub.spawnables;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Spawnable;
import me.CarsCupcake.SkyblockRemake.isles.hub.mobs.OldWolf;
import org.bukkit.Location;

public class OldWolfSpawner extends Spawnable {
    @Override
    public Location[] getSpawnLocations() {
        return new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -270.5, 90.0, 98.5),new Location(Main.getMain().getServer().getWorld("world"), -256.5, 105.0, 75.5)};
    }

    @Override
    public long frequence() {
        return 20 * 20;
    }

    @Override
    public Class<? extends SkyblockEntity> spawnEntity() {
        return OldWolf.class;
    }
}
