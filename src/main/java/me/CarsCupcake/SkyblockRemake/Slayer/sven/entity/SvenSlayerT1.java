package me.CarsCupcake.SkyblockRemake.Slayer.sven.entity;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Slayer;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SvenSlayerT1 extends Slayer {
    protected static final double MOVEMENT_SPEED = 0.445;
    private Wolf entity;
    public SvenSlayerT1(SkyblockPlayer player) {
        super(player);
    }

    @Override
    public int getMaxHealth() {
        return 2000;
    }

    @Override
    public LivingEntity getEntity() {

        return entity;
    }

    @Override
    public int getDamage() {
        return 60;
    }

    @Override
    public void spawn(@NotNull Location loc) {
        entity = loc.getWorld().spawn(loc, Wolf.class);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(MOVEMENT_SPEED);
        entity.setAngry(true);
        entity.setCustomNameVisible(true);
    }

    @Override
    public String getName() {
        return "Sven Packmaster";
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }
}
