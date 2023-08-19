package me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.special;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.EntityAtributes;
import me.CarsCupcake.SkyblockRemake.Skyblock.FinalDamageDesider;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.DungeonMob;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;

@EntityAtributes(EntityAtributes.Attributes.DinnerboneEntity)
public class Fel extends DungeonMob implements FinalDamageDesider {
    private LivingEntity entity;
    private ArmorStand nametag;

    public Fel(int floor, boolean master) {
        super(floor, master);
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Enderman.class, enderman -> {
            enderman.setAI(false);
            enderman.setInvisible(true);
            enderman.setCustomNameVisible(false);
            enderman.setCustomName("Dinnerbone");
        });
        new EntityRunnable() {
            @Override
            public void run() {
                if(nametag != null) nametag.teleport(entity.getLocation().add(0, 2.9, 0));
                else if (entity.getNearbyEntities(3, 3, 3).stream().anyMatch(entity1 -> entity1 instanceof Player)) {
                    nametag = entity.getWorld().spawn(entity.getLocation().add(0, 2.9, 0), ArmorStand.class, armorStand -> {
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName(getBaseName(Fel.this));
                        armorStand.setMarker(true);
                        armorStand.setInvisible(true);
                        armorStand.setBasePlate(false);
                    });
                    entity.setAI(true);
                    entity.setInvisible(false);
                }
            }

            @Override
            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
                nametag.remove();
            }
        }.runTaskTimer(this, 10, 1);
        SkyblockEntity.livingEntity.addEntity(entity, this);
    }

    @Override
    public String getName() {
        return "Fel";
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName("Dinnerbone");
        entity.setCustomNameVisible(false);
        if (nametag != null)
            nametag.setCustomName("§c" + getName() + " §a" + Tools.toShortNumber(getHealth()) + ((stared) ? " §6✯" : ""));
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
            case 5 -> (master) ? 45_000_000 : 600_000;
            case 6 -> (master) ? 80_000_000 : 700_000;
            case 7 -> (master) ? 160_000_000 : 1_500_000;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected int damageFromFloor(int floor, boolean master) {
        return switch (floor) {
            case 5 -> (master) ? 150_000 : 8_000;
            case 6 -> (master) ? 200_000 : 9_600;
            case 7 -> (master) ? 240_000 : 20_000;
            default -> throw new IllegalStateException("Unexpected value: " + floor);
        };
    }

    @Override
    protected double defenseFromFloor(int floor, boolean master) {
        return 0;
    }

    @Override
    public double getFinalDamage(SkyblockPlayer player, double damage) {
        return (nametag == null) ? 0 : damage;
    }
}
