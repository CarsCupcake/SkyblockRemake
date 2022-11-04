package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class FlowerOfTruth implements AbilityManager {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        event.setCancelled(true);
        return false;
    }

    @Override
    public boolean executeAbility(EntityDamageByEntityEvent event) {
        return false;
    }
}
