package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.entitys;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.FinalDamageDesider;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraEntity;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import java.util.HashMap;

public class Blight extends KuudraEntity implements FinalDamageDesider {
    private static final Color green = Color.fromBGR(0x268105);
    private LivingEntity entity;
    private int stage = 0;

    public Blight(int tier) {
        super(tier);
    }

    @Override
    public int getMaxHealth() {
        return switch (tier) {
            case  2 -> 4_500_000;
            case  3 -> 5_500_000;
            case  4 -> 7_000_000;
            case  5 -> 9_000_000;
            default -> 4_000_000;
        };
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return switch (tier) {
            case  2 -> 6_000;
            case  3 -> 7_000;
            case  4 -> 8_000;
            case  5 -> 10_000;
            default -> 4_500;
        };
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class, zombie -> {
            zombie.getEquipment().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(green).build());
            zombie.getEquipment().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(green).build());
            zombie.getEquipment().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(green).build());
            zombie.setCustomNameVisible(true);
        });
        livingEntity.addEntity(entity, this);
        new EntityRunnable() {
            @Override
            public void run() {
                if(stage == 2) stage = 0; else stage++;
                switch (stage){
                    case 0 -> setColor(green);
                    case 1 -> setColor(Color.YELLOW);
                    case 2 -> setColor(Color.RED);
                }
            }
        }.runTaskTimer(this, 100, 100);
    }
    public void setColor(Color color){
        entity.getEquipment().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(color).build());
        entity.getEquipment().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(color).build());
        entity.getEquipment().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(color).build());
    }

    @Override
    public String getName() {
        return "Kuudra Follower";
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName(getBaseName(this));
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getTokens() {
        return 4;
    }

    @Override
    public double getFinalDamage(SkyblockPlayer player, double damage) {
        return (stage == 2) ? 0 : damage;
    }
}
