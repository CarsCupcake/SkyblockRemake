package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.Objects;

public class MinosChampion extends SkyblockEntity {
    private int maxHealth = 1;
    private final int damage;
    public MinosChampion(ItemRarity rarity) {
        if (Objects.requireNonNull(rarity) == ItemRarity.LEGENDARY) {
            maxHealth = 12_000_000;
            damage = 3_800;
        } else {
            maxHealth = 2_000_000;
            damage = 2_700;
        }
        health = maxHealth;

    }
    @Override
    public int getMaxHealth() {
        return maxHealth;
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
        return damage;
    }
}
