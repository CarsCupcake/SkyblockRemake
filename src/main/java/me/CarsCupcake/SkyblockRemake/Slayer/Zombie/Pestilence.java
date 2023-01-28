package me.CarsCupcake.SkyblockRemake.Slayer.Zombie;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class Pestilence extends LifeDrain{
    public Pestilence(SkyblockPlayer player) {
        super(player);
    }

    @Override @OverridingMethodsMustInvokeSuper
    public void spawn(Location loc) {
        super.spawn(loc);
    }

    @Override @OverridingMethodsMustInvokeSuper
    public void kill() {
        super.kill();
    }
}
