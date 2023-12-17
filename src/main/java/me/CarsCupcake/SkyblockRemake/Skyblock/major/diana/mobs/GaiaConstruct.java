package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Skyblock.FinalDamageDesider;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Location;
import org.bukkit.entity.Golem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import java.util.Objects;

public class GaiaConstruct extends SkyblockEntity implements FinalDamageDesider {
    private int maxHealth = 1;
    private final int damage;
    int hits = 6;
    public GaiaConstruct(ItemRarity rarity) {
        if (Objects.requireNonNull(rarity) == ItemRarity.LEGENDARY) {
            maxHealth = 1_500_000;
            damage = 3_400;
        } else {
            maxHealth = 300_000;
            damage = 850;
        }
        health = maxHealth;

    }
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }
    private LivingEntity entity;
    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Golem.class, golem -> {
            golem.setCustomNameVisible(true);
        });
        SkyblockEntity.livingEntity.addEntity(entity, this);
        new EntityRunnable() {

            @Override
            public void run() {

            }
        }.runTaskTimer(this, 1, 1);
    }

    @Override
    public String getName() {
        return "§2Gaia Cosntruct";
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        super.damage(damage, player);
        if (hits == 0) {
            if (health > maxHealth * 0.66) hits = 6;
            else if (health > maxHealth * 0.33) hits = 7;
            else hits = 8;
        }
        hits--;
    }

    @Override
    public double getFinalDamage(SkyblockPlayer player, double damage) {
        return (hits == 0) ? damage : 0;
    }
}
