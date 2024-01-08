package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class KuudraEntity extends SkyblockEntity {
    protected final int tier;
    public KuudraEntity(int tier) {
        this.tier = tier;
    }

    @Override
    public int getLevel() {
        return tier * 100;
    }

    public abstract int getTokens();

    @Override
    @OverridingMethodsMustInvokeSuper
    public void kill() {
        super.kill();
    }
}
