package me.CarsCupcake.SkyblockRemake.isles.rift.entitys;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

public abstract class Crux extends RiftEntity {
    protected abstract void onHalfDamage();
    protected boolean isHalfDone = false;
    @Override
    public void damage(double damage, SkyblockPlayer player) {
        super.damage(damage, player);
        if(isHalfDone) return;
        if(health <= getMaxHealth()/2){
            isHalfDone = true;
            onHalfDamage();
        }
    }
}
