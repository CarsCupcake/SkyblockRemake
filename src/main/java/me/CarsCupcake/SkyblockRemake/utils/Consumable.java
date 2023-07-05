package me.CarsCupcake.SkyblockRemake.utils;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public interface Consumable {
    boolean has(SkyblockPlayer player);
    void consume(SkyblockPlayer player);
}
