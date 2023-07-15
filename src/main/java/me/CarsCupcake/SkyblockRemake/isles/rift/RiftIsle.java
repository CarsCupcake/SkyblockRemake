package me.CarsCupcake.SkyblockRemake.isles.rift;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.Region;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.RegionCuboid;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.RegionHandler;
import me.CarsCupcake.SkyblockRemake.abilities.ABILITIES;
import me.CarsCupcake.SkyblockRemake.isles.rift.boss.leechSupreme.LeechFightManager;
import me.CarsCupcake.SkyblockRemake.isles.rift.entitys.spawningAreas.ShySpawning;
import me.CarsCupcake.SkyblockRemake.isles.rift.regions.BlackLagoonHandler;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;
import org.bukkit.util.Vector;

import java.util.List;

public class RiftIsle {
    public RiftIsle(){
        ABILITIES.registerEvent(new RegionHandler(
                List.of(
                        new Region("§dWizard Tower", List.of(
                                new RegionCuboid(new Vector(-51, 133, 65), new Vector(-38, 71, 80))
                        ), new NearbyRegion(0), new StopTimerRegion(0), null),
                        new Region("§cLeeches Lair", List.of(
                                LeechFightManager.bossArea
                        ), null, new ActiveTimerRegion(0), null),
                        new Region("§aWyld Woods", List.of(
                                new RegionCuboid(new Vector(-171, 0, 99), new Vector(-21, 200, 204)),
                                new RegionCuboid(new Vector(-141, 0, 100), new Vector(-29, 200, 62))
                        ), null, new ActiveTimerRegion(0), null),
                        new Region("§0Black Lagoon", List.of(
                                new RegionCuboid(new Vector(-55, 0, 62), new Vector(-244, 200, -2)),
                                new RegionCuboid(new Vector(-171, 0, 98), new Vector(-244, 200, 191))),
                                new NearbyRegion(240), new ActiveTimerRegion(240), null
                        ))
                ));
        new ShySpawning();
        LeechFightManager.getInstance().spawn();
        ABILITIES.registerEvent(new BlackLagoonHandler());
    }
    record StopTimerRegion(long riftTimeConsumtion) implements RunnableWithParam<Bundle<SkyblockPlayer, Region>> {
        @Override
        public void run(Bundle<SkyblockPlayer, Region> b) {
            RiftPlayer p = RiftPlayer.getRiftPlayer(b.getFirst());
            if(p.getTimer().isRunning()) p.getTimer().pause();
        }
    }
    record ActiveTimerRegion(long riftTimeConsumtion) implements RunnableWithParam<Bundle<SkyblockPlayer, Region>> {
        @Override
        public void run(Bundle<SkyblockPlayer, Region> b) {
            RiftPlayer p = RiftPlayer.getRiftPlayer(b.getFirst());
            if(!p.getTimer().isRunning()) p.getTimer().start();
        }
    }
    record NearbyRegion(long riftTimeConsumtion) implements RunnableWithParam<Bundle<SkyblockPlayer, Region>> {
        @Override
        public void run(Bundle<SkyblockPlayer, Region> b) {

        }
    }
}
