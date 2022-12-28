package me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Corruptable;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;

import java.util.ArrayList;
import java.util.HashMap;

public class TaurusPig extends SkyblockEntity implements Corruptable {
    private boolean isCorrupted = false;
    private int maxHealth = 100;
    private int health = 100;
    private LivingEntity entity;
    private SkyblockEntity main;
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
        return 10000;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Pig.class, e->{

        });
        entity.setCustomNameVisible(true);
        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);
    }


    @Override
    public String getName() {
        return "Thunder";
    }

    @Override
    public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomNameVisible(false);
        entity.setCustomName(" ");

    }

    @Override
    public void kill() {
        entity.remove();
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        main.damage(damage, player);

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
    public void setMain(SkyblockEntity entity){
        main =entity;
    }
}
