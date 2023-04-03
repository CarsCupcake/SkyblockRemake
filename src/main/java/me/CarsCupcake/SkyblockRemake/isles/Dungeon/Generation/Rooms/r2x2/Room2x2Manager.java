package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r2x2;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.RoomManager;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.r2x2.r.MithrilCave;

import java.util.List;
import java.util.Set;

public class Room2x2Manager implements RoomManager {
    private static final List<IRoom> room = List.of(new MithrilCave());
    @Override
    public IRoom getNewRandom(Set<Integer> taken) {
        return room.get(0);
    }

    @Override
    public int getMaxAmount() {
        return 3;
    }
}
