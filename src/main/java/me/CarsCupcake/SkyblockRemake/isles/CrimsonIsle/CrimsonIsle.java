package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.boss.Bladesoul;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.story.TestNpc;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class CrimsonIsle {
    private static TestNpc testNpc;
    private static final Location bladesoul = new Location(Bukkit.getWorld("world_nether"), -294.5, 82.00, -517.5);
    public CrimsonIsle(){
        testNpc = new TestNpc();
        new Bladesoul(bladesoul);
    }
    public static void init(SkyblockPlayer player){
        Main.getDebug().debug("Added " + player.getName() + " to Crimson Isle quest", false);
        testNpc.spawn(player);
    }
}
