package me.CarsCupcake.SkyblockRemake.Slayer.Blaze.ItemAbility;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class FiredustDaggerHit implements AbilityManager<DamagePrepairEvent> {
    @Override
    public boolean executeAbility(DamagePrepairEvent event) {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Blaze)
            event.addPostMultiplier(1.2);
        if (entity instanceof PigZombie)
            event.addPostMultiplier(1.1);
        return false;
    }
}
