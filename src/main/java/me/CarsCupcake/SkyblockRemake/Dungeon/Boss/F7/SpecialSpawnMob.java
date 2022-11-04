package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SpecialSpawnMob {
    private final ArmorStand stand;
    private final Class<? extends SkyblockEntity> entity;

    private final Particle.DustOptions color1 = new Particle.DustOptions(Color.GREEN,1);
    private final Particle.DustOptions color2 = new Particle.DustOptions(Color.GRAY,1);
    private final Particle.DustOptions color3 = new Particle.DustOptions(Color.PURPLE,1);
    public SpecialSpawnMob(Vector vec, Class<? extends SkyblockEntity> entity, Location start) {
        this.entity = entity;
        stand = Bukkit.getWorld("world").spawn(start,ArmorStand.class,s ->{
           s.setInvisible(true);
           s.setInvulnerable(true);
        });
        stand.setVelocity(vec);
        armorStandChecker();

    }
    private void armorStandChecker(){
        new BukkitRunnable() {
            @Override
            public void run() {
                stand.getWorld().spawnParticle(Particle.SPELL_MOB,stand.getEyeLocation(),2);


                stand.getWorld().spawnParticle(Particle.REDSTONE,stand.getEyeLocation(),1, color1);
                stand.getWorld().spawnParticle(Particle.REDSTONE,stand.getEyeLocation(),1, color2);
                stand.getWorld().spawnParticle(Particle.REDSTONE,stand.getEyeLocation(),1, color3);

                if(stand.isOnGround()){
                    try {
                        @SuppressWarnings("deprecation")
                        SkyblockEntity e =  entity.newInstance();
                        e.spawn(stand.getLocation());
                    } catch (Exception ignored) {

                    }
                    stand.remove();
                    cancel();
                }
            }
        }.runTaskTimer(Main.getMain(), 0,1);
    }
}
