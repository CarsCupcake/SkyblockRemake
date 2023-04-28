package me.CarsCupcake.SkyblockRemake.Skyblock.projectile;

import me.CarsCupcake.SkyblockRemake.utils.Pair;
import org.bukkit.entity.LivingEntity;

public interface SkyblockProjectile {
    default void onRawHit(LivingEntity event){}
    Pair<Integer> getDamageBundle();
}
