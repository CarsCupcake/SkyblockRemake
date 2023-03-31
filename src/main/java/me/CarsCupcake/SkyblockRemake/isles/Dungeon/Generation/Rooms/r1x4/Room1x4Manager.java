package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x4;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.RoomManager;

import java.util.Set;

public class Room1x4Manager implements RoomManager {
    @Override
    public IRoom getNewRandom(Set<Integer> taken) {
        return null;
    }

    @Override
    public int getMaxAmount() {
        return 5;
    }
}
