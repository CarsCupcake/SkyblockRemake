package me.CarsCupcake.SkyblockRemake.isles.rift.entitys;

import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.FinalDamageDesider;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Shy extends Crux implements FinalDamageDesider {
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
    private boolean onHalf = false;

    @Override
    protected void onHalfDamage() {
        onHalf = true;
        ArmorStand text = entity.getWorld().spawn(entity.getEyeLocation(), ArmorStand.class, s -> {
            s.setMarker(true);
            s.setBasePlate(false);
            s.setCustomNameVisible(true);
            s.setCustomName("I am ugly!");
        });
        new BukkitRunnable(){

            @Override
            public void run() {
                text.remove();
                entity.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, entity.getEyeLocation(), 1);
                onHalf = false;
            }
        }.runTaskLater(Main.getMain(), 60);
    }

    @Override
    public double getFinalDamage(SkyblockPlayer player, double damage) {
        return (onHalf) ? 0 : damage;
    }
}
