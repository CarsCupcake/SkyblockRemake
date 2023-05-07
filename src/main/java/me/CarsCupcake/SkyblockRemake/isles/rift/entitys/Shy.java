package me.CarsCupcake.SkyblockRemake.isles.rift.entitys;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import java.util.HashMap;

public class Shy extends SkyblockEntity {
    private LivingEntity entity;
    @Override
    public int getMaxHealth() {
        return 0;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class, zombie -> {
            zombie.setBaby();
            zombie.setCustomNameVisible(true);
            zombie.getEquipment().setHelmet(Tools.CustomHeadTexture(""));
            zombie.setInvisible(true);
        });
    }

    @Override
    public String getName() {
        return "§3Shy";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
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
    public int getLevel() {
        return 1;
    }
}
