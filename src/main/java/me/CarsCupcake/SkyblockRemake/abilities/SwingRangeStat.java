package me.CarsCupcake.SkyblockRemake.abilities;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;

public class SwingRangeStat implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(event.getPlayer());
        double swingrange = Main.getPlayerStat(player, Stats.SwingRange);
        if(swingrange <= 3)
            return;
        if(event.getAction() == Action.PHYSICAL)
            return;
        if(event.getAction() != Action.LEFT_CLICK_AIR)
            return;
        RayTraceResult traceResult = player.getWorld().rayTraceEntities(player.getLocation().add(player.getLocation().getDirection().multiply(1)), player.getLocation().getDirection(), swingrange - 1, 2, entity -> !isAllowed(entity));
        if(traceResult == null)
            return;
        if(traceResult.getHitEntity() == null || !(traceResult.getHitEntity() instanceof LivingEntity entity))
                return;

        if(player.getLocation().distance(entity.getLocation()) > swingrange && player.getLocation().distance(entity.getEyeLocation()) > swingrange &&
                player.getEyeLocation().distance(entity.getLocation()) > swingrange && player.getEyeLocation().distance(entity.getEyeLocation()) > swingrange)
            return;
        if (traceResult.getHitEntity().getType() == EntityType.ARMOR_STAND) return;
        //TODO Make New (swingrange stat is broken)
        Calculator calculator = new Calculator();
        calculator.playerToEntityDamage((LivingEntity) traceResult.getHitEntity(), player);
        calculator.damageEntity(entity, player);
        calculator.showDamageTag(entity);

        entity.damage(0.1);
    }
    private boolean isAllowed(Entity entity) {
        if (!(entity instanceof LivingEntity)) return false;
        if (entity.getType() == EntityType.ARMOR_STAND) return false;
        SkyblockEntity e = SkyblockEntity.livingEntity.getSbEntity(entity);
        return !e.isIgnored();
    }
}
