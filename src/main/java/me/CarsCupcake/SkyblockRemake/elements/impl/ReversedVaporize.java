package me.CarsCupcake.SkyblockRemake.elements.impl;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.elements.Element;
import me.CarsCupcake.SkyblockRemake.elements.ElementalReaction;
import me.CarsCupcake.SkyblockRemake.utils.Pair;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReversedVaporize extends ElementalReaction {
    public ReversedVaporize(@Nullable LivingEntity applier, @NotNull SkyblockEntity entity, DamagePrepairEvent event) {
        super("§cVaporize", new Pair<>(Element.Hydro, Element.Pyro), applier, entity);
        if(event != null) event.addPostMultiplier(1.5);
        entity.getEntity().getWorld().spawnParticle(Particle.SMOKE_NORMAL, entity.getEntity().getLocation(), 1);
    }
}
