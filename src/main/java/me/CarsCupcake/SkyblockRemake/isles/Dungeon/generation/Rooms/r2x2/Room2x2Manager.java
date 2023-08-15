package me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r2x2;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.RoomManager;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r2x2.r.MithrilCave;

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
