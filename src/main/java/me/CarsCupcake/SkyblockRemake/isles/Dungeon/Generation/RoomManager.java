package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation;

import java.util.Set;

public interface RoomManager {
    IRoom getNewRandom(Set<Integer> taken);
    int getMaxAmount();

}
