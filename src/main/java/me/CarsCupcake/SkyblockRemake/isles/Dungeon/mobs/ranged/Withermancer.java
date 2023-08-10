package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.ranged;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.EntityAtributes;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonSkeleton;
import me.CarsCupcake.SkyblockRemake.utils.Pair;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntitySkeletonWither;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.*;

@EntityAtributes.MagicResistance(multiplier = 0.2)
public class Withermancer extends DungeonSkeleton {
    private LivingEntity entity;

    public Withermancer(int floor, boolean master) {
        super(floor, master);
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        SBEntity craftEntity = new SBEntity(loc);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(craftEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        entity = (LivingEntity) craftEntity.getBukkitEntity();
        entity.setAI(true);
        entity.setRemoveWhenFarAway(false);
        entity.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.STONE_SWORD));
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
        SkyblockEntity.livingEntity.addEntity(entity, this);
        ArrayDeque<Bundle<ArmorStand, MotionHandler>> skulls = new ArrayDeque<>();
        respawn(skulls, loc);
        new EntityRunnable() {
            int i = 0;

            @Override
            public void run() {
                i++;
                if (i == 50) {
                    i = 0;
                    if (skulls.isEmpty())
                        respawn(skulls, entity.getLocation());
                    else if (craftEntity.getGoalTarget() != null && craftEntity.getGoalTarget().getBukkitEntity() instanceof Player player)
                        followPlayer(skulls.pop().getFirst(), SkyblockPlayer.getSkyblockPlayer(player));
                }
                for (Bundle<ArmorStand, MotionHandler> b : skulls) {
                    MotionHandler data = b.getLast();
                    ArmorStand stand = b.getFirst();
                    Pair<Double> result = data.calculatioNext();
                    double yaw = result.getFirst();
                    double height = result.getLast();
                    Location location = entity.getLocation().add(new Vector(0.5, 0, 0).rotateAroundY(Math.toRadians(yaw)).add(new Vector(0, height + 0.25, 0)));
                    location.setYaw((float) yaw);
                    stand.teleport(location);
                }
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                skulls.forEach(armorStandIntegerBundle -> armorStandIntegerBundle.getFirst().remove());
            }
        }.runTaskTimer(this, 0, 1);
    }

    private void respawn(Deque<Bundle<ArmorStand, MotionHandler>> skulls, Location loc) {
        skulls.add(new Bundle<>((loc.getWorld().spawn(loc, ArmorStand.class, stand -> {
            stand.setInvisible(true);
            stand.setBasePlate(false);
            stand.getEquipment().setItemInMainHand(new ItemStack(Material.WITHER_SKELETON_SKULL));
            stand.setRightArmPose(new EulerAngle(5.497787143782138, 4.066617157157146788, 0));
            stand.addScoreboardTag("remove");
            stand.setCollidable(false);
            stand.setGravity(false);
        })), new MotionHandler(0)));
        MotionHandler m = new MotionHandler(180);
        m.direction = true;
        m.motion *= -1;
        skulls.add(new Bundle<>((loc.getWorld().spawn(loc, ArmorStand.class, stand -> {
            stand.setInvisible(true);
            stand.setBasePlate(false);
            stand.getEquipment().setItemInMainHand(new ItemStack(Material.WITHER_SKELETON_SKULL));
            stand.setRightArmPose(new EulerAngle(5.497787143782138, 4.066617157157146788, 0));
            stand.addScoreboardTag("remove");
            stand.setCollidable(false);
            stand.setGravity(false);
        })), m));
    }

    private void followPlayer(ArmorStand stand, SkyblockPlayer player) {
        new EntityRunnable() {
            int i = 0;

            @Override
            public void run() {
                i++;
                if (i > 100 || stand.isDead()) {
                    cancel();
                    return;
                }
                Vector dir = getDir(stand, player).normalize();
                Location l = stand.getLocation().add(dir.multiply(0.5));
                l.setDirection(dir);
                stand.teleport(l);
                List<Entity> entities = stand.getNearbyEntities(0.5, 0.5, 0.5).stream().filter(entity1 -> entity1 instanceof Player).toList();
                if (entities.isEmpty()) return;
                cancel();
                for (Entity entity : entities) {
                    SkyblockPlayer target = SkyblockPlayer.getSkyblockPlayer((Player) entity);
                    Calculator calculator = new Calculator();
                    calculator.entityToPlayerDamage(Withermancer.this, target);
                    calculator.damagePlayer(target);
                    calculator.showDamageTag(target);
                    target.sendMessage("§7Withermancer's Skull damaged you for §c" + String.format("%.0f", calculator.damage) + " §7damage");
                    stand.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, stand.getEyeLocation(), 1);
                }
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                if (!stand.isDead()) stand.remove();
            }
        }.runTaskTimer(this, 0, 1);
    }

    private Vector getDir(Entity root, Entity target) {
        return target.getLocation().add(0, 0.5, 0).toVector().subtract(root.getLocation().toVector());
    }

    @Override
    public String getName() {
        return "Withermancer";
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
    protected int healthFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 4 -> (master) ? 31_250_000 : 300_000;
            case 5 -> (master) ? 48_000_000 : 470_000;
            case 6 -> (master) ? 88_000_000 : 600_000;
            case 7 -> (master) ? 150_000_000 : 1_250_000;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected int damageFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 4 -> (master) ? 100_000 : 4_640;
            case 5 -> (master) ? 150_000 : 6_400;
            case 6 -> (master) ? 200_000 : 8_480;
            case 7 -> (master) ? 240_000 : 17_600;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected double defenseFromFloor(int floor, boolean master) {
        return 0;
    }

    private static class SBEntity extends EntitySkeletonWither {

        public SBEntity(Location location) {
            super(EntityTypes.ba, ((CraftWorld) location.getWorld()).getHandle());
            setAggressive(true);
            setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        }

        @Override
        public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
            if (entityliving instanceof EntityPlayer pl && SkyblockPlayer.getSkyblockPlayer(pl.getBukkitEntity()) != null)
                return super.setGoalTarget(entityliving, reason, fireEvent);
            return false;
        }
    }

    private static class MotionHandler {
        private double yaw;
        private double motion = 0.1;
        private boolean dirChange = false;
        private double currentY = 0;
        private boolean direction = false;

        public MotionHandler(double yaw) {
            this.yaw = yaw;
        }

        public Pair<Double> calculatioNext() {
            yaw += 10;
            if (dirChange) {
                if (direction)
                    motion += 0.01;
                else motion -= 0.01;
                if (motion >= 0.1 || motion <= -0.1) {
                    direction = !direction;
                    dirChange = false;
                }
            } else {
                if ((direction && currentY <= -0.5) || (!direction && currentY >= 0.5)) dirChange = true;
            }
            currentY += motion;
            return new Pair<>(yaw, currentY);
        }
    }
}
