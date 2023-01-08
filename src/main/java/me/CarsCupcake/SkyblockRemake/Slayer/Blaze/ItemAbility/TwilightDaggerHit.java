package me.CarsCupcake.SkyblockRemake.Slayer.Blaze.ItemAbility;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.SkyblockDamagePlayerToEntityExecuteEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

public class TwilightDaggerHit implements AbilityManager<SkyblockDamagePlayerToEntityExecuteEvent> {
    @Override
    public boolean executeAbility(SkyblockDamagePlayerToEntityExecuteEvent event) {

        return false;
    }

    public static class DaggerHit implements Listener {

        @EventHandler
        public boolean executeAbility(DamagePrepairEvent event) {
            if(event.getPlayer() != null && event.getCalculator().getType() == SkyblockDamageEvent.DamageType.PlayerToEntity && ItemHandler.hasPDC("id", event.getPlayer().getItemInHand(), PersistentDataType.STRING)) {
                if(ItemHandler.getPDC("id", event.getPlayer().getItemInHand(), PersistentDataType.STRING).equals("MAWDUST_DAGGER")){
                    LivingEntity entity = event.getEntity();
                    if (entity instanceof Blaze)
                        event.addPostMultiplier(1.5);
                    if (entity instanceof Skeleton || entity instanceof WitherSkeleton)
                        event.addPostMultiplier(1.2);
                }else if(ItemHandler.getPDC("id", event.getPlayer().getItemInHand(), PersistentDataType.STRING).equals("BURSTMAW_DAGGER")){
                    LivingEntity entity = event.getEntity();
                    if (entity instanceof Blaze)
                        event.addPostMultiplier(2.5);
                    if (entity instanceof Skeleton || entity instanceof WitherSkeleton)
                        event.addPostMultiplier(1.5);
                }else if(ItemHandler.getPDC("id", event.getPlayer().getItemInHand(), PersistentDataType.STRING).equals("HEARTMAW_DAGGER")){
                    LivingEntity entity = event.getEntity();
                    if (entity instanceof Blaze)
                        event.addPostMultiplier(3.5);
                    if (entity instanceof Skeleton || entity instanceof WitherSkeleton)
                        event.addPostMultiplier(2);
                }
            }
            return false;
        }
    }

}
