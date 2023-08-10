package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.special;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonSkeleton;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
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

public class Skeletor extends DungeonSkeleton {
    private LivingEntity entity;
    public Skeletor(int floor, boolean master) {
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
        entity.getEquipment().setItemInMainHand(new ItemStack(Material.BONE));
        entity.getEquipment().setHelmet(Tools.CustomHeadTexture("http://textures.minecraft.net/texture/89d074ad9b9971879eb325bddff3675f7224856bd6d569fc8d483c133d73005d"));
        entity.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        entity.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        entity.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
        entity.setRemoveWhenFarAway(false);
        SkyblockEntity.livingEntity.addEntity(entity, this);
    }

    @Override
    public String getName() {
        return "Skeletor";
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
            case 3 -> (master) ? 6_900_000 : 250_000;
            case 4 -> (master) ? 7_800_000 : 500_000;
            case 5 -> (master) ? 9_000_000 : 700_000;
            case 6 -> (master) ? 10_200_000 : 1_000_000;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected int damageFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 3 -> (master) ? 90_000 : 3_560;
            case 4 -> (master) ? 99_000 : 4_640;
            case 5 -> (master) ? 108_000 : 6_400;
            case 6 -> (master) ? 117_000 : 8_040;
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
