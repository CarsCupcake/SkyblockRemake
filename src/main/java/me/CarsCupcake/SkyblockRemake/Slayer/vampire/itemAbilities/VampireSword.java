package me.CarsCupcake.SkyblockRemake.Slayer.vampire.itemAbilities;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Slayer.vampire.VampireItems;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftCalculator;
import me.CarsCupcake.SkyblockRemake.isles.rift.events.RiftCalculatorEvent;
import me.CarsCupcake.SkyblockRemake.isles.rift.events.RiftDamageEvent;
import me.CarsCupcake.SkyblockRemake.utils.Pair;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.ImmutableTriple;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Triple;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;

public class VampireSword implements Listener {
    private static final Map<String, Triple<Integer, Integer, Double>> stats = Map.of(VampireItems.SilverLacedKarambit.getId(), new ImmutableTriple<>(3, 5, 0.5d),
            VampireItems.SilverTwistKarambit.getId(), new ImmutableTriple<>(5, 6, 0.5d));
    @EventHandler
    public void triggerAbility(RiftDamageEvent event) {
        if (event.getCalculator().getAction() != RiftCalculator.Action.PlayerToEntity) return;
        Triple<Integer, Integer, Double> t = stats.get(ItemHandler.getItemManager(event.getCalculator().getPlayer().getItemInHand()).itemID);
        if (t != null) {
            event.getCalculator().heartsDamage += t.getLeft();
            event.getCalculator().getPlayer().setMana(event.getCalculator().getPlayer().currmana + t.getMiddle());
            event.getCalculator().getPlayer().setHealth(t.getRight() + event.getCalculator().getPlayer().getHealth(), HealthChangeReason.Ability);
        }
    }
}
