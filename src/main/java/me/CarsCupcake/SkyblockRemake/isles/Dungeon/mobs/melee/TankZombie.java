package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.melee;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonMob;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class TankZombie extends DungeonMob {
    private LivingEntity entity;

    public TankZombie(int floor, boolean master) {
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
        entity.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.BOW));
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
        entity.getEquipment().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setLeatherColor(Color.YELLOW).build());
        entity.getEquipment().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(Color.YELLOW).build());
        entity.getEquipment().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(Color.YELLOW).build());
        entity.getEquipment().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(Color.YELLOW).build());
        SkyblockEntity.livingEntity.addEntity(entity, this);
    }

    @Override
    public String getName() {
        return "Tank Zombie";
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
            case 0 -> (master) ? 105_000 : 1_500;
            case 1 -> (master) ? 150_000 : 2_500;
            case 2 -> (master) ? 160_000 : 4_500;
            case 3 -> (master) ? 225_000 : 8_000;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected int damageFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 0 -> (master) ? 11_000 : 320;
            case 1 -> (master) ? 18_000 : 494;
            case 2 -> (master) ? 25_000 : 760;
            case 3 -> (master) ? 32_500 : 1_320;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected double defenseFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 0 -> 2000;
            case 1 -> 2100;
            case 2 -> 2200;
            case 3 -> 2300;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    private static class SBEntity extends EntityZombie {

        public SBEntity(Location location) {
            super(EntityTypes.be, ((CraftWorld) location.getWorld()).getHandle());
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
