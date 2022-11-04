package me.CarsCupcake.SkyblockRemake.End.Teleporter;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.ITeleporter;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.Spawnable;
import org.bukkit.Location;

public class VoidSephultures extends Spawnable implements ITeleporter {
    @Override
    public Location baseLocation() {
        return new Location(Main.getMain().getServer().getWorld("world"), -562.5,7,-314.5);
    }

    @Override
    public Location teleportTo() {
        return new Location(Main.getMain().getServer().getWorld("world"), -569.5,7,-318.5, 45, 0);
    }

    @Override
    public Location[] getSpawnLocations() {
        return new Location[0];
    }

    @Override
    public long frequence() {
        return 0;
    }

    @Override
    public Class<? extends SkyblockEntity> spawnEntity() {
        return null;
    }
}
