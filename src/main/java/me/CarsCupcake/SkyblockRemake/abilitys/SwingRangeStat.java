package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.entity.LivingEntity;
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
        if(swingrange == 0)
            return;
        if(event.getAction() == Action.PHYSICAL)
            return;
        if(event.getAction() != Action.LEFT_CLICK_AIR)
            return;
        RayTraceResult traceResult = player.getWorld().rayTraceEntities(player.getLocation().add(player.getLocation().getDirection().multiply(1)), player.getLocation().getDirection(), swingrange - 1, 2);
        if(traceResult == null)
            return;
        if(traceResult.getHitEntity() == null || !(traceResult.getHitEntity() instanceof LivingEntity entity))
                return;

        if(player.getLocation().distance(entity.getLocation()) > swingrange && player.getLocation().distance(entity.getEyeLocation()) > swingrange &&
                player.getEyeLocation().distance(entity.getLocation()) > swingrange && player.getEyeLocation().distance(entity.getEyeLocation()) > swingrange)
            return;

        entity.damage(1, player);
    }
}
