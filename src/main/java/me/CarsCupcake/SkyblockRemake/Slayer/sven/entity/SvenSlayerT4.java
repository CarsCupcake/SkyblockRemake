package me.CarsCupcake.SkyblockRemake.Slayer.sven.entity;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public class SvenSlayerT4 extends SvenSlayerT3 {
    public SvenSlayerT4(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getTrueDamage() {
        return 200;
    }

    @Override
    public int getDamage() {
        return 440;
    }

    @Override
    public int getMaxHealth() {
        return 2_000_000;
    }

    @Override
    protected Pup makeNew() {
        return new PupT4(this);
    }
}
