package me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation;


import java.util.Set;

public interface RoomManager {
    IRoom getNewRandom(Set<Integer> taken);
    int getMaxAmount();
}
