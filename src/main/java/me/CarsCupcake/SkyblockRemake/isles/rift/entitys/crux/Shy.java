package me.CarsCupcake.SkyblockRemake.isles.rift.entitys.crux;

import me.CarsCupcake.SkyblockRemake.Entities.StandCoreExtention;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.FinalDamageDesider;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import me.CarsCupcake.SkyblockRemake.isles.rift.entitys.Crux;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

import java.util.Iterator;

public class Shy extends Crux implements FinalDamageDesider {
    private static final String NORMAL_FACE = "ewogICJ0aW1lc3RhbXAiIDogMTY0NzU4NzkyNDI4NywKICAicHJvZmlsZUlkIiA6ICJmMTYwZTMxMzJjYWM0YjRiOWM5OTk2NDQ1OGIxOWM0ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUb255S0tLIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2FjOWFkY2Q5NzVhOTE2OTE0YjVkMjlhZGFjZjVmNTJlNzk1MTQ3ODU4MzQ3NjU1MmE0ZjZmZThkOTRmNDhjMmEiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";
    private static final String RAGE_FACE = "ewogICJ0aW1lc3RhbXAiIDogMTY0NzU5NTMwNTIxMywKICAicHJvZmlsZUlkIiA6ICI3NTE0NDQ4MTkxZTY0NTQ2OGM5NzM5YTZlMzk1N2JlYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGFua3NNb2phbmciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdjN2YxZjFhZjNlZDVmMWNjOTdiNmZjODU0ODQ4YThiY2I2ODM5ZjIwY2Y1YTBhNWNhMTZkNzBkYmMyOGI1YyIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";
    private static final String LOOK_AWAY_FACE = "ewogICJ0aW1lc3RhbXAiIDogMTY0NzU5MzA4NzU3OCwKICAicHJvZmlsZUlkIiA6ICI4N2RlZmVhMTQwMWQ0MzYxODFhNmNhOWI3ZGQ2ODg0MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJCdm5ueUJ2biIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84Mjc3MWQ2ZWM0N2EyM2Y4MTZlNzhjNzgxMzBkYTVmNGZhYzQ1ZjlhODM0YTk4YzU1MWUzZGRiNDVkMzcyMWY2IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";
    private LivingEntity entity;
    private ArmorStand stand;
    private boolean hasBeenAttacked = false;
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
        entity = (LivingEntity) new Damager(loc).getBukkitEntity();
        entity.setRemoveWhenFarAway(false);
        ((Ageable) entity).setBaby();
        entity.setSilent(true);
        entity.teleport(loc);
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
        entity.setCustomNameVisible(true);
        entity.setInvisible(true);
        stand = loc.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
            armorStand.setSmall(true);
            armorStand.setCustomNameVisible(true);
            armorStand.setInvisible(true);
            armorStand.setBasePlate(false);
            armorStand.getEquipment().setHelmet(Tools.getCustomTexturedHeadFromSkullValue(NORMAL_FACE));
            armorStand.addScoreboardTag("remove");
            armorStand.setRemoveWhenFarAway(false);
        });
        SkyblockEntity.addExtention(new StandCoreExtention(stand, this));
        SkyblockEntity.livingEntity.addEntity(entity, this);
        new EntityRunnable() {
            @Override
            public void run() {
                stand.teleport(entity);
                stand.setCustomName(entity.getCustomName());
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                stand.remove();
            }
        }.runTaskTimer(this, 1, 1);
    }

    @Override
    public String getName() {
        return "§3Shy";
    }
    private boolean onHalf = false;



    @Override
    public boolean hasNoKB() {
        return onHalf;
    }

    @Override
    public int getLevel() {
        return 1;
    }
    @Override
    protected void onHalfDamage() {
        onHalf = true;
        stand.getEquipment().setHelmet(Tools.getCustomTexturedHeadFromSkullValue(LOOK_AWAY_FACE));
        ArmorStand text = entity.getWorld().spawn(stand.getEyeLocation().add(0, 0.75, 0), ArmorStand.class, s -> {
            s.setMarker(true);
            s.setBasePlate(false);
            s.setCustomNameVisible(true);
            s.setInvisible(true);
            s.setCustomName("I am ugly!");
        });
        entity.setAI(false);
        new BukkitRunnable(){

            @Override
            public void run() {
                text.remove();
                entity.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, entity.getEyeLocation(), 1);
                onHalf = false;
                stand.getEquipment().setHelmet(Tools.getCustomTexturedHeadFromSkullValue(RAGE_FACE));
                stand.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, stand.getEyeLocation(), 1);
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
                    if(!((player.getLocation().getPitch() > pitch + 45 || player.getLocation().getPitch() < pitch - 45) || (player.getLocation().getYaw() > yaw + 45 || player.getLocation().getYaw() < yaw - 45))) {
                        player.subtractRiftTime(60);
                        player.sendMessage("§cShy §eremoved §a60s " + Stats.RiftTime.getSymbol() + "Rift Time §efrom using §dDon't look at me§e!");
                    }
                }
                entity.setAI(true);
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

    @Override
    public void damage(double damage, SkyblockPlayer player) {

        if(player != null) {
            if(!hasBeenAttacked) stand.getEquipment().setHelmet(Tools.getCustomTexturedHeadFromSkullValue(RAGE_FACE));
            hasBeenAttacked = true;
        }
        if(onHalf) return;

        super.damage(damage, player);
    }

    @Override
    public double getHeartsDamage() {
        return 0;
    }
    private class Damager extends EntityZombie {
        public Damager(Location location) {
            super(EntityTypes.be, ((CraftWorld)location.getWorld()).getHandle());
            setLocation(location.getX(), location.getY(), location.getX(), location.getYaw(), location.getPitch());
            ((CraftWorld)location.getWorld()).getHandle().addEntity(this, CreatureSpawnEvent.SpawnReason.CUSTOM);
        }

        @Override
        public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
            if(!hasBeenAttacked) return false;
            return super.setGoalTarget(entityliving, reason, fireEvent);
        }
    }
}
