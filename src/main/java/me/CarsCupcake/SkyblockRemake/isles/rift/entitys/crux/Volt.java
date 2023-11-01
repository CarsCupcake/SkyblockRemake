package me.CarsCupcake.SkyblockRemake.isles.rift.entitys.crux;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.FinalDamageDesider;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import me.CarsCupcake.SkyblockRemake.isles.rift.entitys.Crux;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Iterator;

public class Volt extends Crux implements FinalDamageDesider {
    private LivingEntity entity;
    @Override
    public int getMaxHealth() {
        return 110;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
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
    public HashMap<ItemManager, Integer> getGarantuedDrops(SkyblockPlayer player) {
        return null;
    }


    @Override
    public boolean hasNoKB() {
        return false;
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
                for (Iterator<Entity> it = getEntity().getNearbyEntities(20, 20, 20).stream().filter(e -> e instanceof Player).iterator(); it.hasNext(); ) {
                    RiftPlayer player = RiftPlayer.getRiftPlayer((Player) it.next());
                    Vector dir = entity.getEyeLocation().toVector().subtract(player.getEyeLocation().toVector()).normalize();
                    float pitch;
                    float yaw = 0;
                    double x = dir.getX();
                    double z = dir.getZ();
                    if (x == 0.0 && z == 0.0) {
                        pitch = (float)(dir.getY() > 0.0 ? -90 : 90);
                    } else {
                        double theta = Math.atan2(-x, z);
                        yaw = (float)Math.toDegrees((theta + 6.283185307179586) % 6.283185307179586);
                        double x2 = NumberConversions.square(x);
                        double z2 = NumberConversions.square(z);
                        double xz = Math.sqrt(x2 + z2);
                        pitch = (float)Math.toDegrees(Math.atan(-dir.getY() / xz));
                    }
                    if(!((player.getLocation().getPitch() > pitch + 45 || player.getLocation().getPitch() < pitch - 45) && (player.getLocation().getYaw() > yaw + 45 || player.getLocation().getYaw() < yaw - 45))) {
                        player.subtractRiftTime(60);
                        player.sendMessage("§cShy §eremoved §a60s " + Stats.RiftTime.getSymbol() + "Rift Time §efrom using §dDon't look at me§e!");
                    }
                }
            }
        }.runTaskLater(Main.getMain(), 60);
    }

    @Override
    public double getFinalDamage(SkyblockPlayer player, double damage) {
        return (onHalf) ? 0 : damage;
    }

    @Override
    public int getRiftTimeDamage() {
        return 5;
    }
}
