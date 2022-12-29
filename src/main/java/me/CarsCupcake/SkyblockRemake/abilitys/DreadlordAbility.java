package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class DreadlordAbility implements AbilityManager<PlayerInteractEvent> {
    @Override
    public boolean executeAbility(PlayerInteractEvent event) {
        WitherSkull skull = event.getPlayer().launchProjectile(WitherSkull.class);
        skull.addScoreboardTag("dreadlord");
        return false;
    }

}
