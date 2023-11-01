package me.CarsCupcake.SkyblockRemake.Slayer.zombie;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class Pestilence extends LifeDrain{
    private BukkitRunnable runnable;
    public Pestilence(SkyblockPlayer player) {
        super(player);
    }

    @Override @OverridingMethodsMustInvokeSuper
     public void spawn(@NotNull Location loc) {
        super.spawn(loc);
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(owner.getLocation().distance(getEntity().getLocation()) <= 8.5){
                    Calculator c = new Calculator();
                    c.entityToPlayerDamage(Pestilence.this, owner);
                    c.damagePlayer(owner);
                    c.showDamageTag(owner);
                }
            }
        };
        runnable.runTaskTimer(Main.getMain(), 20, 20);
    }

    @Override @OverridingMethodsMustInvokeSuper
    public void kill() {
        super.kill();
        runnable.cancel();
    }
}
