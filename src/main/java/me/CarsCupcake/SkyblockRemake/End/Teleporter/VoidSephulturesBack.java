package me.CarsCupcake.SkyblockRemake.End.Teleporter;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.ITeleporter;
import org.bukkit.Location;

public class VoidSephulturesBack implements ITeleporter {
    @Override
    public Location baseLocation() {
        return new Location(Main.getMain().getServer().getWorld("world"), -567.5, 7, -319.5);
    }

    @Override
    public Location teleportTo() {
        return new Location(Main.getMain().getServer().getWorld("world"), -561.5, 7, -313.5, -45, 0);
    }
}
