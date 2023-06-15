package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Items.AbilityPreExecuteEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

public class TeleportAndKillAbility extends PreAbilityExecution implements AbilityManager<PlayerInteractEvent>, Listener {


    private static final double DAMAGE = 20000;
    private static final double TELEPORT_DISTANCE = 1.0;
    private static final double TELEPORT_HEIGHT_OFFSET = 0.5;

    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location startLoc = player.getEyeLocation();
        Vector direction = startLoc.getDirection();

        LivingEntity target = getTargetedEntity(player);
        if (target != null) {
            teleportAndKill(player, target);
            return true;
        }

        return false;
    }

    private LivingEntity getTargetedEntity(Player player) {
        Location startLoc = player.getEyeLocation();
        Vector direction = startLoc.getDirection();
        double maxDistance = 50.0;
        Location targetLoc = startLoc.clone();
        for (double distance = 0; distance <= maxDistance; distance += 0.1) {
            targetLoc.add(direction);
            Entity target = getEntityAtLocation(targetLoc);
            if (target instanceof LivingEntity) {
                return (LivingEntity) target;
            }
            if (!targetLoc.getBlock().isPassable()) {
                break;
            }
        }
        return null;
    }

    private Entity getEntityAtLocation(Location location) {
        for (Entity entity : location.getWorld().getNearbyEntities(location, 1.0, 1.0, 1.0)) {
            if (entity instanceof LivingEntity && !(entity instanceof Player)) {
                return entity;
            }
        }
        return null;
    }

    private void teleportAndKill(Player player, LivingEntity target) {
        Location targetLoc = target.getLocation().add(target.getVelocity().multiply(TELEPORT_DISTANCE));
        Block targetBlock = targetLoc.getBlock();

        if (!targetBlock.isPassable()) {
            targetLoc.add(0, TELEPORT_HEIGHT_OFFSET, 0);
            targetBlock = targetLoc.getBlock();
            if (!targetBlock.isPassable()) {
                return;
            }
        }

        Calculator calculator = new Calculator();
        calculator.setMagic("ShockScythe", 0.07);
        calculator.playerToEntityMagicDamage(SkyblockPlayer.getSkyblockPlayer(player), target, DAMAGE);
        calculator.damageEntity(target, SkyblockPlayer.getSkyblockPlayer(player));

        player.teleport(targetLoc);

        if (calculator.damage > 0) {
            calculator.showDamageTag(target);
            calculator.sendMagicMessage(1, SkyblockPlayer.getSkyblockPlayer(player));
        }

        target.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, targetLoc, 1);
        target.getWorld().playSound(targetLoc, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
    }

    @Override
    public void preEvent(AbilityPreExecuteEvent event) {
        System.out.println("Triggered");
        Entity e = getTargetedEntity(event.getPlayer());
        System.out.println(e);
        if (e == null) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§cNo Target!");
        }
    }
}
