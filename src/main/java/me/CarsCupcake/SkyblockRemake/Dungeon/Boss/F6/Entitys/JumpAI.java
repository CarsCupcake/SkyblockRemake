package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class JumpAI extends BukkitRunnable {
    private final LivingEntity entity;
    public JumpAI(LivingEntity entity){
        this.entity = entity;
    }

    @Override
    public void run() {
        if(entity.isOnGround()){
            boolean isNear = false;
            for (Player p : Bukkit.getOnlinePlayers())
                if(entity.getLocation().distance(p.getLocation()) < 8){
                    isNear = true;
                    break;
                }
            if(!isNear)
                entity.setVelocity(entity.getVelocity().add(new Vector(0,0.42, 0)));
        }
    }
}
