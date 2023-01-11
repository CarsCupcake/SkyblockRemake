package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DreadlordHandler implements Listener {
    @EventHandler
    public void skyblockEvent(SkyblockDamageEvent event){
        if(event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE){
            Projectile projectile = event.getProjectile();
            if(projectile.getType() == EntityType.WITHER_SKULL)
                if(projectile.getScoreboardTags().contains("dreadlord")){
                    event.setCancelled(true);
                    Calculator calculator = new Calculator();
                    calculator.setMagic("Dreadlord Sword", 0.3);
                    calculator.playerToEntityMagicDamage(event.getPlayer(), event.getEntity(), 500);
                    calculator.damageEntity(event.getEntity(), event.getPlayer());
                    calculator.sendMagicMessage(1, event.getPlayer());
                    calculator.showDamageTag(event.getEntity());
                }

        }
    }
}
