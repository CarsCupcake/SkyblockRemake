package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class F7Phase2 implements Listener {
    //Storm spawn: 74.5 189 54
    private final Storm storm;
    public F7Phase2(Storm storm) {
        this.storm = storm;
        Main.getMain().getServer().getPluginManager().registerEvents(new Pillar(), Main.getMain());

    }

    @EventHandler
    public void tntExplode(EntityExplodeEvent event) {
        System.out.println(event.getEntity().getScoreboardTags());
        event.setCancelled(true);
        if(event.getEntity() instanceof TNTPrimed && event.getEntity().getScoreboardTags().contains("storm")){

            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().add(0, 0.5, 0), 6, 0, 0, 0, 6, null, true);
            for(Player p : Bukkit.getOnlinePlayers())
                p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
            List<Entity> entities = event.getEntity().getNearbyEntities(10,10,10).stream().filter(entity -> entity instanceof Player).toList();
            if(!entities.isEmpty()){
                for(Entity entity : entities){
                    Player player = (Player) entity;
                    player.damage(0.1);


                    Calculator calc = new Calculator();
                    calc.entityToPlayerDamage(storm, SkyblockPlayer.getSkyblockPlayer(player));
                    calc.damagePlayer(SkyblockPlayer.getSkyblockPlayer(player));


                }
            }

        }


    }
    @EventHandler
    public void fireballHit(ProjectileHitEvent event){
        if(event.getEntity().getType() == EntityType.FIREBALL && event.getEntity().getScoreboardTags().contains("storm")&& event.getHitBlock() != null){
            event.getEntity().getWorld().spawn(event.getEntity().getLocation(), LightningStrike.class);
            List<Entity> entities = event.getEntity().getNearbyEntities(4,4,4).stream().filter(e -> e instanceof Player).toList();

            for (Entity entity : entities){
                Player player = (Player) entity;
                player.damage(0.1);
                player.setVelocity(player.getVelocity().add(genVec(event.getEntity().getLocation(),entity.getLocation()).multiply(1)));

                if(!player.isOp())
                {
                    Calculator calc = new Calculator();
                    calc.entityToPlayerDamage(storm, SkyblockPlayer.getSkyblockPlayer(player));
                    calc.damagePlayer(SkyblockPlayer.getSkyblockPlayer(player));
                }

            }
        }
    }

    private Vector genVec(Location a, Location b) {
        double dX = a.getX() - b.getX();
        double dY = a.getY() - b.getY();
        double dZ = a.getZ() - b.getZ();
        double yaw = Math.atan2(dZ, dX);
        double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
        double x = Math.sin(pitch) * Math.cos(yaw);
        double y = Math.sin(pitch) * Math.sin(yaw);
        double z = Math.cos(pitch);

        Vector vector = new Vector(x, z, y);
        //If you want to: vector = vector.normalize();

        return vector;
    }
}
