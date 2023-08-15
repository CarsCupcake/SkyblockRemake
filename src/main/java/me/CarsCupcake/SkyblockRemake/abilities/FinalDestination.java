package me.CarsCupcake.SkyblockRemake.abilities;

import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class FinalDestination implements FullSetBonus {
    private SkyblockPlayer player;
    @Override
    public void start() {
        //TODO: Add Bonus Shit
        System.out.println("start");
    }

    @Override
    public void stop() {
        //TODO: Remove Bonus Shit
        System.out.println("end");
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
        this.player = player;
    }

    @Override
    public Bonuses getBonus() {
        return Bonuses.VivaciousDarkness;
    }

    @Override
    public SetType getSetType() {
        return SetType.Sneak;
    }
}
