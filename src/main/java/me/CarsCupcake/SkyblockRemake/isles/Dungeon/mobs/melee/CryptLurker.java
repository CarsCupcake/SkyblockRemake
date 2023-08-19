package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.melee;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.Calculator;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonMob;
import me.CarsCupcake.SkyblockRemake.utils.SinusMovement;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;
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

public class CryptLurker extends DungeonMob {
    private boolean hasBone = true;
    private LivingEntity entity;

    public CryptLurker(int floor, boolean master) {
        super(floor, master);
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        SBEntity craftEntity = new SBEntity(loc, this);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(craftEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        entity = (LivingEntity) craftEntity.getBukkitEntity();
        entity.setAI(true);
        entity.setRemoveWhenFarAway(false);
        entity.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.BONE));
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
        SkyblockEntity.livingEntity.addEntity(entity, this);
    }

    @Override
    public String getName() {
        return "Crypt Lurker";
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

    private static class SBEntity extends EntityZombie {
        private final CryptLurker lurker;

        public SBEntity(Location location, CryptLurker main) {
            super(EntityTypes.be, ((CraftWorld) location.getWorld()).getHandle());
            setAggressive(true);
            setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
            lurker = main;
        }

        @Override
        public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
            if (entityliving instanceof EntityPlayer pl && SkyblockPlayer.getSkyblockPlayer(pl.getBukkitEntity()) != null) {
                SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer(pl.getBukkitEntity());
                if (lurker.hasBone) {
                    lurker.entity.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
                    lurker.hasBone = false;
                    SinusMovement movement = new SinusMovement(1, 1, 0);
                    ArmorStand s = player.getWorld().spawn(lurker.entity.getLocation().add(0, 0.5, 0), ArmorStand.class, stand -> {
                        stand.addScoreboardTag("remove");
                        stand.setInvisible(true);
                        stand.setInvulnerable(true);
                        stand.setBasePlate(false);
                        stand.getEquipment().setItemInMainHand(new ItemStack(Material.BONE));
                    });
                    movement.move(
                            s,
                            () -> {
                                if (!s.isDead()) s.remove();
                                s.getWorld().spawnParticle(Particle.CLOUD, s.getEyeLocation().subtract(0, 0.5, 0), 1);
                            },
                            20, 0, lurker.getEntity().getEyeLocation(), player.getLocation().add(0, 0.5, 0),
                            () -> {
                                s.getWorld().getNearbyEntities(s.getEyeLocation(), 0.5, 1, 0.5).stream().filter(e -> e instanceof Player).forEach(entity -> {
                                    if (!s.isDead()) s.remove();
                                    Calculator calculator = new Calculator();
                                    calculator.entityToPlayerDamage(lurker, player);
                                    calculator.damagePlayer(player);
                                    calculator.showDamageTag(player);
                                });
                                if (!s.isDead())
                                    s.setRightArmPose(new EulerAngle(s.getRightArmPose().getX() + 0.6D, 0.0D, 0.0D));

                            }
                    );
                }
                return super.setGoalTarget(entityliving, reason, fireEvent);
            }
            return false;
        }
    }
}
