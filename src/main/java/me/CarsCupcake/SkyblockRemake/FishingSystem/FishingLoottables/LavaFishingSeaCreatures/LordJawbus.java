package me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Corruptable;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Squid;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LordJawbus extends SkyblockEntity {
    private int health = 100000000;
    private LivingEntity entity;
    @Override
    public int getMaxHealth() {
        int maxHealth = 100000000;
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
        return 15000;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, IronGolem.class);
        entity.setCustomNameVisible(true);
        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);
    }


    @Override
    public String getName() {
        return "Lord Jawbus";
    }

    @Override
    public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
        HashMap<ItemManager, Integer> drops = new HashMap<>();
        drops.put(Items.SkyblockItems.get("MAGMA_LORD_FRAGMENT"), 1);
        drops.put(Items.SkyblockItems.get("MAGMA_FISH_SILVER"), new Random().nextInt(25) + 25);
        return drops;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName("§e﴾" + SkyblockEntity.getBaseName(this) + "§e﴿");

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


}
