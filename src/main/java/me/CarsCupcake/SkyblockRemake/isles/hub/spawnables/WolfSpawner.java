package me.CarsCupcake.SkyblockRemake.isles.hub.spawnables;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Spawnable;
import me.CarsCupcake.SkyblockRemake.isles.hub.mobs.Wolf;
import org.bukkit.Location;

public class WolfSpawner extends Spawnable {
    @Override
    public Location[] getSpawnLocations() {
        return new Location[]{new Location(Main.getMain().getServer().getWorld("world"), -215.5, 91.0, 56.5),new Location(Main.getMain().getServer().getWorld("world"), -230.5, 100.0, 53.5),new Location(Main.getMain().getServer().getWorld("world"), -235.5, 100.0, 56.5),new Location(Main.getMain().getServer().getWorld("world"), -254.5, 93.0, 59.5),new Location(Main.getMain().getServer().getWorld("world"), -254.5, 93.0, 76.5),new Location(Main.getMain().getServer().getWorld("world"), -240.5, 94.0, 73.5),new Location(Main.getMain().getServer().getWorld("world"), -231.5, 97.0, 85.5),new Location(Main.getMain().getServer().getWorld("world"), -272.5, 90.0, 78.5),new Location(Main.getMain().getServer().getWorld("world"), -264.5, 91.0, 96.5),new Location(Main.getMain().getServer().getWorld("world"), -244.5, 93.0, 104.5),new Location(Main.getMain().getServer().getWorld("world"), -222.5, 92.0, 93.5),new Location(Main.getMain().getServer().getWorld("world"), -239.5, 92.0, 121.5),new Location(Main.getMain().getServer().getWorld("world"), -221.5, 92.0, 124.5),new Location(Main.getMain().getServer().getWorld("world"), -208.5, 90.0, 104.5),new Location(Main.getMain().getServer().getWorld("world"), -181.5, 86.0, 88.5),new Location(Main.getMain().getServer().getWorld("world"), -178.5, 86.0, 76.5),new Location(Main.getMain().getServer().getWorld("world"), -205.5, 91.0, 46.5)};
    }

    @Override
    public long frequence() {
        return 10 * 20;
    }

    @Override
    public Class<? extends SkyblockEntity> spawnEntity() {
        return Wolf.class;
    }
}
