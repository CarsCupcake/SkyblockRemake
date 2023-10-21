package me.CarsCupcake.SkyblockRemake.Slayer.enderman;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NukekubiFixations {
    static Set<NukekubiFixations> heads = new HashSet<>();
    @Getter
    private final SkyblockPlayer player;
    private int timer = 0;
    private final HighEndermanSlayer slayer;
    @Getter
    private boolean inExpandingPhase = true;
    private final ArmorStand stand;
    public NukekubiFixations(SkyblockPlayer player, HighEndermanSlayer slayer){
        this.player = player;
        this.slayer = slayer;
        heads.add(this);
        stand = slayer.getEntity().getEyeLocation().getWorld().spawn(slayer.getEntity().getEyeLocation(), ArmorStand.class, s -> {
            s.setInvisible(true);
            s.setInvulnerable(true);
            s.setGravity(false);
            s.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/edb0b13de6139130cb5d3523e8acc4fafca10a1216a495378bc9e1d298ff9af9"));
        });
        Location l = stand.getLocation().clone();
        l.setYaw(new Random().nextInt(361));
        stand.teleport(l);
        new BukkitRunnable() {
            double movement = 0.4;
            @Override
            public void run() {
                if(stand == null || stand.isDead()){
                    cancel();
                    return;
                }

                movement -= 0.01;

                if(movement <= 0){
                    cancel();
                    inExpandingPhase = false;
                    return;
                }

                Location l = stand.getLocation();
                l.add(stand.getLocation().getDirection().multiply(movement));
                if(!l.clone().add(0, stand.getEyeHeight(), 0).getBlock().isPassable()){
                    cancel();
                    inExpandingPhase = false;
                    return;
                }

                stand.teleport(l);
            }
        }.runTaskTimer(Main.getMain(), 1, 1);
    }
    public Entity getEntity(){
        return stand;
    }
    public void remove(){
        heads.remove(this);
        slayer.removeHead(this);
        stand.remove();
    }
    public void addTimerTick(){
        timer++;
        if(timer >= 30){
            heads.remove(this);
            slayer.removeHead(this);
            stand.remove();
            stand.getWorld().spawnParticle(Particle.FLASH, stand.getEyeLocation(), 1);
        }
    }
}
