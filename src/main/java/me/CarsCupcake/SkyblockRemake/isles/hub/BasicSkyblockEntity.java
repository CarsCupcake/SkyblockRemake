package me.CarsCupcake.SkyblockRemake.isles.hub;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.ParameterizedType;

public abstract class BasicSkyblockEntity <T extends LivingEntity> extends SkyblockEntity {
    protected T entity;
    protected final Class<T> getEntityType() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, getEntityType(), e -> {
            e.setCustomNameVisible(true);
            e.setRemoveWhenFarAway(false);
            onSpawn(e);
        });
        afterSpawn();
        SkyblockEntity.livingEntity.addEntity(entity, this);
    }
    protected void onSpawn(T entity) {}
    protected void afterSpawn() {}
}
