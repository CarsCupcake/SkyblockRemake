package me.CarsCupcake.SkyblockRemake.elements.impl;

import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.elements.Element;
import me.CarsCupcake.SkyblockRemake.elements.ElementalReaction;
import me.CarsCupcake.SkyblockRemake.utils.Pair;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Overload extends ElementalReaction {
    public Overload(@Nullable LivingEntity applier, @NotNull SkyblockEntity entity) {
        super("§cVaporize", new Pair<>(Element.Electro, Element.Pyro), applier, entity);
        entity.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, entity.getEntity().getLocation(), 1);
        for (Entity e : entity.getEntity().getNearbyEntities(2, 2, 2)){
            if(e instanceof Player p){
                SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
                Calculator calculator = new Calculator();
                if(applier instanceof Player pl) {
                    calculator.playerToEntityMagicDamage(SkyblockPlayer.getSkyblockPlayer(pl), null, dmg(SkyblockPlayer.getSkyblockPlayer(pl).getSkyblockLevel()) * 2);
                }else calculator.playerToEntityMagicDamage(null, null, dmg(player.getSkyblockLevel()) * 2);
                calculator.damagePlayer(player);
            }else if (e instanceof LivingEntity le && SkyblockEntity.livingEntity.exists(le)){
                Calculator c = new Calculator();
                if(applier instanceof Player pl){
                    c.playerToEntityMagicDamage(SkyblockPlayer.getSkyblockPlayer(pl), le, dmg(SkyblockPlayer.getSkyblockPlayer(pl).getSkyblockLevel()) * 2);
                }else c.playerToEntityMagicDamage(null, le, dmg(10) * 2);
                c.damageEntity(le, null, EntityDamageEvent.DamageCause.MAGIC);
            }
        }
    }
    private double dmg(int sbLevel){
        return ((sbLevel - 1) * 2.8) + 34.3;
    }
}
