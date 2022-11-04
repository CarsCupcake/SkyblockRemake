package me.CarsCupcake.SkyblockRemake.utils.SignGUI;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.core.BlockPosition;
import org.jetbrains.annotations.NotNull;
import java.beans.ConstructorProperties;

public class SignCompleteEvent  {
    private final SkyblockPlayer player;
    private final BlockPosition location;
    private final String[]      lines;




    @ConstructorProperties({"player", "location", "lines"})
    public SignCompleteEvent(SkyblockPlayer player, BlockPosition location, String[] lines)
    {
        this.player = player;
        this.location = location;
        this.lines = lines;
    }

    public final SkyblockPlayer getPlayer()
    {
        return this.player;
    }

    public final BlockPosition getLocation()
    {
        return this.location;
    }

    public final String[] getLines()
    {
        return this.lines;
    }
}
