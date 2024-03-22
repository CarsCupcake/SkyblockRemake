package me.CarsCupcake.SkyblockRemake.isles.hub;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.isles.hub.spawnables.*;
import org.bukkit.scheduler.BukkitRunnable;

public class HubManager {
    public HubManager() {
        new BukkitRunnable() {
            public void run() {
                new CryptGhoulSpawner();
                new GoldenGhoulSpawner();
                new GraveyardZombieSpawner();
                new ZombieVillagerSpawner();
                new WolfSpawner();
                new OldWolfSpawner();
            }
        }.runTaskLater(Main.getMain(), 20);
    }
}
