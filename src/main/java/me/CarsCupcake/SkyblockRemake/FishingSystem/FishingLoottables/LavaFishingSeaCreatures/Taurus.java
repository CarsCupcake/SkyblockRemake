package me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Corruptable;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import java.util.ArrayList;
import java.util.HashMap;

public class Taurus extends SkyblockEntity implements Corruptable {
    private boolean isCorrupted = false;
    private int maxHealth = 5000000;
    private int health = 5000000;
    private LivingEntity entity;
    @Getter
    private TaurusPig pig;
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
        return 13000;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class, e->{

        });
        entity.setCustomNameVisible(true);
        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);
        pig = new TaurusPig();
        pig.setMain(this);
        pig.spawn(loc);
        pig.getEntity().setPassenger(entity);

    }


    @Override
    public String getName() {
        return "Taurus";
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
        pig.kill();
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
