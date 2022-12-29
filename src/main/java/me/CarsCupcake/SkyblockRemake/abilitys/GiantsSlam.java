package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class GiantsSlam implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        new Sword(event.getPlayer().getTargetBlock(null, 6).getLocation(),SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
        return false;
    }

    private static class Sword extends BukkitRunnable {
        private final Giant giant;

        private Sword(Location location, SkyblockPlayer player){
            List<Entity> e = location.getWorld().getNearbyEntities(location, 4,4,4).stream().filter(entity -> entity instanceof LivingEntity && !(entity instanceof ArmorStand) &&
                    (Main.currentityhealth.containsKey(entity) || SkyblockEntity.livingEntity.containsKey(entity))).toList();
            double dmg = 0;
            int i = 0;
            for(Entity entity : e){
                Calculator c = new Calculator();
                c.setMagic("Giant's Slam", 0.05);
                c.playerToEntityMagicDamage(player, (LivingEntity) entity, 100000);
                c.damageEntity((LivingEntity) entity, player);
                c.showDamageTag(entity);
                dmg += c.damage;
                i++;
            }
            if(i > 0){
                Calculator c = new Calculator();
                c.damage = dmg;
                c.setMagic("Giant's Slam");
                c.sendMagicMessage(i, SkyblockPlayer.getSkyblockPlayer(player));
            }
            this.runTaskLater(Main.getMain(), 100);
            Location pL = player.getLocation().clone();
            pL.setPitch(0);
            pL.setYaw(0);
            pL.subtract(2,1,4);
            pL.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, pL, 1);
            giant = player.getWorld().spawn(pL, Giant.class,g ->{
                g.setAI(false);
                g.setCustomName("Dinnerbone");
                g.setCustomNameVisible(false);
                g.addScoreboardTag("npc");
                g.setInvisible(true);
                g.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
                g.setGravity(false);
            });

        }

        @Override
        public void run() {
            giant.remove();
        }
    }
}
