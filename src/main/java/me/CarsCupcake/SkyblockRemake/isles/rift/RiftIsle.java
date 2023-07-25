package me.CarsCupcake.SkyblockRemake.isles.rift;

import kotlin.Triple;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.Region;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.RegionCuboid;
import me.CarsCupcake.SkyblockRemake.Skyblock.regions.RegionHandler;
import me.CarsCupcake.SkyblockRemake.abilities.ABILITIES;
import me.CarsCupcake.SkyblockRemake.isles.rift.boss.leechSupreme.LeechFightManager;
import me.CarsCupcake.SkyblockRemake.isles.rift.entitys.spawningAreas.ShySpawning;
import me.CarsCupcake.SkyblockRemake.isles.rift.regions.BlackLagoonHandler;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import java.util.HashMap;
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
            RiftPlayer player = RiftPlayer.getRiftPlayer(b.getFirst());
            if(player.getPaiedRegions().contains(b.getLast())) return;
            if(player.getRiftTime() < riftTimeConsumtion){
                Vector v = b.getLast().getNextPoint(player.getLocation());
                player.setVelocity(player.getLocation().toVector().subtract(v).multiply(-4).setY(1));
                player.sendMessage("You do not have enouth Rift Time!");
                player.setRegion(null);
                return;
            }
            if(!player.getTimer().isRunning()) player.getTimer().start();
            player.subtractRiftTime(riftTimeConsumtion);
            if(riftTimeConsumtion != 0) player.sendMessage("Consumed §a" + toTimeString(Tools.breakDownTime(player.getRiftTime())) + " rift time to enter");
            player.getPaiedRegions().add(b.getLast());
        }
    }
    record NearbyRegion(long riftTimeConsumtion) implements RunnableWithParam<Bundle<SkyblockPlayer, Region>> {
        private static final HashMap<SkyblockPlayer, ArmorStand> stands =  new HashMap<>();
        @Override
        public void run(Bundle<SkyblockPlayer, Region> b) {
            System.out.println("Near");
            Location l = b.getLast().getNextPoint(b.getFirst().getLocation()).toLocation(b.getFirst().getWorld());
            if(!stands.containsKey(b.getFirst())){
                stands.put(b.getFirst(), l.getWorld().spawn(l, ArmorStand.class, armorStand -> {
                    armorStand.setMarker(true);
                    armorStand.setInvisible(true);
                    armorStand.setBasePlate(false);
                    armorStand.setCustomNameVisible(true);
                    armorStand.setCustomName("§7Rift Time consumtion: §a" + toTimeString(Tools.breakDownTime(((RiftPlayer) b.getFirst()).getRiftTime())));
                }));
            }
            ArmorStand s = stands.get(b.getFirst());
            s.teleport(l);
        }

    }
    private static String toTimeString(Triple<Long, Long, Long> time){
        StringBuilder builder = new StringBuilder();
        if(time.getThird() != 0)
            builder.append(time.getThird()).append("h").append(" ");
        if(time.getSecond() != 0)
            builder.append(time.getSecond()).append("m").append(" ");
        builder.append((time.getFirst() < 10) ? "0" : "").append(time.getFirst()).append("s");
        return builder.toString();
    }

}
