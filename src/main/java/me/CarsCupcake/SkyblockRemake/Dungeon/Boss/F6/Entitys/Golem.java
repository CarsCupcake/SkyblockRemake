package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.Entitys;

import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F6.F6Items;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Defensive;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;

import javax.tools.Tool;
import java.util.HashMap;
import java.util.Random;

public class Golem extends SkyblockEntity implements Defensive {
    private boolean isAsleep = true;
    private org.bukkit.entity.Golem entity;
    private int health = 20000000;
    private JumpAI ai;
    @Override
    public int getMaxHealth() {
        return 20000000;
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
        return 120000;
    }

    @Override
    public void spawn(Location loc) {
        loc.setPitch(45);
        entity = loc.getWorld().spawn(loc, IronGolem.class, golem -> {
            golem.setAI(false);
            golem.addScoreboardTag("combatxp:4000");
        });
        entity.setRemoveWhenFarAway(false);
        SkyblockEntity.livingEntity.put(entity, this);
        Main.updateentitystats(entity);
        this.ai = new JumpAI(entity);

    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        double chance = new Random().nextDouble();
        if(chance <= 0.04){
            HashMap<ItemManager, Integer> drops = new HashMap<>();
            drops.put(F6Items.Items.AncientRose.getItem(), 1);
            return drops;
        }
        return null;
    }

    @Override
    public void updateNameTag() {
        if(isAsleep)
            entity.setCustomName("§5Sleeping Golem");
        else
            entity.setCustomName("§6Woke Golem" + " §a" + Tools.toShortNumber(getHealth()));
    }

    @Override
    public void kill() {
        try {
            ai.cancel();
        }catch (Exception ignored){}
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        isAsleep = false;
        entity.setAI(true);
        entity.setTarget(player);
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.6);
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
    public double getDefense() {
        return 200;
    }
}
