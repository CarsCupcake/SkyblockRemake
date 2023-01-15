package me.CarsCupcake.SkyblockRemake.Slayer.Enderman;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EndermanListener implements Listener {
    public EndermanListener(){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (NukekubiFixations fixations : NukekubiFixations.heads){
                   if (Tools.getNearestEntitysInSight(fixations.getPlayer().getPlayer(), 40).contains(fixations.getEntity())){
                       fixations.addTimerTick();
                   }
                   if(!fixations.isInExpandingPhase()){
                       Location lo =  fixations.getEntity().getLocation().setDirection(fixations.getPlayer().getEyeLocation().clone().subtract( fixations.getEntity().getLocation()).toVector());
                       fixations.getEntity().teleport(lo);
                   }
                }
            }
        }.runTaskTimer(Main.getMain(), 1, 1);
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        for (BeaconThrower.YangGlyph glyph : BeaconThrower.YangGlyph.glyphSet){
            if(glyph.getPlayer().getPlayer() != event.getPlayer())
                continue;
            if(glyph.getBeaconLocation().distance(event.getTo()) < 1){
                glyph.pickUp();
            }
        }
    }
}
