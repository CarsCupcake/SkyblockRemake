package me.CarsCupcake.SkyblockRemake.Items;


import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;



public interface FullSetBonus {
    void start();
    void stop();
    int getPieces();
    int getMaxPieces();
    void setPlayer(SkyblockPlayer player);
    Bonuses getBonus();
    default SetType getSetType() {
        return SetType.Normal;
    }
    default FullSetBonus makeNew(SkyblockPlayer player) {
        FullSetBonus bonus;
        try {
            bonus = getClass().newInstance();
            bonus.setPlayer(player);
            return bonus;
        }catch (Exception ignored){}
        return null;
    }

    enum SetType {
        Normal,
        Sneak
    }

}
