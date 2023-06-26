package me.CarsCupcake.SkyblockRemake.isles.rift.entitys;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;

public abstract class RiftEntity extends SkyblockEntity {
    public abstract int getRiftTimeDamage();
    public abstract double getHeartsDamage();
    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }
}
