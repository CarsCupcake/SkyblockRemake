package me.CarsCupcake.SkyblockRemake.isles.hub;

import me.CarsCupcake.SkyblockRemake.isles.hub.spawnables.CryptGhoulSpawner;
import me.CarsCupcake.SkyblockRemake.isles.hub.spawnables.GoldenGhoulSpawner;
import me.CarsCupcake.SkyblockRemake.isles.hub.spawnables.GraveyardZombieSpawner;
import me.CarsCupcake.SkyblockRemake.isles.hub.spawnables.ZombieVillagerSpawner;

public class HubManager {
    public HubManager() {
        new CryptGhoulSpawner();
        new GoldenGhoulSpawner();
        new GraveyardZombieSpawner();
        new ZombieVillagerSpawner();
    }
}
