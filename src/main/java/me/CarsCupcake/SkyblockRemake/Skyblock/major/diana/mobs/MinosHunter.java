package me.CarsCupcake.SkyblockRemake.Skyblock.major.diana.mobs;

import me.CarsCupcake.SkyblockRemake.Items.ItemRarity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

public class MinosHunter extends SkyblockEntity {
    private int maxHealth = 1;
    private final int damage;
    private LivingEntity entity;
    public MinosHunter(ItemRarity rarity) {
        switch (rarity) {
            case RARE, EPIC -> {
                maxHealth = 100_000;
                damage = 850;
            }
            case LEGENDARY -> {
                maxHealth = 500_000;
                damage = 2_700;
            }
            default -> {
                maxHealth = 2_000;
                damage = 300;
            }
        }
        health = maxHealth;

    }
    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class, zombie -> {
            zombie.getEquipment().setHelmet(new ItemBuilder(Material.PLAYER_HEAD).build());
            zombie.getEquipment().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(Color.RED).setGlint(true).build());
            zombie.getEquipment().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(Color.RED).setGlint(true).build());
            zombie.getEquipment().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(Color.BLACK).setGlint(true).build());
            zombie.getEquipment().setItemInMainHand(new ItemBuilder(Material.WOODEN_SWORD).build());
            zombie.setAdult();
            zombie.setCustomNameVisible(true);
        });
        SkyblockEntity.livingEntity.addEntity(entity, this);
    }

    @Override
    public String getName() {
        return "Minos Hunter";
    }

    @Override
    public boolean hasNoKB() {
        return false;
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
