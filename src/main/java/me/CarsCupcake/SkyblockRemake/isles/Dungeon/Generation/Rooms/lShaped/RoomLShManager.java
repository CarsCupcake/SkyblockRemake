package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.lShaped;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.RoomManager;

import java.util.Set;

public class RoomLShManager implements RoomManager {
    @Override
    public IRoom getNewRandom(Set<Integer> taken) {
        return null;
    }

    @Override
    public int getMaxAmount() {
        return 4;
    }
}
