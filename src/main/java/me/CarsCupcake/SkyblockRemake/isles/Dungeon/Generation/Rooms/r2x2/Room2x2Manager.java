package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r2x2;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.RoomManager;

import java.util.Set;

public class Room2x2Manager implements RoomManager {
    @Override
    public IRoom getNewRandom(Set<Integer> taken) {
        return null;
    }

    @Override
    public int getMaxAmount() {
        return 3;
    }
}
