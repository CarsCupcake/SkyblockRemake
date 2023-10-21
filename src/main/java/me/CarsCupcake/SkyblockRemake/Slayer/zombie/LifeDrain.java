package me.CarsCupcake.SkyblockRemake.Slayer.zombie;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class LifeDrain extends ZombieSlayer {
    protected BukkitRunnable lifeDrainTimer;
    public LifeDrain(SkyblockPlayer player) {
        super(player);
    }

    @Override @OverridingMethodsMustInvokeSuper
    public void spawn(Location loc) {
        lifeDrainTimer = new BukkitRunnable() {
            @Override
            public void run() {
                heal();
                getEntity().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, getEntity().getLocation().add(0, getEntity().getEyeHeight() / 2, 0),
                        10, 0.5, getEntity().getEyeHeight() / 2, 0.5);
            }
        };
        lifeDrainTimer.runTaskTimer(Main.getMain(), 50, 50);
    }

    @Override @OverridingMethodsMustInvokeSuper
    public void kill() {
        super.kill();
        try {
            lifeDrainTimer.cancel();
        }catch (Exception ignored){}
    }

    protected int healAmount(){
        return getDamage();
    }

    private void heal(){
        int healAmount = healAmount();
        health = Math.min(healAmount + getHealth(), getMaxHealth());
    }
}

