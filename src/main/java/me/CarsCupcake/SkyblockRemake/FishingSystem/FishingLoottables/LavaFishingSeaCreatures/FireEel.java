package me.CarsCupcake.SkyblockRemake.FishingSystem.FishingLoottables.LavaFishingSeaCreatures;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.MultipleEntity.MultipleEntityHead;
import me.CarsCupcake.SkyblockRemake.Skyblock.Corruptable;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import java.util.ArrayList;
import java.util.HashMap;

public class FireEel extends MultipleEntityHead implements Corruptable {
    private boolean isCorrupted = false;
    private int maxHealth = 4000000;
    private int health = 4000000;
    private Zombie z;
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
        return z;
    }


    @Override
    public int getDamage() {
        return 12000;
    }

    @Override
    public void spawn(Location loc) {

        z = loc.getWorld().spawn(loc, Zombie.class, armorStand ->{
            armorStand.setInvisible(true);
            armorStand.setGravity(false);
            armorStand.getEquipment().setHelmet(Tools.CustomHeadTexture("https://textures.minecraft.net/texture/f1a539d1064dd6d21f995db79fa4339cb8e616d293bd665b12de4e9a6ff5d587"));
            armorStand.setSilent(true);
            z.setCustomNameVisible(true);
        });
        SkyblockEntity.livingEntity.put(z, this);
        Main.updateentitystats(z);



    }


    @Override
    public String getName() {
        return "Fire Eel";
    }

    @Override
    public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        z.setCustomName(SkyblockEntity.getBaseName(this));
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
        return true;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }

    @Override
    public void corrupt() {
        if(!isCorrupted){
            isCorrupted = true;
            health *= 3;
            maxHealth *= 3;
            Main.updateentitystats(z);
        }
    }

    @Override
    public boolean isCorrupted() {
        return isCorrupted;
    }
}
