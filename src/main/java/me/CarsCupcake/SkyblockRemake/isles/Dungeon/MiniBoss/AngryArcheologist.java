package me.CarsCupcake.SkyblockRemake.isles.Dungeon.MiniBoss;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonMob;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.ReflectionUtils;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalBowShoot;
import net.minecraft.world.entity.monster.EntitySkeleton;
import net.minecraft.world.entity.monster.EntitySkeletonAbstract;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.HashMap;

public class AngryArcheologist extends DungeonMob {
    private LivingEntity entity;
    private SBEntity craftEntity;
    private final ItemBuilder theSword = new ItemBuilder(Material.DIAMOND_SWORD).setGlint(true);
    private double tenPers;
    private boolean heal = false;
    private boolean healDone = false;
    private BukkitRunnable cooldown;
    private final BukkitRunnable r = new BukkitRunnable() {
        int shootColdown = 0;
        int shots = 0;

        @Override
        public void run() {
            if (heal) return;
            if (craftEntity.getGoalTarget() != null && Bukkit.getEntity(craftEntity.getGoalTarget().getUniqueID()) != null && getLocation(craftEntity.getGoalTarget()).distance(entity.getLocation()) < 5.5) {
                if (shootColdown != 0) shootColdown--;
                if (entity.getEquipment().getItem(EquipmentSlot.HAND).getType() != Material.DIAMOND_SWORD)
                    entity.getEquipment().setItem(EquipmentSlot.HAND, theSword.build());
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

    public AngryArcheologist(int floor, boolean master) {
        super(floor, master);
        tenPers = getMaxHealth() * 0.1;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        craftEntity = new AngryArcheologist.SBEntity(loc);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(craftEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        entity = (LivingEntity) craftEntity.getBukkitEntity();
        entity.setAI(true);
        entity.setRemoveWhenFarAway(false);
        entity.getEquipment().setItem(EquipmentSlot.HEAD, new ItemBuilder(Material.DIAMOND_HELMET).setGlint(true).build());
        entity.getEquipment().setItem(EquipmentSlot.CHEST, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setGlint(true).build());
        entity.getEquipment().setItem(EquipmentSlot.LEGS, new ItemBuilder(Material.DIAMOND_LEGGINGS).setGlint(true).build());
        entity.getEquipment().setItem(EquipmentSlot.FEET, new ItemBuilder(Material.DIAMOND_BOOTS).setGlint(true).build());
        entity.getEquipment().setItem(EquipmentSlot.HAND, theSword.build());
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
        DiguestMobsManager.createEntity(entity, "-", "ewogICJ0aW1lc3RhbXAiIDogMTYxMjAzMDY5NDA5OSwKICAicHJvZmlsZUlkIiA6ICJmNWQwYjFhZTQxNmU0YTE5ODEyMTRmZGQzMWU3MzA1YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXRjaFRoZVdhdmUxMCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jNDhjNzgzNDU4ZTRjZjg1MThlOGFiNTg2M2ZiYzRjYjk0OGY5MDU2OGVlYjlhNjBkMTZjNGZkZTJiOTZjMDMzIgogICAgfQogIH0KfQ==", "h0EcebQKYgqarHvlkbkkkRN798ir/crHJD4PUtLWNgohxOCk0WbtPu5YxQpmCL75Y6I2Y0vVQvic7x2r4vfMUu5z0O5dfjUXwpXQ6zWYdmHIbeg796EqUsdr1VJlPHMY/PVYle+NoYflwssIXYqLOWqswaBB4cz6qfyinujYoU6wVhGbONns7h/mpCM1r+gyua0hP9g+kjgslGebpDtkQRtv/kZpJ5+19cM5KT12KmjBGlTwsmiP+RfEINt5oGv2p12wqwv0CC5TFqB+/SM1yjYcEdWXQfzmsnC9nzIfgEHpNquKiX2pcGfVPvKgjkkLkO23nnQ0e2KOfIhLKHlyHcESd/lwGP9Ea/i+JVtZMEMUmuU3lQU+ywDMCQiGNEnB9MFlDdA6LBc2mwZKYShyQNgEveXxV2V1j8dt5ctKe7ANBMrCKXRjIO0TcHv2q/PJ9GwEuSfRNwdZp88gkbb79VV+7R4nkzAzEmNpRUxpB4P0qYDpMNCaC+NYEjHrzUr3hiD3tyHQzWHqvOJYYkor5kxGBoE19lZNxfVOEv9K6dIiSAPAtyYbRc9PVL9DUwlshfQO+kYwymSfZqVqW8CUafIA2NtIpIshsuOigqPMYIJv/p1HfGZVSGZ2B1Zmb/DQ9QoTLPqv+ExZ/zMMAqLQB+aB1DL5qfMABNOYhyfSQ2c=");
        SkyblockEntity.livingEntity.addEntity(entity, this);
        updateNameTag();
        r.runTaskTimer(Main.getMain(), 5, 5);
    }

    @Override
    public String getName() {
        return "Angry Archeologist";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
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
        super.kill();
    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;
        if (!healDone && !heal && health <= tenPers) {
            heal = true;
            entity.setAI(false);
            Location l = entity.getLocation();
            l.setPitch(90);
            entity.teleport(l);
            new BukkitRunnable() {
                int times;

                @Override
                public void run() {
                    if (entity == null || entity.isDead()) {
                        cancel();
                        return;
                    }
                    times++;

                    entity.getWorld().spawn(entity.getLocation(), ThrownPotion.class, splashPotion -> {
                        ItemStack item = new ItemStack(Material.SPLASH_POTION);
                        PotionMeta meta = (PotionMeta) item.getItemMeta();
                        meta.setColor(Color.RED);
                        item.setItemMeta(meta);
                        splashPotion.setItem(item);
                    });
                    health += tenPers * 2;
                    entity.getWorld().spawnParticle(Particle.HEART, entity.getLocation().clone().add(0, entity.getEyeHeight() / 2, 0), 10,0.6, entity.getEyeHeight() / 2, 0.6);
                    if (times == 3) {
                        tenPers -= tenPers * 0.1;
                        cancel();
                        entity.setAI(true);
                        healDone = true;
                        heal = false;
                        cooldown = new BukkitRunnable() {
                            @Override
                            public void run() {
                                healDone = false;
                            }
                        };
                        cooldown.runTaskLater(Main.getMain(), 200);
                    }
                }
            }.runTaskTimer(Main.getMain(), 5, 10);
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
    protected int healthFromFloor(int floor, boolean master) {
        switch (floor) {
            case 1 -> {
                if (master) return 2_400_000;
                else return 9_500;
            }
            case 2 -> {
                if (master) return 1_000_000;
                else return 20_000;
            }
            case 3 -> {
                if (master) return 1_875_000;
                else return 44_000;
            }
            case 4 -> {
                if (master) return 3_000_000;
                else return 75_000;
            }
            case 5 -> {
                if (master) return 4_375_000;
                else return 125_000;
            }
            case 6 -> {
                if (master) return 6_000_000;
                else return 180_000;
            }
            case 7 -> {
                if (master) return 6_900_000;
                else return 240_000;
            }
            case 8 -> {
                if (master) return 7_800_000;
                else return 320_000;
            }
            case 9 -> {
                if (master) return 12_000_000;
                else return 420_000;
            }
            case 10 -> {
                if (master) return 18_000_000;
                else return 900_000;
            }
            default -> throw new IllegalArgumentException("Not a valid floor!");
        }
    }

    @Override
    protected int damageFromFloor(int floor, boolean master) {
        switch (floor) {
            case 9 -> {
                if (master) return 184_000;
                else return 8_400;
            }
            case 10 -> {
                if (master) return 184_000;
                else return 17_600;
            }
            default -> throw new IllegalArgumentException("Not a valid floor!");
        }
    }

    @Override
    protected double defenseFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 1, 2 -> 900;
            case 3 -> 1000;
            case 4 -> 1050;
            case 5 -> 1100;
            case 6 -> 1150;
            case 7, 8, 9, 10 -> 1200;
            default -> throw new IllegalArgumentException("Not a valid floor!");
        };
    }

    @Override
    public void updateNameTag() {
        DiguestMobsManager.getDiguested.get(entity).setName("§c" + getName() + " §a" + Tools.toShortNumber(getHealth()));
        super.updateNameTag();
    }

    private static class SBEntity extends EntitySkeleton {

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
        public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
            if (entityliving instanceof EntityPlayer pl && SkyblockPlayer.getSkyblockPlayer(pl.getBukkitEntity()) != null)
                return super.setGoalTarget(entityliving, reason, fireEvent);
            return false;
        }

        @Override
        protected void initPathfinder() {

            super.initPathfinder();
        }
    }

    private static Location getLocation(EntityLiving entityLiving) {
        return Bukkit.getEntity(entityLiving.getUniqueID()).getLocation();
    }
}
