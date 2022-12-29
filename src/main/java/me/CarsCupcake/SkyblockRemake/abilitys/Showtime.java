package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Showtime implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        new BonzoStaffProjectile(event.getPlayer().getLocation().add(event.getPlayer().getLocation().getDirection().multiply(1)), event.getPlayer());
        return false;
    }

}
