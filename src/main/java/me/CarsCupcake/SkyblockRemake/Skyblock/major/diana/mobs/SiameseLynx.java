package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class SiameseLynx extends SkyblockEntity {
    private int maxHealth = 1;
    private final int damage;
    public SiameseLynx(ItemRarity rarity) {
        switch (rarity) {
            case RARE, EPIC -> {
                maxHealth = 150_000;
                damage = 625;
            }
            case LEGENDARY -> {
                maxHealth = 800_000;
                damage = 2_125;
            }
            default -> {
                maxHealth = 30_000;
                damage = 250;
            }
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
