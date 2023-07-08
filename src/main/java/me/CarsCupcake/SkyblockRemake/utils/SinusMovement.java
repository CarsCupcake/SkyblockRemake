package me.CarsCupcake.SkyblockRemake.utils;

import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * is used to make an entitys y move like a sin funktion
 *
 * @param strech
 */
public record SinusMovement(double strech, double maxHeight, double add) {
    public void move(Entity e, Runnable runnable, int ticks, int staringTick, Location start, Location finish) {
        move(e, runnable, ticks, staringTick, start, finish, null);
    }
    public void move(Entity e, Runnable runnable, int ticks, int staringTick, Location start, Location finish, Runnable tick) {
        new BukkitRunnable() {
            int i = staringTick;
            final double y = e.getLocation().getY();
            final Vector dir = finish.toVector().subtract(start.toVector()).normalize().multiply(start.distance(finish)/(ticks - staringTick));
            @Override
            public void run() {
                i++;
                if(ticks < i) cancel();
                Location l = e.getLocation();
                l.add(dir);
                l.setY(y + calculateOffset(i, ticks));
                e.teleport(l);
                if(tick != null) tick.run();
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                if(runnable != null) runnable.run();
            }
        }.runTaskTimer(Main.getMain(), 1, 1);
    }

    public double calculateOffset(int tick, int animationLenght) {
        return useFunction(((((double) tick) / ((double) animationLenght))) * Math.PI);
    }

    private double useFunction(double val) {
        return maxHeight * Math.sin(val * strech) + add;
    }
}
