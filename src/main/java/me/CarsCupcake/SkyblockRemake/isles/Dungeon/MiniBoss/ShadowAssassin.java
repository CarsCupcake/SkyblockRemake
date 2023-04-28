package me.CarsCupcake.SkyblockRemake.isles.Dungeon.MiniBoss;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.DiguestMobsManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonMob;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
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
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ShadowAssassin extends DungeonMob {
    private LivingEntity entity;
    private SBEntity craftEntity;
    private BukkitRunnable cooldown;
    private final BukkitRunnable r = new BukkitRunnable() {
        int teleportCooldown = 0;

        @Override
        public void run() {
            if (craftEntity.getGoalTarget() != null && Bukkit.getEntity(craftEntity.getGoalTarget().getUniqueID()) != null && getLocation(craftEntity.getGoalTarget()).distance(entity.getLocation()) < 1) {
            } else {
                if (teleportCooldown >= 40) {
                    double nX;
                    double nZ;
                    float nang = craftEntity.getGoalTarget().getYRot() + 90;
                    if(nang < 0) nang += 360;
                    nX = Math.cos(Math.toRadians(nang));
                    nZ = Math.sin(Math.toRadians(nang));

                    Location shadowStep = new Location(
                            craftEntity.getGoalTarget().getBukkitEntity().getWorld(),
                            craftEntity.getGoalTarget().getBukkitEntity().getLocation().getX() - nX,
                            craftEntity.getGoalTarget().getBukkitEntity().getLocation().getY(),
                            craftEntity.getGoalTarget().getBukkitEntity().getLocation().getZ() - nZ,
                            craftEntity.getGoalTarget().getBukkitEntity().getLocation().getYaw(),
                            craftEntity.getGoalTarget().getBukkitEntity().getLocation().getPitch()
                    );

                    craftEntity.getBukkitEntity().teleport(shadowStep);

                    teleportCooldown = 0;
                    return;
                } else teleportCooldown++;
            } teleportCooldown++;
        }
    };

    public ShadowAssassin(int floor, boolean master) {
        super(floor, master);
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        craftEntity = new ShadowAssassin.SBEntity(loc);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(craftEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        entity = (LivingEntity) craftEntity.getBukkitEntity();
        entity.setAI(true);
        entity.setRemoveWhenFarAway(false);
        entity.getEquipment().setItem(EquipmentSlot.HAND, new ItemBuilder(Material.IRON_SWORD).build());
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
        DiguestMobsManager.createEntity(entity, loc, "SA", "ewogICJ0aW1lc3RhbXAiIDogMTU4OTEzNzY1ODgxOSwKICAicHJvZmlsZUlkIiA6ICJlM2I0NDVjODQ3ZjU0OGZiOGM4ZmEzZjFmN2VmYmE4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaW5pRGlnZ2VyVGVzdCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zMzk5ZTAwZjQwNDQxMWU0NjVkNzQzODhkZjEzMmQ1MWZlODY4ZWNmODZmMWMwNzNmYWZmYTFkOTE3MmVjMGYzIgogICAgfQogIH0KfQ==", "Zc+egPfvdkkutM0qR13oIUCXYuLIRkXGLuKutWxSUbW4H7jujEIQD+aKW+Yy9JekTbaqehvp+OArXMkjRs9h8o0ZGAJY/xlXF3OVzfBA7hIvrtx7cSaIRIr5pfjcBCUe0m1l8shByayaCtu/q11QZzZCX1+ZHKgghG9W95EnkmyAESHNjIXFBCMxPCElGfjEIsKwdt48NIlDiCmx3pUSCr3AnL8FvHrG4CMNZK+hhMStOV8nLq7l6MppsUUmRWkL0DVDTEh9BHzAWw3pBOvwP3r9Ax/5amBDrB1sN8vSa/bfuMxlxH11UGt3kb04SOuxYuMCCSCzKq0xSzlP5H5HfW3wSSk9T2zcpyEZgsIud28FZzBjcdgB+Umq0Cp7IybAi6xFbjC8zNgh+y24sNv6F4XJzv8v5eB1AwUZXStDrqrIpTb1XHIJurRNBbyXh3q8XuR2ECmpZAwupKtxWDo5og6IbigQEjKFjMrmvgnUd1dukcdro+w/p2IgmGHVXoR6jtN1YNnpldILDJiql8R097Nco3wU0crU5M1qfqkHHEvOOrf7iOZRF+psNaiJSZuBNmmTdS+13Q+nNwoTfGERFb8Em3YxKFs5j9l4a7HxbW2YvH93sGHCxuPgd9bXJ9KPh6Yp9Uch1cDB/uF4FfOwN7WMQ8ON7IhAHAegjLththc=");
        SkyblockEntity.livingEntity.put(entity, this);
        updateNameTag();
        r.runTaskTimer(Main.getMain(), 5, 5);
    }

    @Override
    public String getName() {
        return "Shadow Assassin";
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
