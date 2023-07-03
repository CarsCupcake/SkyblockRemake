package me.CarsCupcake.SkyblockRemake.abilities;

import java.util.List;

import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;

public class Hyperion_WitherImpact implements AbilityManager<PlayerInteractEvent> {

    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {


        Player player = event.getPlayer();
        double cd = Main.getPlayerStat(SkyblockPlayer.getSkyblockPlayer(player), Stats.CritDamage);
        event.getPlayer().setVelocity(event.getPlayer().getVelocity().setY(0));
        if (event.getPlayer().getEyeLocation().add(0, 1, 0).getBlock().isPassable()) {
            event.getPlayer().teleport(player.getLocation().add(0, 1, 0));
        }
        teleport(event.getPlayer());
        event.getPlayer().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getPlayer().getLocation().add(0, 0.5, 0), 6, 0, 0, 0, 6, null, true);
        if (Main.absorbtionrunntime.getOrDefault(player, 0) == 0) {
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 0.2f, 2f);
            Main.absorbtion.put(player, (int) (cd * 1.5));
            Main.getMain().absorbtioneffect(player, 5);
        }
        Location loc = event.getPlayer().getLocation();
        List<Entity> close = (List<Entity>) event.getPlayer().getWorld().getNearbyEntities(loc, 6, 6, 6);
        close.remove(event.getPlayer());
        int i = 0;
        double damage = 0;
        for (Entity target : close) {
            if (target instanceof LivingEntity && !(target instanceof Player) && target.getType() != EntityType.ARMOR_STAND && !Main.entitydead.containsKey(target)) {
                Calculator calculator = new Calculator();
                calculator.setMagic("Implosion", 0.3);
                calculator.playerToEntityMagicDamage(SkyblockPlayer.getSkyblockPlayer(player), (LivingEntity) target, 10000);
                calculator.damageEntity((LivingEntity) target, SkyblockPlayer.getSkyblockPlayer(player));
                if (calculator.damage > 0) calculator.showDamageTag(target);
                i++;
                damage += calculator.damage;


            }

        }
        if (i > 0) {
            Calculator c = new Calculator();
            c.damage = damage;
            c.setMagic("Implosion");
            c.sendMagicMessage(i, SkyblockPlayer.getSkyblockPlayer(player));
        }
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.4f, 1.2f);
        return false;
    }

    public void teleport(Player player) {
        Location mainLoc = player.getLocation();
        for (int i = 1; i <= 10 * 2; i++) {
            Location loc = player.getLocation();
            Vector dir = loc.getDirection();
            dir.normalize();
            dir.multiply(0.5); //1 blocks a way
            mainLoc.add(dir);

            if (mainLoc.getBlock().isEmpty() || mainLoc.getBlock().isLiquid() || mainLoc.getBlock().isPassable()) {
                player.teleport(mainLoc);
            } else break;
        }
    }


}
