package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.DungeonRoomsTypes;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class Room {
    @Getter
    private final DungeonRoomsTypes type;
    @Getter
    private final boolean isSub;
    private final Location2d location;
    @Getter
    private final Room main;
    private final Set<Room> subRooms = new HashSet<>();
    @Getter
    private IRoom room;
    @Getter
    private int rotation = 0;

    public Room(@NotNull DungeonRoomsTypes type,@NotNull Location2d l, IRoom room, int rotation) {
        this(type, l, false, null);
        this.room = room;
        this.rotation = rotation;
    }

    @Contract("_, _, true, null -> fail")
    public Room(@NotNull DungeonRoomsTypes type,@NotNull Location2d l, boolean isSub,@Nullable Room main) {
        if (isSub && main == null)
            throw new IllegalArgumentException("main room is not allowed to be null when its a subroom!");
        Assert.allNotNull("Elements not allowed to be null!", type, l);
        Assert.isTrue(!l.isOutOfBounds(), "Location " + l + " is out of map!");
        Assert.isTrue(!Generator.getGenerator().getMap().containsKey(l), "Cannot override an other room at " + l + "!");
        this.main = main;
        this.type = type;
        this.isSub = isSub;
        location = l;
        if (isSub) main.addSubRoom(this);

    }

    public Location2d getLocation() {
        return location.clone();
    }

    public void addSubRoom(@NotNull Room r) {
        Assert.isTrue(r.isSub(), "Room is not a subroom!");
        Assert.isTrue(!isSub, "Room has to be Main room to accept subrooms!");

        subRooms.add(r);
    }

    public Set<Room> getSubRooms() {
        Assert.isTrue(!isSub, "Room is not allowed to have subrooms!");
        return new HashSet<>(subRooms);
    }

}
