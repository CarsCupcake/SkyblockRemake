package me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.lShaped;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.RoomManager;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.lShaped.r.Melon7;

import java.util.List;
import java.util.Set;

public class RoomLShManager implements RoomManager {
    private static final List<IRoom> room = List.of(new Melon7());
    @Override
    public IRoom getNewRandom(Set<Integer> taken) {
        return room.get(0);
    }

    @Override
    public int getMaxAmount() {
        return 4;
    }
}
