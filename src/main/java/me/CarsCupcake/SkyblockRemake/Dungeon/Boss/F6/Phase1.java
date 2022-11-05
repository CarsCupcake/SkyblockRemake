package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys.Golem;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys.Terracotta;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftZombie;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Phase1 {
    private static Phase1 instance;
    private static final Location[] terraLocs = {new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 23.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 24.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 25.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 27.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 29.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 31.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 33.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 35.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 37.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 39.5)};
    private static final Location[] golemLocs = {new Location(Main.getMain().getServer().getWorld("world"), -1.5, 69.0, 52.5),new Location(Main.getMain().getServer().getWorld("world"), 4.5, 69.0, 66.5),new Location(Main.getMain().getServer().getWorld("world"), -1.5, 69.0, 80.5),new Location(Main.getMain().getServer().getWorld("world"), -15.5, 69.0, 80.5),new Location(Main.getMain().getServer().getWorld("world"), -21.5, 69.0, 66.5),new Location(Main.getMain().getServer().getWorld("world"), -15.5, 69.0, 52.5)};
    private final ArrayList<Terracotta> terracottas = new ArrayList<>();
    public Phase1(){
        instance = this;
        init();
    }
    private void init(){
        for(Location loc : terraLocs){
            Location l1 = loc.clone();
            l1.setYaw(90);
            Terracotta t = new Terracotta();
            t.spawn(l1);
            t.getEntity().setAI(false);
            t.setImune(true);
            terracottas.add(t);
            Location l2 = loc.clone().add(-6, 0, 0);
            l2.setYaw(-90);
            t = new Terracotta();
            t.spawn(l2);
            t.getEntity().setAI(false);
            t.setImune(true);
            terracottas.add(t);

        }
        for(Location location : golemLocs){
            Golem g = new Golem();
            g.spawn(location.clone());
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Terracotta terracotta : terracottas){
                    terracotta.setImune(false);
                    terracotta.getEntity().setAI(true);
                    ((CraftZombie)terracotta.getEntity()).getHandle().setJumping(true);
                    Vector v = new Vector(0,0.42, 0);
                    terracotta.getEntity().setVelocity(v);
                }
            }
        }.runTaskLater(Main.getMain(), 100);
    }

    @NotNull
    public static Phase1 getPhase(){
        if(instance == null)
            throw new IllegalArgumentException("Phase1 is null");
        return instance;
    }
    public void terracottaKill(){

    }

}
