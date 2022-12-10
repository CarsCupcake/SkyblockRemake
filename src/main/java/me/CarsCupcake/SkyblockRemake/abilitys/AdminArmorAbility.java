package me.CarsCupcake.SkyblockRemake.abilitys;

import me.CarsCupcake.SkyblockRemake.Items.Bonuses;
import me.CarsCupcake.SkyblockRemake.Items.FullSetBonus;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class AdminArmorAbility implements FullSetBonus {
    private SkyblockPlayer player;
    @Override
    public void start() {
        player.setAllowFlight(true);
        player.setFlying(true);
    }

    @Override
    public void stop() {
        player.setFlying(false);
        player.setAllowFlight(false);
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
        return Bonuses.AdminArmor;
    }
}
