package me.CarsCupcake.SkyblockRemake.abilities;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import org.bukkit.event.player.PlayerInteractEvent;

public class FlowerOfTruth implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        event.setCancelled(true);
        return false;
    }

}
