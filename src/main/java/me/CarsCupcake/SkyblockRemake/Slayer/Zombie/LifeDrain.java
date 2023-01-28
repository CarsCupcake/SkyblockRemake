package me.CarsCupcake.SkyblockRemake.Slayer.Zombie;

import com.comphenix.net.bytebuddy.implementation.bind.annotation.SuperCall;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class LifeDrain extends Slayer {
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
        try {
            lifeDrainTimer.cancel();
        }catch (Exception ignored){}
    }

    abstract int healAmount();

    private void heal(){
        int healAmount = healAmount();
        setHealth(Math.min(healAmount + getHealth(), getMaxHealth()));
    }
}

