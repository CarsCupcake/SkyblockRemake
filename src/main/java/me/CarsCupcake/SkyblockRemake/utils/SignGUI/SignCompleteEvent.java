package me.CarsCupcake.SkyblockRemake.utils.SignGUI;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import net.minecraft.core.BlockPosition;

import java.beans.ConstructorProperties;

public record SignCompleteEvent(SkyblockPlayer player, BlockPosition location, String[] lines) {
    @ConstructorProperties({"player", "location", "lines"})
    public SignCompleteEvent {
    }
}
