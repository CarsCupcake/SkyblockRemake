package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle;

import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.boss.Bladesoul;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class CrimsonIsle {
    private static final Location bladesoul = new Location(Bukkit.getWorld("world_nether"), -294.5, 82.00, -517.5);
    public CrimsonIsle(){
        System.out.println("Bla bla bla load");
        new Bladesoul(bladesoul);
    }
}
