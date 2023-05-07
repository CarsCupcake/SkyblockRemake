package me.CarsCupcake.SkyblockRemake.Skyblock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import me.CarsCupcake.SkyblockRemake.elements.Element;
import me.CarsCupcake.SkyblockRemake.elements.Elementable;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.LivingEntity;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.NotNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class SkyblockEntity implements Elementable {
    protected int health;

    public SkyblockEntity() {
        health = getMaxHealth();
    }


    public abstract int getMaxHealth();

    public int getHealth() {
        return health;
    }

    public abstract LivingEntity getEntity();

    public abstract int getDamage();

    public abstract void spawn(Location loc);

    public abstract String getName();

    public abstract HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player);

    public void updateNameTag() {
        getEntity().setCustomName(getBaseName(this));
    }

    @MustBeInvokedByOverriders
    @OverridingMethodsMustInvokeSuper
    public void kill() {
        EntityRunnable.remove(this);
    }

    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;
    }

    public abstract boolean hasNoKB();

    public abstract int getTrueDamage();

    public boolean isIgnored() {
        return false;
    }

    protected final Set<Element> elements = new HashSet<>();

    public int getLevel() {
        return -1;
    }

    @Override
    public Set<Element> getElements() {
        return elements;
    }

    @Override
    public void addElement(Element element) {
        elements.add(element);
    }

    @Override
    public void removeElement(Element element) {
        elements.remove(element);
    }

    public static EntityMap livingEntity = EntityMap.INSTANCE;
    public static ArrayList<LivingEntity> cooldowns = new ArrayList<>();


    static void updateDiguestedEntity(SkyblockEntity e) {
        LivingEntity entity = e.getEntity();
        float health;
        float maxhealth;

        maxhealth = e.getMaxHealth();
        health = e.getHealth();

        if (health <= 0) {
            entity.setCustomNameVisible(false);

            entity.setHealth(0);
            return;
        }
        @SuppressWarnings("deprecation") float estimated = (float) ((health / maxhealth) * entity.getMaxHealth());
        entity.setHealth(estimated);
        e.updateNameTag();
    }

    static String getBaseName(String name, int health, int maxhealth, int level) {
        return getBaseName(name, health, maxhealth, level,false);
    }

    static String getBaseName(String name, int health, int maxhealth, int level, boolean isCorrupted) {
        if (level == -1) {
            return getBaseName(name, health, maxhealth, isCorrupted);
        } else {
            String str;
            if (isCorrupted)
                str = "§7[§8Lv" + level + "§7] §5§ka§5Corrupted " + name + "§ka §a" + health + "§8/§a" + maxhealth;
            else str = "§7[§8Lv" + level + "§7] §c" + name + " §a" + health + "§8/§a" + maxhealth;
            return str;
        }
    }

    static String getBaseName(String name, int health, int maxhealth, boolean isCorrupted) {
        String str;
        if (isCorrupted) str = "§7[§8Lv?§7] §5§ka§5Corrupted " + name + "§ka §a" + health + "§8/§a" + maxhealth;
        else str = "§7[§8Lv?§7] §c" + name + " §a" + health + "§8/§a" + maxhealth;
        return str;
    }

    public static String getBaseName(SkyblockEntity entity) {
        if (entity instanceof Corruptable)
            return getBaseName(entity.getName(), entity.getHealth(), entity.getMaxHealth(), entity.getLevel(),((Corruptable) entity).isCorrupted());
        else return getBaseName(entity.getName(), entity.getHealth(), entity.getMaxHealth(), entity.getLevel());
    }

    public static void updateEntity(SkyblockEntity e) {
        LivingEntity entity = e.getEntity();

        if (Main.entitydead.containsKey(entity) && Main.entitydead.get(entity)) return;
        if (DiguestMobsManager.entitys.containsKey(entity)) {
            updateDiguestedEntity(e);
            return;
        }


        int health;
        int maxhealth;


        maxhealth = e.getMaxHealth();
        health = e.getHealth();

        if (health <= 0) {
            entity.setCustomNameVisible(false);

            entity.setHealth(0);

            return;
        }
        @SuppressWarnings("deprecation") double estimated = ((double) health / (double) maxhealth) * entity.getMaxHealth();
        entity.setHealth(estimated);


        e.updateNameTag();

    }

    public static boolean isOnCooldown(LivingEntity entity) {
        return cooldowns.contains(entity);
    }

    public static void setOnCooldown(LivingEntity entity) {
        cooldowns.add(entity);
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldowns.remove(entity);
            }
        }.runTaskLater(Main.getMain(), 15);

    }

    public static void killEntity(@NotNull SkyblockEntity entity, SkyblockPlayer killer) {
        LivingEntity e = entity.getEntity();
        if (killer != null) e.addScoreboardTag("killer:" + killer.getName());
        Main.EntityDeath(e);
        e.damage(9999999, killer);
        if (e instanceof EnderDragon) e.setHealth(0);
        SkyblockEntity.livingEntity.remove(e);
    }

    public static void killEntity(@NotNull LivingEntity e, SkyblockPlayer killer) {
        if (livingEntity.exists(e)) {
            killEntity(livingEntity.getSbEntity(e), killer);
            return;
        }

        if (killer != null) e.addScoreboardTag("killer:" + killer.getName());
        Main.EntityDeath(e);
        e.damage(9999999, killer);
        if (e instanceof EnderDragon) e.setHealth(0);
    }

}
