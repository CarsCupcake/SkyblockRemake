package me.CarsCupcake.SkyblockRemake.utils.runnable;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class EntityRunnable extends BukkitRunnable {
    public static final HashMap<SkyblockEntity, Set<EntityRunnable>> runnable = new HashMap<>();
    private SkyblockEntity entity;

    @NotNull
    public synchronized BukkitTask runTaskTimer(@NotNull SkyblockEntity entity, long delay, long period) throws IllegalArgumentException, IllegalStateException {
        addToMap(entity);
        return super.runTaskTimer(Main.getMain(), delay, period);
    }

    @NotNull
    public synchronized BukkitTask runTaskLater(@NotNull SkyblockEntity entity, long delay) throws IllegalArgumentException, IllegalStateException {
        addToMap(entity);
        return super.runTaskLater(Main.getMain(), delay);
    }

    @NotNull
    public synchronized BukkitTask runTaskTimerAsynchronously(@NotNull SkyblockEntity entity, long delay, long period) throws IllegalArgumentException, IllegalStateException {
        addToMap(entity);
        return super.runTaskTimerAsynchronously(Main.getMain(), delay, period);
    }

    @NotNull
    public synchronized BukkitTask runTaskLaterAsynchronously(@NotNull SkyblockEntity entity, long delay) throws IllegalArgumentException, IllegalStateException {
        addToMap(entity);
        return super.runTaskLaterAsynchronously(Main.getMain(), delay);
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        if (entity != null) {
            if (runnable.containsKey(entity)) {
                Set<EntityRunnable> entityRunnables = runnable.get(entity);
                entityRunnables.remove(this);
                if (entityRunnables.isEmpty()) runnable.remove(entity);
                else runnable.put(entity, entityRunnables);
            }
        }
        super.cancel();
    }

    private void addToMap(SkyblockEntity entity) {
        this.entity = entity;
        Set<EntityRunnable> entityRunnables;
        if (runnable.containsKey(entity)) entityRunnables = runnable.get(entity);
        else entityRunnables = new HashSet<>();
        entityRunnables.add(this);
        runnable.put(entity, entityRunnables);
    }

    public static void remove(SkyblockEntity entity) {
        if (!runnable.containsKey(entity)) return;
        Set<EntityRunnable> entityRunnables = runnable.get(entity);
        runnable.remove(entity);
        for (EntityRunnable r : entityRunnables) {
            try {
                r.cancel();
            } catch (Exception ignored) {}
        }

    }
}
