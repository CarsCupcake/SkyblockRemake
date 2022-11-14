package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys.Golem;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys.TerraTransition;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys.Terracotta;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftZombie;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Phase1 {
    private static Phase1 instance;
    private static final Location[] terraLocs = {new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 23.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 24.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 25.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 27.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 29.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 31.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 33.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 35.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 37.5),new Location(Main.getMain().getServer().getWorld("world"), -5.5, 69.0, 39.5)};
    private static final Location[] golemLocs = {new Location(Main.getMain().getServer().getWorld("world"), -1.5, 69.0, 52.5),new Location(Main.getMain().getServer().getWorld("world"), 4.5, 69.0, 66.5),new Location(Main.getMain().getServer().getWorld("world"), -1.5, 69.0, 80.5),new Location(Main.getMain().getServer().getWorld("world"), -15.5, 69.0, 80.5),new Location(Main.getMain().getServer().getWorld("world"), -21.5, 69.0, 66.5),new Location(Main.getMain().getServer().getWorld("world"), -15.5, 69.0, 52.5)};
    public final ArrayList<Terracotta> terracottas = new ArrayList<>();
    public final ArrayList<TerraTransition> dead = new ArrayList<>();
    private final BukkitRunnable timerRunner;
    private final BossBar bar;
    private int timer = 120;
    public Phase1(){
        instance = this;
        init();
        bar = Bukkit.createBossBar("§cSadan's Interest Level", BarColor.PURPLE, BarStyle.SOLID);
        for (Player p : Bukkit.getOnlinePlayers())
            bar.addPlayer(p);

        timerRunner = new BukkitRunnable() {
            @Override
            public void run() {
                reduceBy1();
            }
        };
        timerRunner.runTaskTimer(Main.getMain(), 20, 20);
    }
    private void reduceBy1(){
        timer--;
        if(timer <= 0){
            phaseSwitch();
            timerRunner.cancel();
        }else
            bar.setProgress(timer/120d);
    }
    private void phaseSwitch(){
        bar.removeAll();
        for(Terracotta t : terracottas) {
            t.getEntity().remove();
            t.getEntity().getWorld().spawnParticle(Particle.ASH, t.getEntity().getLocation(),10, 5,5,5);
        }
        terracottas.clear();
        for (TerraTransition t : dead) {
            t.remove();
            t.getL().getWorld().spawnParticle(Particle.ASH, t.getL(),10, 5,5,5);
        }
        new Phase2();
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
    public void terracottaKill(Terracotta terracotta){
        reduceBy1();
        if(timer > 0) {
            terracottas.remove(terracotta);
            dead.add(new TerraTransition(terracotta.getEntity().getLocation()));
        }
    }

}
