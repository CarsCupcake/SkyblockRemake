package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.MythologicalPerk;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class Minotaur extends SkyblockEntity {
    private int maxHealth = 1;
    private final int damage;
    private final MythologicalPerk perk;
    public Minotaur(ItemRarity rarity, MythologicalPerk perk) {
        switch (rarity) {
            case RARE, EPIC -> {
                maxHealth = 625_000;
                damage = 250;
            }
            case LEGENDARY -> {
                maxHealth = 2_500_000;
                damage = 600;
            }
            default -> {
                maxHealth = 60_000;
                damage = 200;
            }
        }
        health = maxHealth;
        this.perk = perk;
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

    @Override
    public void kill() {
        super.kill();
        if (perk != null) perk.kill(this);
    }
}
