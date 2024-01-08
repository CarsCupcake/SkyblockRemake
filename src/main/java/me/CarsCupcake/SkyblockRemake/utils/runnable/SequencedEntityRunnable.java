package me.CarsCupcake.SkyblockRemake.utils.runnable;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;

public class SequencedEntityRunnable extends SequencedRunnable {
    private final SkyblockEntity entity;
    public SequencedEntityRunnable(SkyblockEntity entity) {
        this.entity = entity;
    }

    @Override
    public void setCancelled() {
        if (!entity.isHasDoneDeath()) entity.getSequencedRunnables().remove(this);
        super.setCancelled();
    }
}
