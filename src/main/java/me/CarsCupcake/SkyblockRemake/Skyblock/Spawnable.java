package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Spawnable {
    private BukkitRunnable runnable;
    private final List<SkyblockEntity> entitys = new ArrayList<>();
    private static final ArrayList<Spawnable> a = new ArrayList<>();

    public Spawnable() {
        init();
        a.add(this);
    }

    private void init() {
        int i = 0;
        for (Location location : getSpawnLocations()) {
            SkyblockEntity entity = getNewEntity();
            if (canSpawn(location)) {
                entity.spawn(location);
                entity.getEntity().addScoreboardTag("spawnId:" + i);
                entitys.add(entity);
            } else {
                entitys.add(null);
            }
            i++;
        }
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                respawnMissing();
            }
        };
        runnable.runTaskTimer(Main.getMain(), frequence(), frequence());
    }

    public void stop() {
        try {
            runnable.cancel();
        } catch (Exception ignored) {
        }
        for (SkyblockEntity entity : entitys)
            entity.getEntity().remove();
        entitys.clear();
    }

    private SkyblockEntity getNewEntity() {
        try {
            @SuppressWarnings("deprecation") SkyblockEntity e = spawnEntity().newInstance();
            return e;
        } catch (Exception ignored) {
        }
        return null;
    }

    private void respawnMissing() {
        for (int i = 0; i < getSpawnLocations().length; i++) {
            if (entitys.get(i) == null || entitys.get(i).isHasDoneDeath()) {
                SkyblockEntity entity = getNewEntity();
                if (canSpawn(getSpawnLocations()[i])) {
                    entity.spawn(getSpawnLocations()[i]);
                    entity.getEntity().addScoreboardTag("spawnId:" + i);
                    entitys.set(i, entity);
                }
            }
        }
    }

    public static void disable() {
        for (Spawnable spawnable : a) {
            spawnable.stop();
        }
    }

    protected boolean canSpawn(Location location) {
        return true;
    }


    public abstract Location[] getSpawnLocations();

    public abstract long frequence();

    public abstract Class<? extends SkyblockEntity> spawnEntity();

}
