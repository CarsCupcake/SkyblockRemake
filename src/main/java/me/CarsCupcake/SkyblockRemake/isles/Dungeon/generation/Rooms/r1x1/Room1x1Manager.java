package me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r1x1;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.RoomManager;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation.Rooms.r1x1.r.Andeside2;

import java.util.List;
import java.util.Set;

public class Room1x1Manager implements RoomManager {
    private static final List<IRoom> room = List.of(new Andeside2());
    @Override
    public IRoom getNewRandom(Set<Integer> taken) {
        return room.get(0);
    }

    @Override
    public int getMaxAmount() {
        return 21;
    }
}
