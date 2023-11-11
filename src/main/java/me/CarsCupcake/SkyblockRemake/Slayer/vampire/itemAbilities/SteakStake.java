package me.CarsCupcake.SkyblockRemake.Slayer.vampire.itemAbilities;

import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Slayer.vampire.VampireSlayerT1;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftCalculator;
import me.CarsCupcake.SkyblockRemake.isles.rift.events.RiftDamageEvent;

public class SteakStake implements AbilityManager<RiftDamageEvent> {
    @Override
    public boolean triggerAbility(RiftDamageEvent event) {
        if (event.getCalculator().getAction() != RiftCalculator.Action.PlayerToEntity) return true;
        if (!VampireSlayerT1.vampires.contains(event.getCalculator().getEntity().getClass())) return true;
        if ((double) event.getCalculator().getEntity().getHealth() / event.getCalculator().getEntity().getMaxHealth() <= 0.2) {
            event.getCalculator().heartsDamage = 1000;
            return false;
        }
        return true;
    }
}
