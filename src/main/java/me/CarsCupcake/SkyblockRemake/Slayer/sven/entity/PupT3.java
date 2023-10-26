package me.CarsCupcake.SkyblockRemake.Slayer.sven.entity;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;

public class PupT3 extends SkyblockEntity implements Pup{
    private Wolf entity;
    private final SvenSlayerT3 slayer;
    public PupT3(SvenSlayerT3 slayer) {
        this.slayer = slayer;
        spawn(slayer.getEntity().getLocation());
    }
    @Override
    public void remove() {
        SkyblockEntity.killEntity(entity, slayer.getOwner());
    }

    @Override
    public int getMaxHealth() {
        return slayer.getMaxHealth() * 10;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return 90;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Wolf.class, wolf -> {
            wolf.setBaby();
            wolf.setAgeLock(true);
        });
        entity.setCustomNameVisible(true);
        entity.setAngry(true);
    }

    @Override
    public String getName() {
        return "Svens Pup";
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }

    @Override
    public void kill() {
        super.kill();
        slayer.getPups().remove(this);
    }
}
