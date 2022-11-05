package me.CarsCupcake.SkyblockRemake.End.Dragon;

import me.CarsCupcake.SkyblockRemake.End.Dragon.DragonAi.SkyblockDragon;
import org.bukkit.entity.ArmorStand;

public interface CustomDragon {
    SkyblockDragon getSkyblockDragon();
    ArmorStand getFollower();
}
