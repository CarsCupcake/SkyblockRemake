package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class KuudraEntity extends SkyblockEntity {
    public abstract int getTokens();

    @Override
    @OverridingMethodsMustInvokeSuper
    public void kill() {
        super.kill();
    }
}
