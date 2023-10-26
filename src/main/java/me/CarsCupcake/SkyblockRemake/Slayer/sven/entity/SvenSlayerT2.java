package me.CarsCupcake.SkyblockRemake.Slayer.sven.entity;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class SvenSlayerT2 extends SvenSlayerT1{
    public SvenSlayerT2(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int getTrueDamage() {
        return 90;
    }

    @Override
    public int getMaxHealth() {
        return 40_000;
    }
}
