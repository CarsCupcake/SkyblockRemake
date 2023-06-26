package me.CarsCupcake.SkyblockRemake.abilities;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.player.PlayerInteractEvent;

public class DreadlordAbility implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean triggerAbility(PlayerInteractEvent event) {
        WitherSkull skull = event.getPlayer().launchProjectile(WitherSkull.class);
        skull.addScoreboardTag("dreadlord");
        return false;
    }

}
