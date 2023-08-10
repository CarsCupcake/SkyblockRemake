package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.melee;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonMob;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.SinusMovement;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.HashMap;

public class ZombieKnight extends DungeonMob {
    private boolean hasBone = true;
    private LivingEntity entity;

    public ZombieKnight(int floor, boolean master) {
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
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
        entity.getEquipment().setHelmet(new ItemBuilder(Material.GOLDEN_HELMET).build());
        entity.getEquipment().setChestplate(new ItemBuilder(Material.GOLDEN_CHESTPLATE).build());
        entity.getEquipment().setLeggings(new ItemBuilder(Material.GOLDEN_LEGGINGS).build());
        entity.getEquipment().setBoots(new ItemBuilder(Material.GOLDEN_BOOTS).build());
        SkyblockEntity.livingEntity.addEntity(entity, this);
    }

    @Override
    public String getName() {
        return "Zombie Knight";
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
            case 0 -> 12_000;
            case 1 -> (master) ? 850_000 : 19_000;
            case 2 -> (master) ? 1_500_000 : 26_000;
            case 3 -> (master) ? 2_300_000 : 40_000;
            case 4 -> (master) ? 3_250_000 : 82_000;
            case 5 -> (master) ? 4_350_000 : 123_000;
            case 6 -> (master) ? 6_400_000 : 200_000;
            case 7 -> (master) ? 13_000_000 : 420_000;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected int damageFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 0 -> 520;
            case 1 -> (master) ? 15_000 : 920;
            case 2 -> (master) ? 24_750 : 1_200;
            case 3 -> (master) ? 36_000 : 1_800;
            case 4 -> (master) ? 48_750 : 2_800;
            case 5 -> (master) ? 63_000 : 3_760;
            case 6 -> (master) ? 80_000 : 4_720;
            case 7 -> (master) ? 90_000 : 10_400;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected double defenseFromFloor(int floor, boolean master) {
        return 0;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName("§c" + getName() + " §a" + Tools.toShortNumber(getHealth()));
        super.updateNameTag();
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
