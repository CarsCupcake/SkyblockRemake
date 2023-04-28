package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.boss;

import lombok.Getter;
import lombok.Setter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

import java.util.HashMap;

public abstract class AbstracCrimsonIsleBoss extends SkyblockEntity {
    public static final HashMap<Class<? extends AbstracCrimsonIsleBoss>, AbstracCrimsonIsleBoss> onRespawn = new HashMap<>();
    private final Location location;
    @Getter
    @Setter
    private boolean isRespawned = false;
    public AbstracCrimsonIsleBoss(Location spawning){
        location = spawning;
        new BukkitRunnable(){
            public void run(){
                spawn(location);
            }
        }.runTaskLater(Main.getMain(), 10);
    }
    @MustBeInvokedByOverriders
    @Override
    public void kill(){
        //TODO: add loottable and loot!
        super.kill();
        onRespawn.put(this.getClass(), this);
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!isRespawned) {
                    getNew(location);
                    onRespawn.remove(AbstracCrimsonIsleBoss.this.getClass());
                }
            }
        }.runTaskLater(Main.getMain(), 20*60);
    }
    protected abstract void getNew(Location l);
    public void respawn(){
        isRespawned = true;
        getNew(location);
        onRespawn.remove(AbstracCrimsonIsleBoss.this.getClass());
    }
}
