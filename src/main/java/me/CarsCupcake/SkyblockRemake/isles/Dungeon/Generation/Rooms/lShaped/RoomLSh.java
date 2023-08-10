package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Rooms.lShaped;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.IRoom;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Location2d;

import java.util.Set;

public abstract class RoomLSh extends IRoom {
    @Override
    public Set<Location2d> getNextLocations(Location2d base, int rotation) {
       //TODO: FINISH!
        throw new  UnsupportedOperationException("Not Done");
    }

    @Override
    public void place(Location2d location2d, int rotation) {
        super.place(location2d, baseRotation());
    }
}
