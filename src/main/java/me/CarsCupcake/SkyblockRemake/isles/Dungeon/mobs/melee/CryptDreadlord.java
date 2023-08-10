package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.melee;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonMob;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.minecraft.network.protocol.game.PacketPlayOutAnimation;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntitySkeleton;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class CryptDreadlord extends DungeonMob {
    private LivingEntity entity;
    private SBEntity craftEntity;
    private final BukkitRunnable r = new BukkitRunnable() {
        int cd = 0;
        int shots = 0;
        @Override
        public void run() {
            if(cd != 0) cd--;
            if (craftEntity.getGoalTarget() != null && Bukkit.getEntity(craftEntity.getGoalTarget().getUniqueID()) != null && getLocation(craftEntity.getGoalTarget()).distance(entity.getLocation()) < 5.5) {
                if (entity.getEquipment().getItem(EquipmentSlot.HAND).getType() != Material.DIAMOND_SWORD)
                    entity.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.DIAMOND_SWORD));
            } else if (cd == 0) {
                if(shots > 10) {
                    cd = 10;
                    shots = 0;
                }
                else {
                    EntityLiving o = craftEntity.getGoalTarget();
                    if(o == null) return;
                    LivingEntity e = (LivingEntity) o.getBukkitEntity();
                    if(e == null) return;
                    if(!(e instanceof Player)) return;
                    Location l = entity.getEyeLocation();
                    Location pL = e.getLocation();
                    Vector dir = pL.toVector().subtract(pL.clone().add(0, 0.5, 0).toVector());
                    dir = dir.normalize();
                    l.getWorld().spawn(l.add(dir), WitherSkull.class, skull -> skull.setShooter(entity));
                    if(e instanceof Player p) {
                        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(p);
                        player.getHandle().b.sendPacket(new PacketPlayOutAnimation(craftEntity, 0));
                    }
                    shots++;
                }
            }
        }
    };

    public CryptDreadlord(int floor, boolean master) {
        super(floor, master);
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        craftEntity = new CryptDreadlord.SBEntity(loc);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(craftEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);

        entity = (LivingEntity) craftEntity.getBukkitEntity();
        entity.setAI(true);
        entity.setRemoveWhenFarAway(false);
        entity.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.DIAMOND_SWORD));
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
        new PlayerDisguise(entity, "ewogICJ0aW1lc3RhbXAiIDogMTU5OTMwNTMxNjAwOSwKICAicHJvZmlsZUlkIiA6ICJhNzdkNmQ2YmFjOWE0NzY3YTFhNzU1NjYxOTllYmY5MiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwOEJFRDUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjZmMzQ5MjcwYTNiODUxODk2Y2RhZDg0MmY1ZWVjNmUxNDBiZDkxMTliNzVjMDc0OTU1YzNiZTc4NjVlMjdjNyIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9", "oAlG5VcLjYkfnvuec8pu291y2ZPbCUSg80sQGpXQun1cV/GxEZy1LvbJBBM0RlbNBKPOpj42MUGcROfG82/4lyIDsz9nC0IhDFJnvOTWMm/L0+UikIYoUAmuCAP2YScGlNQ5GARyHFd82KLU1ZSAMGraqb/lFCgSTPe9p669036zTKHbm6obLlHCO9vj1t8l3Y5WlDjpbeMmCcOHYztIQs1/ZPsvvoy4DgqfHM+FUMP2qMPb4Cd81VvzkCt3xrtrW5oCNCeDtycjG3j+sL8TNXjHrGp6Uk22W5zFYUnpSlSeAxB6i9IaE6v6dkKIfu8LsPSWvuEiIa4czsRKsXhK4rJ6pUtcV9WMh1qTgEBTzX6Xc3TAVCeeSKkq5HZyBDvWhrTKZeSoXnsMRrNtwJKccyVOklSyK7P18KvmNg3WIfwy6pOdzi5Gb9dXr31Em82VSvQ0Q4Mzp+EGmMWjYfymiKMRyqjbaaHlgRBxwKTrj/m2l9B1MgfEGORg6KuubmQ6FeFKauPHv47eTc+rAJEKxDGLu0VL9FmhPAszYJ3tHFIT7gjuTjkSfd/kzjnlS/omE1IHeFJwf9rS8+ozSay7QonySqGal7fnvG3Y5vXDoHIfg9XWIEmE8fmxdBW5em4HMPJEtoEBA1bA8mcb8muTnFL8xw9wQxJOBh0X3g3FdbE=");
        SkyblockEntity.livingEntity.addEntity(entity, this);
        r.runTaskTimer(Main.getMain(), 5, 5);
    }

    @Override
    public String getName() {
        return "Crypt Souleater";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
    }


    @Override
    public void kill() {
        try {
            r.cancel();
        }catch (Exception ignored){}
        super.kill();
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
        return switch (floor) {
            case 0 -> 19_000;
            case 1 -> (master) ? 1_050_000 : 32_000;
            case 2 -> (master) ? 2_025_000 : 65_000;
            case 3 -> (master) ? 3_300_000 : 118_000;
            case 4 -> (master) ? 4_875_000 : 210_000;
            case 5 -> (master) ? 6_750_000 : 270_000;
            case 6 -> (master) ? 10_200_000 : 390_000;
            case 7 -> (master) ? 20_000_000 : 840_000;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected int damageFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 0 -> 520;
            case 1 -> (master) ? 16_000 : 760;
            case 2 -> (master) ? 27_750 : 1_120;
            case 3 -> (master) ? 42_000 : 1_880;
            case 4 -> (master) ? 58_750 : 2_840;
            case 5 -> (master) ? 78_000 : 4_080;
            case 6 -> (master) ? 100_000 : 5_680;
            case 7 -> (master) ? 114_000 : 11_840;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected double defenseFromFloor(int floor, boolean master) {
        return 0;
    }

    private static class SBEntity extends EntitySkeleton {

        public SBEntity(Location location) {
            super(EntityTypes.aB, ((CraftWorld) location.getWorld()).getHandle());
            setAggressive(true);
            setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        }

        @Override
        public void a(EntityLiving entityliving, float f) {
            LivingEntity e = (LivingEntity) Bukkit.getEntity(this.aj);
            Location l = e.getEyeLocation();
            Location pL = new Location(l.getWorld(), locX(), locY(), locZ());
            Vector dir = pL.toVector().subtract(pL.clone().add(0, 0.5, 0).toVector());
            dir = dir.normalize();
            l.getWorld().spawn(l.clone().add(dir.multiply(0.3)), WitherSkull.class, skull -> skull.setShooter(e));
        }
        @Override
        public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
            if (entityliving instanceof EntityPlayer pl && SkyblockPlayer.getSkyblockPlayer(pl.getBukkitEntity()) != null)
                return super.setGoalTarget(entityliving, reason, fireEvent);
            return false;
        }

        @Override
        public void tick() {
            super.tick();
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
