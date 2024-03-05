package me.CarsCupcake.SkyblockRemake.utils;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class Laser {
    @Getter
    private final LaserProvider start;
    @Getter
    private final LaserReciever end;
    private static final HashMap<LivingEntity, ArmorStand> lasers = new HashMap<>();

    public Laser(Location guardian, Location squid) {
        guardian.setPitch(0);
        squid.setPitch(0);

        Assert.isTrue(guardian.getWorld() == squid.getWorld(), "The world are not allowed to be different!");


        start = new LaserProvider();
        start.spawn(guardian);

        end = new LaserReciever();
        end.spawn(squid);

        start.g.setTarget(end.getEntity());

        lasers.put(start.g, end.g);

    }

    public void stop() {
        lasers.remove(start.g);
        start.getEntity().remove();
        end.getEntity().remove();
    }

    public void rotateAroundStartY(double degree) {
        Vector dir = end.getEntity().getLocation().toVector().subtract(start.getEntity().getLocation().toVector());
        dir = dir.rotateAroundY(Math.toRadians(degree));
        Location newEnd = start.getEntity().getLocation().add(dir);
        getEnd().getEntity().teleport(newEnd);
    }

    public List<Entity> getHitEntitys() {
        return getHitEntitys(entity -> true);
    }
    public List<Entity> getHitEntitys(Predicate<Entity> entityPredicate) {
        List<Entity> entities = new ArrayList<>();
        double length = start.getEntity().getLocation().toVector().subtract(end.getEntity().getLocation().toVector()).length();
        for (Entity entity : start.getEntity().getNearbyEntities(length, length, length)) {
            if (!entityPredicate.test(entity)) continue;
            RayTraceResult result = entity.getBoundingBox().rayTrace(start.getEntity().getLocation().toVector(), start.getEntity().getLocation().getDirection(), length);
            if (result == null) continue;
            if (entity.equals(end.getEntity())) continue;
            entities.add(entity);
        }
        return entities;
    }

public static class LaserListener implements Listener {
    @EventHandler
    public void targetSwap(EntityTargetLivingEntityEvent event) {
        if (!(event.getEntity() instanceof LivingEntity entity)) return;
        if (lasers.containsKey(entity) && lasers.get(entity) != event.getTarget()) event.setCancelled(true);
    }
}

public static class LaserReciever extends SkyblockEntity {
    private ArmorStand g;

    @Override
    public int getMaxHealth() {
        return 100;
    }

    @Override
    public LivingEntity getEntity() {
        return g;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void spawn(Location loc) {
        g = loc.getWorld().spawn(loc, ArmorStand.class, a -> {
            a.setInvisible(true);
            a.setInvulnerable(true);
            a.setAI(false);
            a.setSilent(true);
            a.setGravity(false);
            a.addScoreboardTag("invinc");
            a.addScoreboardTag("remove");
            a.setBasePlate(false);
            a.setCollidable(false);
        });
        SkyblockEntity.livingEntity.addEntity(g, this);
        Main.updateentitystats(g);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public HashMap<ItemManager, Integer> getGarantuedDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
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

public static class LaserProvider extends SkyblockEntity {
    private Guardian g;

    @Override
    public int getMaxHealth() {
        return 100;
    }

    @Override
    public LivingEntity getEntity() {
        return g;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void spawn(Location loc) {
        g = loc.getWorld().spawn(loc, Guardian.class, a -> {
            a.setInvisible(true);
            a.setInvulnerable(true);
            a.setLaser(true);
            a.setSilent(true);
            a.setGravity(false);
            a.addScoreboardTag("invinc");
            a.setCollidable(false);
        });
        SkyblockEntity.livingEntity.addEntity(g, this);
        Main.updateentitystats(g);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public HashMap<ItemManager, Integer> getGarantuedDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
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
}