package me.CarsCupcake.SkyblockRemake.isles.Dungeon.MiniBoss.LostAdventurers;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonMob;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.ReflectionUtils;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.minecraft.network.protocol.game.PacketPlayOutEntityStatus;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalBowShoot;
import net.minecraft.world.entity.monster.EntitySkeleton;
import net.minecraft.world.entity.monster.EntitySkeletonAbstract;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;

public abstract class LostAdventurer extends DungeonMob {
    protected LivingEntity entity;
    protected LostAdventurer.SBEntity craftEntity;
    protected final ItemBuilder theSword = new ItemBuilder(Material.DIAMOND_SWORD).setGlint(true);
    protected double tenPers;
    protected boolean heal = false;
    protected boolean healDone = false;
    protected BukkitRunnable cooldown;
    protected final BukkitRunnable r = new BukkitRunnable() {
        int shootColdown = 0;
        int shots = 0;
        int aotdCooldown = 0;

        @Override
        public void run() {
            if(aotdCooldown != 0) aotdCooldown--;
            if (heal) return;
            if (craftEntity.getGoalTarget() != null && craftEntity.getGoalTarget() instanceof EntityPlayer && Bukkit.getEntity(craftEntity.getGoalTarget().getUniqueID()) != null && getLocation(craftEntity.getGoalTarget()).distance(entity.getLocation()) < 5.5) {
                if (shootColdown != 0) shootColdown--;
                if (entity.getEquipment().getItem(EquipmentSlot.HAND).getType() != Material.DIAMOND_SWORD)
                    entity.getEquipment().setItem(EquipmentSlot.HAND, theSword.build());
                if(getLocation(craftEntity.getGoalTarget()).distance(entity.getLocation()) < 3 && aotdCooldown == 0){
                    Location l = entity.getLocation();
                    Location pL = new Location(l.getWorld(), craftEntity.getGoalTarget().locX(), craftEntity.getGoalTarget().locY(), craftEntity.getGoalTarget().locZ());
                    Vector dir = pL.toVector().subtract(l.clone().add(0, 0.5, 0).toVector());
                    dir = dir.normalize();
                    dir = dir.multiply(5);
                    dir = dir.add(new Vector(0, 0.2, 0));
                    Calculator c = new Calculator();
                    SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(((EntityPlayer) craftEntity.getGoalTarget()).getBukkitEntity());
                    player.setVelocity(dir);
                    c.entityToPlayerDamage(LostAdventurer.this, player, new Bundle<>(getDamage() * 2, 0));
                    player.sendMessage("§7Lost Adventurer's Dragon's Breath damaged you for §c" + (getDamage() * 2) + " §7damage");
                    entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1, 1);
                    c.damagePlayer(player);
                    c.showDamageTag(player);
                    aotdCooldown = 50;
                    ParticleUtils.spiralParticles(entity, 0.2, 1, Particle.FLAME);
                }
            } else {
                if (shootColdown != 0) shootColdown--;
                if (shootColdown > 0 && entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue() != 0)
                    return;
                if (shots >= 40) {
                    if (entity.getEquipment().getItem(EquipmentSlot.HAND).getType() != Material.DIAMOND_SWORD)
                        entity.getEquipment().setItem(EquipmentSlot.HAND, theSword.build());
                    shootColdown = 40;
                    shots = 0;
                    return;
                } else shots++;
                if (entity.getEquipment().getItem(EquipmentSlot.HAND).getType() != Material.BOW)
                    entity.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.BOW));

            }
        }
    };
    public LostAdventurer(int floor, boolean master) {
        super(floor, master);
        tenPers = getMaxHealth() * 0.1;
        System.out.println(tenPers);
    }
    @Override
    public String getName() {
        return "Lost Adventurer";
    }

    @Override
    public void kill() {
        try {
            r.cancel();
        } catch (Exception ignored) {
        }
        try {
            cooldown.cancel();
        } catch (Exception ignored) {
        }
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;
        if (!healDone && !heal && health <= tenPers) {
            heal = true;
            entity.setAI(false);
            entity.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.GOLDEN_APPLE));
            for (Player p : Bukkit.getOnlinePlayers()) {
                PacketPlayOutEntityStatus status = new PacketPlayOutEntityStatus(craftEntity, (byte) 9);
                ((CraftPlayer) p).getHandle().b.sendPacket(status);
            }
            entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 1, 1);
            new BukkitRunnable() {
                int i = 0;
                @Override
                public void run() {
                    if (entity == null || entity.isDead()) {
                        cancel();
                        return;
                    }
                    if(i == 7){
                        cancel();
                        entity.getWorld().spawnParticle(Particle.HEART, entity.getLocation().clone().add(0, entity.getEyeHeight() / 2, 0), 20, 0.6, entity.getEyeHeight() / 2, 0.6);
                        health += tenPers * 5;
                        tenPers -= tenPers * 0.1;
                        entity.setAI(true);
                        healDone = true;
                        heal = false;
                        entity.getEquipment().setItem(EquipmentSlot.HAND, theSword.build());
                        cooldown = new BukkitRunnable() {
                            @Override
                            public void run() {
                                healDone = false;
                            }
                        };
                        cooldown.runTaskLater(Main.getMain(), 200);
                        entity.getWorld().playSound(entity.getEyeLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
                    }else
                        entity.getWorld().playSound(entity.getEyeLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
                    i++;
                }
            }.runTaskTimer(Main.getMain(), 0, 4);
        }
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
    public void updateNameTag() {
        DiguestMobsManager.getDiguested.get(entity).setName("§c" + getName() + " §a" + Tools.toShortNumber(getHealth()));
        super.updateNameTag();
    }



    protected static class SBEntity extends EntitySkeleton {

        public SBEntity(Location location) {
            super(EntityTypes.aB, ((CraftWorld) location.getWorld()).getHandle());
            try {
                Field f = EntitySkeletonAbstract.class.getDeclaredField("b");
                ReflectionUtils.makeAccessible(f);
                PathfinderGoalBowShoot<EntitySkeletonAbstract> b = (PathfinderGoalBowShoot<EntitySkeletonAbstract>) ReflectionUtils.getField(f, this);
                b.a(0);
                Field f2 = b.getClass().getDeclaredField("d");
                ReflectionUtils.makeAccessible(f2);
                ReflectionUtils.setField(f2, b, 25 * 25);
                f2.setAccessible(false);
                ReflectionUtils.setField(f, this, b);
                f.setAccessible(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            setAggressive(true);
            setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        }

        @Override
        protected void initPathfinder() {

            super.initPathfinder();
        }
    }

    private static Location getLocation(EntityLiving entityLiving) {
        return Bukkit.getEntity(entityLiving.getUniqueID()).getLocation();
    }
    public static class ParticleUtils {
        private static void rotateAroundAxisX(Vector v, double angle) {
            angle = Math.toRadians(angle);
            double y, z, cos, sin;
            cos = Math.cos(angle);
            sin = Math.sin(angle);
            y = v.getY() * cos - v.getZ() * sin;
            z = v.getY() * sin + v.getZ() * cos;
            v.setY(y).setZ(z);
        }
        private static void rotateAroundAxisY(Vector v, double angle) {
            angle = -angle;
            angle = Math.toRadians(angle);
            double x, z, cos, sin;
            cos = Math.cos(angle);
            sin = Math.sin(angle);
            x = v.getX() * cos + v.getZ() * sin;
            z = v.getX() * -sin + v.getZ() * cos;
            v.setX(x).setZ(z);
        }
        public static void spiralParticles(LivingEntity player, double f, double delta, Particle particle) {
            double radius = f;
            double forward = 0;
            double deltaForward = 2;

            for (int i = 0; i < 60; i += 1) {
                double angle = i * 6;
                double x = radius * Math.cos(angle);
                double z = radius * Math.sin(angle);
                forward += deltaForward / 60;
                radius += delta / 60;

                Vector v = new Vector(x, 0, z);
                rotateAroundAxisX(v, player.getEyeLocation().getPitch() - 90);
                Vector v2 = new Vector(v.getX(), v.getY(), v.getZ());
                rotateAroundAxisY(v2, player.getEyeLocation().getYaw());

                Location loc = player.getEyeLocation().add(v2.getX(), v2.getY(), v2.getZ()).add(player.getEyeLocation().getDirection().multiply(0.5));
                loc = loc.add(loc.getDirection().multiply(forward));
                loc.getWorld().spawnParticle(particle, loc.getX(), loc.getY(), loc.getZ(), 0);
            }
        }
    }
}
