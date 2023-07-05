package me.CarsCupcake.SkyblockRemake.isles.rift.boss.leechSupreme;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.RegionCuboid;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class BossFightManager {
    public static final RegionCuboid bossArea = new RegionCuboid(new Vector(-178, 53, 38), new Vector(-123, 23, 80));
    private static BossFightManager manager;
    private final LeechSupremeEntity entity;
    private BossFightManager(){
        entity = new LeechSupremeEntity();
    }
    @NotNull
    public static BossFightManager getInstance(){
        return (manager == null) ? manager = new BossFightManager() : manager;
    }
    public void defeat(){
        manager = null;
        new BukkitRunnable() {
            @Override
            public void run() {
                getInstance().spawn();
            }
        }.runTaskLater(Main.getMain(), 200);
    }
    public void spawn(){
        entity.spawn(new Location(Bukkit.getWorld("world_the_end"), -149.5, 33, 56.5));
    }
    public void add(SkyblockPlayer player){

    }
    public void remove(SkyblockPlayer player){

    }
    public void slimePound(){

    }
    public void wickedBombs(){

    }
    public void leechSwarm(){

    }
    public void mortiferousLazer(){

    }
}
