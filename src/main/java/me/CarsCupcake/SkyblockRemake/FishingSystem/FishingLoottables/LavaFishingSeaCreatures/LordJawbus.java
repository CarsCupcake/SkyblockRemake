package me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Items.Items;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Random;

public class LordJawbus extends SkyblockEntity {
    private LivingEntity entity;
    @Override
    public int getMaxHealth() {
        return 100000000;
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
        SkyblockEntity.livingEntity.addEntity(entity, this);
        Main.updateentitystats(entity);
    }


    @Override
    public String getName() {
        return "Lord Jawbus";
    }

    @Override
    public HashMap<ItemManager,Integer> getGarantuedDrops(SkyblockPlayer player) {
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
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }


}
