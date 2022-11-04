package me.CarsCupcake.SkyblockRemake.Items;


import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;



public interface FullSetBonus {
    void start();
    void stop();
    int getPieces();
    int getMaxPieces();
    void setPlayer(SkyblockPlayer player);
    Bonuses getBonus();

}
