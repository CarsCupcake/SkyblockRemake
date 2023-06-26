package me.CarsCupcake.SkyblockRemake.abilities;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import org.bukkit.event.player.PlayerInteractEvent;

public class Showtime implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        new BonzoStaffProjectile(event.getPlayer().getLocation().add(event.getPlayer().getLocation().getDirection().multiply(1)), event.getPlayer());
        return false;
    }

}
