package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class AspectOfTheEnd implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {

        teleport(event.getPlayer());
        return false;
    }

    public  void teleport(Player player) {
        Location mainLoc = player.getEyeLocation();
        for(int i=1;i<=8*2;i++) {
            Location loc = player.getLocation();
            Vector dir = loc.getDirection();
            dir.normalize();
            dir.multiply(0.5); //1 blocks a way
            mainLoc.add(dir);

            if(mainLoc.getBlock().isEmpty() || mainLoc.getBlock().isLiquid() || mainLoc.getBlock().isPassable()) {
                player.teleport(mainLoc);
            }else break;}}

}
