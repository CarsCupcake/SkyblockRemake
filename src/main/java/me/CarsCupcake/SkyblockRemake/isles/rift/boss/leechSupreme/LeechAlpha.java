package me.CarsCupcake.SkyblockRemake.isles.rift.boss.leechSupreme;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.rift.entitys.RiftEntity;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import java.util.HashMap;

public class LeechAlpha extends RiftEntity {
    private static final String texture = "ewogICJ0aW1lc3RhbXAiIDogMTYwNDYxNDc0Mjg3NywKICAicHJvZmlsZUlkIiA6ICI0ZDcwNDg2ZjUwOTI0ZDMzODZiYmZjOWMxMmJhYjRhZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJzaXJGYWJpb3pzY2hlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Q5NzZmZmRkMjkyYzM0ZmRhYmIyYWNmYTYyZGJmZDRmMGQxMzAwMDg3ODU4MDliZTAxNmQzZDcyNTdiYjMxZjIiCiAgICB9CiAgfQp9";
    private static final String signature = "OUZ7cpgqW4m9lxL155ISzLjbkSjzn41asRnWsffNTaHdLI5ImVtaHAVeIh8C5eSSi/lelmBpcsrB+3VWXWwicWcCcczXan5Ztps6Jp0uY9FlwqCrH7faGDBz8qXe5KL98Lox0ceJQc4SMQfUpmbEHdKIwCCSWosm4RbS2GoPji9k5q0tbzPpvxuHGYM8DvSk19qZ+p/NmooAFJ6cZxd4eDgJjQ8cbxMK77Tb17alkrubi7Eh346sDz2H1U622GnVMV0zKuUpao19V7QXyo4rglLcIx7OcshlTS3d0XgLhzRxD0OGQYD91jYy4TM8aedvNsH+qtX2vgmgt75ZUUEFE91NCsBfR5y7TfoN4yIdTuCMqtTahJZV7Qs0TFSofj+KLEmdv1lCwdqukBO8HAtIzDS9f6llVA+pa35xkiSH+hIkO4OYnUCs37t68rL+2o/emUx8AJX3DyemBWS8nvvY8dQAUdOSwa/4de5ewEcPPigZhKFCZcDBmyrEq+cj187zXDeWcZ0Rv4sL5A1HUYWsOD/GxxYk0on02nk30X4nGfgjo3hexFwxdJdZKSUbHcPztFk1y9rI/dAJSWK1+8MYXpjXqqbIPKBPZsupQLI99nol2IJ2aecL0uTPwrBW6tITZr9JfiBkqVyVbZIEEewAmdikPFv1bqblIzpBGSHn4fg=";
    private LivingEntity entity;
    @Override
    public int getMaxHealth() {
        return 150;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class, (e) -> {
            e.setRemoveWhenFarAway(false);
            e.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
            e.setAdult();
        });
        DiguestMobsManager.createEntity(entity, texture, signature);
        SkyblockEntity.livingEntity.addEntity(entity, this);
        Main.updateentitystats(entity);
        entity.setCustomNameVisible(true);
        entity.setVelocity(LeechFightManager.getMiddle().toVector().subtract(entity.getLocation().toVector()).normalize().setY(1));
        new EntityRunnable() {
            @Override
            public void run() {
                if(LeechFightManager.getMiddle().distance(entity.getLocation()) > 15)
                    entity.setVelocity(LeechFightManager.getMiddle().toVector().subtract(entity.getLocation().toVector()).normalize().setY(0.5));
            }
        }.runTaskTimer(Main.getMain(), 20, 1);
    }

    @Override
    public String getName() {
        return "Leech Alpha";
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
    public int getRiftTimeDamage() {
        return 5;
    }

    @Override
    public double getHeartsDamage() {
        return 0;
    }

    @Override
    public void kill() {
        super.kill();
        LeechFightManager.getInstance().leechSwarmKill();
    }

    @Override
    public int getLevel() {
        return 5;
    }
}
