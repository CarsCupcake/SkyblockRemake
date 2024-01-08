package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

@Getter
public class KuudraBossfight {
    private static final Location[] possibleTentacle = {new Location(Main.getMain().getServer().getWorld("world"), -87.5, 69.0, -144.5),new Location(Main.getMain().getServer().getWorld("world"), -65.5, 69.0, -147.5),new Location(Main.getMain().getServer().getWorld("world"), -55.5, 69.0, -120.5),new Location(Main.getMain().getServer().getWorld("world"), -53.5, 69.0, -100.5),new Location(Main.getMain().getServer().getWorld("world"), -56.5, 69.0, -77.5),new Location(Main.getMain().getServer().getWorld("world"), -75.5, 69.0, -62.5),new Location(Main.getMain().getServer().getWorld("world"), -107.5, 69.0, -53.5),new Location(Main.getMain().getServer().getWorld("world"), -137.5, 69.0, -67.5),new Location(Main.getMain().getServer().getWorld("world"), -153.5, 69.0, -94.5),new Location(Main.getMain().getServer().getWorld("world"), -151.5, 69.0, -121.5),new Location(Main.getMain().getServer().getWorld("world"), -137.5, 69.0, -152.5)};
    public static KuudraBossfight bossfight;
    private final List<SkyblockPlayer> alive = new ArrayList<>(5);
    private final List<TentacleAI> tentacles = new ArrayList<>();
    private final int tier;

    public KuudraBossfight(int tier) {
        bossfight = this;
        this.tier = tier;
        List<Location> ls = new ArrayList<>(List.of(possibleTentacle));
        Collections.shuffle(ls);
        for (int i = 0; i < 7; i++) {
            Location location = ls.get(i);
            location.setY(75d);
            KuudraTentacle tentacle = new KuudraTentacle(tier);
            tentacle.spawn(location);
            tentacle.setAnimations(TentacleAnimations.Idle);
            TentacleAI ai = new TentacleAI(tentacle);
            tentacles.add(ai);
            new BukkitRunnable() {
                @Override
                public void run() {
                    ai.launchNewAttack();
                }
            }.runTaskLater(Main.getMain(), i * 80);
        }
        for (Player player : Bukkit.getOnlinePlayers()) alive.add(SkyblockPlayer.getSkyblockPlayer(player));
    }
    public void spawnTentacle(Location location) {
        location.setY(75d);
        KuudraTentacle tentacle = new KuudraTentacle(tier);
        tentacle.spawn(location);
        tentacle.setAnimations(TentacleAnimations.Idle);
        TentacleAI ai = new TentacleAI(tentacle);
        tentacles.add(ai);
        ai.launchNewAttack();
    }
}
