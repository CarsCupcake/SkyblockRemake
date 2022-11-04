package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class SuperiorBlood implements FullSetBonus {
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public int getPieces() {
        return 4;
    }

    @Override
    public int getMaxPieces() {
        return 4;
    }

    @Override
    public void setPlayer(SkyblockPlayer player) {

    }

    @Override
    public Bonuses getBonus() {
        return Bonuses.SuperiorBlood;
    }
}
