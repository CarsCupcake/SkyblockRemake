package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.AbilityManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LividDaggerability<T extends Event> extends ExtraDamageAbility implements AbilityManager<T> {
    @Override
    public boolean executeAbility(T event) {
        //throw ability
        return false;
    }

    @Override
    public void extraDamageEvent(SkyblockDamageEvent event) {
        //behind hit
        if(event.getType() != SkyblockDamageEvent.DamageType.PlayerToEntity)
            return;
        if(event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK && event.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK)
            return;
        LivingEntity entity = event.getEntity();
        if(isBehind(event.getPlayer(), entity))
            event.getCalculator().damage *= 2;

    }
    public boolean isBehind(SkyblockPlayer player, LivingEntity entity){
        return false;
    }
}
