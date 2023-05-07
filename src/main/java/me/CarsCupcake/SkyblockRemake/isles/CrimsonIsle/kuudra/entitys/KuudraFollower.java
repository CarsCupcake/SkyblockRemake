package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.entitys;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra.KuudraEntity;
import me.CarsCupcake.SkyblockRemake.utils.Inventorys.Items.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import java.util.HashMap;

public class KuudraFollower extends KuudraEntity {
    private LivingEntity entity;
    @Override
    public int getMaxHealth() {
        return 12_500_000;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public int getDamage() {
        return 9000;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class, zombie -> {
            zombie.getEquipment().setHelmet(new ItemBuilder(Material.PLAYER_HEAD).setHead("http://textures.minecraft.net/texture/935541523f14c78d8de98cc296c798f0b867ba85344ed77f6dae12455a174").build());
            zombie.getEquipment().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(Color.fromBGR(0x268105)).build());
            zombie.getEquipment().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherColor(Color.fromBGR(0x268105)).build());
            zombie.getEquipment().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(Color.fromBGR(0x268105)).build());
            zombie.setCustomNameVisible(true);
        });
        livingEntity.addEntity(entity, this);
    }

    @Override
    public String getName() {
        return "Kuudra Follower";
    }

    @Override
    public HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player) {
        return null;
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
    public int getTrueDamage() {
        return 0;
    }

    @Override
    public int getTokens() {
        return 0;
    }
}
