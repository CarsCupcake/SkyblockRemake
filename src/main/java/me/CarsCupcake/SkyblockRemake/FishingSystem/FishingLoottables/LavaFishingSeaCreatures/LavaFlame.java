package me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Corruptable;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class LavaFlame extends SkyblockEntity implements Corruptable {
    private boolean isCorrupted = false;
    private int maxHealth = 3000000;
    private int health = 3000000;
    private LivingEntity entity;
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }


    @Override
    public int getDamage() {
        return 11000;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Blaze.class);
        entity.setCustomNameVisible(true);
        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);
    }


    @Override
    public String getName() {
        return "Lava Flame";
    }

    @Override
    public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName(SkyblockEntity.getBaseName(this));

    }

    @Override
    public void kill() {

    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;

    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }

    @Override
    public void corrupt() {
        if(!isCorrupted){
            maxHealth *= 3;
            health *=3;
            isCorrupted = true;
        }

    }

    @Override
    public boolean isCorrupted() {
        return isCorrupted;
    }
}
