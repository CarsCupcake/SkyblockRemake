package me.CarsCupcake.SkyblockRemake.Slayer.sven.entity;

public class PupT4 extends PupT3{
    public PupT4(SvenSlayerT3 slayer) {
        super(slayer);
    }

    @Override
    public int getMaxHealth() {
        return 200_000;
    }

    @Override
    public int getDamage() {
        return 220;
    }
}
