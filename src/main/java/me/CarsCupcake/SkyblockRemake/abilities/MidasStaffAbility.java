package me.CarsCupcake.SkyblockRemake.abilities;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MidasStaffAbility implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();
        World w = p.getWorld();
        p.playSound(loc, Sound.ENTITY_ARMOR_STAND_HIT, 1, 1);
        loc.setY(loc.getY() + 3);
        new BukkitRunnable() {
            int i = 9;
            @Override
            public void run() {
                if (i > 0) {
                    Vector v = p.getLocation().getDirection().normalize();
                    double x = v.getX();
                    double z = v.getZ();
                    x = x * -1;
                    Vector newvec = new Vector(z, 0, x);
                    newvec.normalize().multiply(1);
                    v.normalize().multiply(1);
                    loc.add(v);
                    w.spawnParticle(Particle.FLAME, loc.getX(), loc.getY() + 3, loc.getZ(), 2, 0, 0, 0);
                    FallingBlock block = w.spawnFallingBlock(loc, Material.GOLD_BLOCK, (byte) 0);
                    ((CraftEntity) block).getHandle().P = true;
                    loc.add(newvec);
                    w.spawnParticle(Particle.FLAME, loc.getX(), loc.getY() + 3, loc.getZ(), 2, 0, 0, 0);
                    FallingBlock block2 = w.spawnFallingBlock(loc, Material.GOLD_BLOCK, (byte) 0);
                    ((CraftEntity) block2).getHandle().P = true;
                    loc.add(newvec.multiply(-2));
                    w.spawnParticle(Particle.FLAME, loc.getX(), loc.getY() + 3, loc.getZ(), 1, 0, 0, 0);
                    FallingBlock block3 = w.spawnFallingBlock(loc, Material.GOLD_BLOCK, (byte) 0);
                    ((CraftEntity) block3).getHandle().P = true;
                    loc.add(newvec.multiply(-0.5));
                    //ToDo Add damage
                } else {
                    cancel();
                }
                i-=1;
            }
        }.runTaskTimer(Main.getMain(), 0, 2);
        return false;
    }
}



