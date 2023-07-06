package me.CarsCupcake.SkyblockRemake.isles.rift;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.Region;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.RegionCuboid;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.RegionHandler;
import me.CarsCupcake.SkyblockRemake.abilities.ABILITIES;
import me.CarsCupcake.SkyblockRemake.isles.rift.boss.leechSupreme.BossFightManager;
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
                                BossFightManager.bossArea
                        ), null, skyblockPlayerRegionBundle -> {
                            BossFightManager.getInstance().add(skyblockPlayerRegionBundle.getFirst());
                            new ActiveTimerRegion(0).run(skyblockPlayerRegionBundle);
                        },
                                skyblockPlayerRegionBundle -> BossFightManager.getInstance().remove(skyblockPlayerRegionBundle.getFirst()))
                )));
        BossFightManager.getInstance().spawn();
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
