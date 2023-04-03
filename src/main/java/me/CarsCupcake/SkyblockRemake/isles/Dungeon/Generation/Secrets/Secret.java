package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Secrets;

import me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation.Room;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import org.jetbrains.annotations.NotNull;

public abstract class Secret {
    protected final Room room;
    public Secret(@NotNull Room room){
        Assert.notNull(room);
        Assert.isTrue(!room.isSub(), "Cannot use subrooms!");
        this.room = room;
    }
    public abstract boolean isCompleted();
    public final Room getRoom(){
        return room;
    }
}
