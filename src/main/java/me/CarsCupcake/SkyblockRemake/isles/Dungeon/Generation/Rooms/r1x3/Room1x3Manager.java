package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x3;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.RoomManager;

import java.util.Set;

public class Room1x3Manager implements RoomManager {
    @Override
    public IRoom getNewRandom(Set<Integer> taken) {
        return null;
    }

    @Override
    public int getMaxAmount() {
        return 3;
    }
}
