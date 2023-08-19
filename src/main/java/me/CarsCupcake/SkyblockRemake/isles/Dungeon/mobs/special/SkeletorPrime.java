package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.special;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonSkeleton;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntitySkeleton;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class SkeletorPrime extends DungeonSkeleton {
    private LivingEntity entity;
    public SkeletorPrime(int floor, boolean master) {
        super(floor, master);
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        SBEntity e = new SBEntity(loc);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(e);
        entity = (LivingEntity) e.getBukkitEntity();
        PlayerDisguise disguise = new PlayerDisguise(entity, "ewogICJ0aW1lc3RhbXAiIDogMTYwMDc3NTY0NTk5OSwKICAicHJvZmlsZUlkIiA6ICJiZWNkZGIyOGEyYzg0OWI0YTliMDkyMmE1ODA1MTQyMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdFR2IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFiZDNhNGY4ZTIyZGIxNWU0ODZhY2FjNWQ0NTZmZjM1ZjM0ZWQ5YWI1YzY0ZWVjMDU3YWZkMjg2NmQ1NTk0MDEiCiAgICB9CiAgfQp9",
                "bkd1w8Lzr1TL+oMkoE2pflrNwAGgbYNzUSt61YgdGJk+FyI93yZ2G+WLgSFCFPMNGlYfB9a87EtNDP1c1iCXkpQd0/nEOQN5/dkl74BRS0XMgVFdD5K1xR7fnlKt0pnHLCcM4Rnr8AJd7vOhBRi2Lr9q7IeIn13bDImXLtJH0OS7hbW8nHL1PeLNGmiBMZI8t9adWM8JdLoFU2MDL6xottFdJuj8gypTyq1GhEErTrJT2LuSqwZt8gi3Mjavct9omQtR6hPViP5VY/wPI/C3LDODamwIr4/vlTZikM3OnmE5CeY8TAgpmGn97rpwSSTE1HuZvZyzdD6n4xyuLw49qw8REmry8SaSO1IbF5yfsYhc4MmQltiEqvARGwC4dTVXWNQVbCbttzyuVYK0ZsGQYELqj7r0gfw3P11/gEVeuywjeTZSUfaTdu7auLb9FXDdURBXs4fTflWA+66ePpSKHjUCGG/6x7qUzjiSbIA4O+OvCSjRxzJMnSrJH/mVuxDItqBt2AtBnO7XD5zWAoFzm3HaIej3vmt5P7OVfF+IZCyW3EUps5D08OdGAytZQGwmrs72qYRc+2JwiJ6XGC7iwrwYhco73nzMKKui5mTNxnKsnWhCxffRZveNfVf5F2GRd/uG4tSdu5JvGi+MLiG5WSnC8Se5gOPuh8pKDcPcIh4=");
        disguise.toggleCustomSoundEffects();
        entity.getEquipment().setItemInMainHand(new ItemStack(Material.BONE));
        entity.setRemoveWhenFarAway(false);
        entity.setAI(true);
        SkyblockEntity.livingEntity.addEntity(entity, this);
    }

    @Override
    public String getName() {
        return "Skeletor";
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
    public int getTrueDamage() {
        return 0;
    }

    @Override
    protected int healthFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 6 -> (master) ? 17_373_736 : 1_515_151;
            case 7 -> (master) ? 20_606_060 : 3_131_313;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected int damageFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 6 -> (master) ? 176_000 : 9_600;
            case 7 -> (master) ? 192_000 : 20_000;
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
        public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
            if (entityliving instanceof EntityPlayer pl && SkyblockPlayer.getSkyblockPlayer(pl.getBukkitEntity()) != null)
                return super.setGoalTarget(entityliving, reason, fireEvent);
            return false;
        }
    }
}
