package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys;

import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.TreeMap;

public class JumpAI extends BukkitRunnable {
    private int cooldown = 0;
    private final LivingEntity entity;
    public JumpAI(LivingEntity entity){
        this.entity = entity;
        this.runTaskTimer(Main.getMain(), 0 ,1);
    }

    @Override
    public void run() {
        if(entity.isOnGround()){
            cooldown--;
            boolean isNear = false;
            TreeMap<Double, Player> locs = new TreeMap<>();
            for (Player p : Bukkit.getOnlinePlayers())
                if(entity.getLocation().distance(p.getLocation()) < 8){
                    isNear = true;

                }else locs.put(entity.getLocation().distance(p.getLocation()), p);
            if(cooldown <= 0)
               if(!isNear) {
                   Location l = locs.firstEntry().getValue().getLocation();
                   Vector dir = l.toVector().subtract(entity.getLocation().toVector()).normalize();
                entity.setVelocity(entity.getVelocity().add(new Vector(0, 0.42, 0).add(dir.multiply(0.6))));
                cooldown = 5;
            }
        }
    }
}
