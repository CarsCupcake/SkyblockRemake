package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x4;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.RoomManager;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r1x4.r.QuartsKnight7;

import java.util.List;
import java.util.Set;

public class Room1x4Manager implements RoomManager {
    private static final List<IRoom> room = List.of(new QuartsKnight7());
    @Override
    public IRoom getNewRandom(Set<Integer> taken) {
        return room.get(0);
    }

    @Override
    public int getMaxAmount() {
        return 5;
    }
}
