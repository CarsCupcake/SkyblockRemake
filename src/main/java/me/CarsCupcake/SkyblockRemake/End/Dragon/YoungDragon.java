package me.CarsCupcake.SkyblockRemake.End.Dragon;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.Defensive;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;

public class YoungDragon implements SkyblockEntity, Defensive {
    private int health = 7500000;
    private EnderDragon entity;
    @Override
    public double getDefense() {
        return 18;
    }

    @Override
    public int getMaxHealth() {
        return 7500000;
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
    public void setHealth(int i) {
        health = i;
    }

    @Override
    public int getDamage() {
        return 1100;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, EnderDragon.class, enderDragon -> {
            enderDragon.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(2.25);
            enderDragon.addScoreboardTag("combatxp:300");
        });
    }

    @Override
    public String getId() {
        return "UNTSABLE_DRAGON";
    }

    @Override
    public String getName() {
        return "Unstable Dragon";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName("§5Unstable Dragon");
    }

    @Override
    public void kill() {
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health += damage;
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }
}
