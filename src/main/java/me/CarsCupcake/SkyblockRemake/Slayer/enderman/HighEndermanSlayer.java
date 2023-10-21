package me.CarsCupcake.SkyblockRemake.Slayer.enderman;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public abstract class HighEndermanSlayer extends BeaconThrower {
    protected final Set<NukekubiFixations> heads = new HashSet<>();
    protected BukkitRunnable headRunner;
    public HighEndermanSlayer(SkyblockPlayer player) {
        super(player);
    }
    protected void startNukekubiFixations(){
        headRunner = new BukkitRunnable() {
            int spawnTime = 6;
            @Override
            public void run() {
                if(spawnTime >= 6 && heads.size() < 6){
                    spawnTime = 0;
                    heads.add(new NukekubiFixations(owner, HighEndermanSlayer.this));
                }else
                    spawnTime++;
                Calculator c = new Calculator();
                c.entityToPlayerDamage(HighEndermanSlayer.this, owner, new Bundle<>((int)(2000 * (heads.size()* Math.pow(2, (heads.size()-1)))), 0));
                c.damagePlayer(owner);
                c.showDamageTag(owner);
            }
        };
        headRunner.runTaskTimer(Main.getMain(), 20, 20);
    }
    public void removeHead(NukekubiFixations fixations){
        heads.remove(fixations);
    }
    @Override
    public void kill(){
        super.kill();
        try {
            headRunner.cancel();
        }catch (Exception ignored){}
        new HashSet<>(heads).forEach(NukekubiFixations::remove);
    }
}
