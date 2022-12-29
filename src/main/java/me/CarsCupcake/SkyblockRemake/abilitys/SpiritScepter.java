package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.List;

public class SpiritScepter implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        event.setCancelled(true);
        makeBat(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        return false;
    }


    public void makeBat(SkyblockPlayer player){
        SpititBat bat = new SpititBat();
        bat.spawn(player.getEyeLocation());
        new BukkitRunnable(){
            private int i = 0;
            @Override
            public void run() {
                i++;
                if(i > 15*20){
                    bat.kill();
                    cancel();
                }
                Vector dir = player.getLocation().getDirection().clone();
                dir.multiply(0.5);
                Location location = bat.getEntity().getLocation().add(dir);
                location.setYaw(player.getLocation().getYaw());
                location.setPitch(player.getLocation().getPitch());
                bat.getEntity().teleport(location);
                Location loc = bat.getEntity().getLocation();
                if(canExplode(loc, bat.getEntity())){
                    cancel();
                    explode(bat, player);
                }


            }
        }.runTaskTimer(Main.getMain(), 0, 1);
    }
    private boolean canExplode(Location location, LivingEntity self){

        if(!location.getBlock().isPassable()) {
            return true;

        }
        boolean explode = false;
        Collection<Entity> entities = self.getNearbyEntities( 1,1,1);
        for(Entity entity : entities){
            if(entity instanceof LivingEntity && !(entity instanceof Player)&& !(entity instanceof ArmorStand) && !entity.equals(self) && !entity.getScoreboardTags().contains("invinc")) {
                explode = true;
                break;
            }
        }

        return explode;
    }
    private void explode(SpititBat bat, SkyblockPlayer player){
        bat.kill();
        int i = 0;
        double damage = 0;
        player.spawnParticle(Particle.EXPLOSION_HUGE, bat.getEntity().getLocation(), 1);
        player.playSound(bat.getEntity().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1,1);
        Collection<Entity> entities = bat.getEntity().getNearbyEntities(5,5,5);
        for(Entity entity : entities){
            if(entity instanceof LivingEntity && !(entity instanceof  Player) && !entity.equals(bat.getEntity())){
                LivingEntity e = (LivingEntity) entity;
                if(SkyblockEntity.livingEntity.containsKey(e) && SkyblockEntity.livingEntity.get(e) instanceof SpititBat)
                    return;

                i++;
                Calculator calculator = new Calculator();
                calculator.setMagic("Spirit Scepter", 0.2);
                calculator.playerToEntityMagicDamage(player, (LivingEntity) entity, 2000);
                calculator.damageEntity((LivingEntity) entity, player);
                calculator.showDamageTag(entity);
                damage += calculator.damage;
            }
        }
        if(i != 0) {
            Calculator c = new Calculator();
            c.damage = damage;
            c.setMagic("Spirit Scepter");
            c.sendMagicMessage(i, player);
        }
    }
}
