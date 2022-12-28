package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.Defensive;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftWither;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;

import java.util.HashMap;

public class MastermodeNecron extends SkyblockEntity implements Defensive {
    private int health = 1400000000;
    private BossBar display;
    private LivingEntity entity;
    @Override
    public int getMaxHealth() {
        return 1400000000;
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
        return 0;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Wither.class, wither -> {
            wither.setCustomNameVisible(true);
            wither.setRemoveWhenFarAway(false);
            wither.setAI(false);
        });
        ((CraftWither)entity).getHandle().setInvul(0);
        SkyblockEntity.livingEntity.put(entity, this);
        display = Bukkit.createBossBar("Necron", BarColor.RED, BarStyle.SOLID);
        for (Player p : Bukkit.getOnlinePlayers())
            display.addPlayer(p);
        Main.updateentitystats(entity);
    }


    @Override
    public String getName() {
        return "Necron";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName("§cNecron");
        ((CraftWither)entity).getBossBar().removeFlag(BarFlag.DARKEN_SKY);
        ((CraftWither)entity).getBossBar().removeAll();
        display.setProgress(((double) getHealth())/((double)getMaxHealth()));
    }

    @Override
    public void kill() {
        display.removeAll();
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
    public double getDefense() {
        return 2100;
    }
}
