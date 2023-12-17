package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.Objects;

public class MinosInquisitor extends SkyblockEntity {
    @Override
    public int getMaxHealth() {
        return 40_000_000;
    }

    @Override
    public LivingEntity getEntity() {
        return null;
    }

    @Override
    public void spawn(Location loc) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getDamage() {
        return 850;
    }
}
