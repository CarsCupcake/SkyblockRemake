package me.CarsCupcake.SkyblockRemake.Entities;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.utils.Cloneable;

public interface MinionEntity extends Cloneable<SkyblockEntity> {
    String getId();
    SkyblockEntity getInstance();
}
