package me.CarsCupcake.SkyblockRemake.Dungeon.Generation;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Dungeon.DungeonRoomsTypes;

public class Room {
    @Getter
    private final DungeonRoomsTypes type;
    @Getter
    private final boolean isSub;
    @Getter
    private final Location location;
    public Room(DungeonRoomsTypes type, Location l){
        this(type, l, false);
    }
    public Room(DungeonRoomsTypes type, Location l, boolean isSub){
        this.type = type;
        this.isSub = isSub;
        location = l;
    }


}
