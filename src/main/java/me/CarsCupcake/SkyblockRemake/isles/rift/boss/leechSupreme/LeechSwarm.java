package me.CarsCupcake.SkyblockRemake.isles.rift.boss.leechSupreme;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.isles.rift.entitys.RiftEntity;
import me.CarsCupcake.SkyblockRemake.utils.Inventories.Items.ItemBuilder;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.utils.runnable.EntityRunnable;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class LeechSwarm extends RiftEntity {
    private static final String headTexture = "ewogICJ0aW1lc3RhbXAiIDogMTYwNDYxNDc0Mjg3NywKICAicHJvZmlsZUlkIiA6ICI0ZDcwNDg2ZjUwOTI0ZDMzODZiYmZjOWMxMmJhYjRhZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJzaXJGYWJpb3pzY2hlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Q5NzZmZmRkMjkyYzM0ZmRhYmIyYWNmYTYyZGJmZDRmMGQxMzAwMDg3ODU4MDliZTAxNmQzZDcyNTdiYjMxZjIiCiAgICB9CiAgfQp9";
    private LivingEntity entity;
    @Override
    public int getMaxHealth() {
        return 45;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, Zombie.class, zombie -> {
            zombie.getEquipment().setHelmet(Tools.getCustomTexturedHeadFromSkullValue(headTexture));
            zombie.getEquipment().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherColor(Color.fromBGR(14, 56, 17)).build());
            zombie.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
            zombie.getEquipment().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherColor(Color.BLACK).build());
            zombie.setAdult();
        });
        SkyblockEntity.livingEntity.addEntity(entity, this);
        entity.setVelocity(BossFightManager.getMiddle().toVector().subtract(entity.getLocation().toVector()).normalize().setY(1));
        new EntityRunnable() {
            @Override
            public void run() {
                if(BossFightManager.getMiddle().distance(entity.getLocation()) > 16)
                    entity.setVelocity(BossFightManager.getMiddle().toVector().subtract(entity.getLocation().toVector()).normalize().setY(0.5));
            }
        }.runTaskTimer(Main.getMain(), 20, 1);
    }

    @Override
    public String getName() {
        return "Leech Swarm";
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
    public int getRiftTimeDamage() {
        return 4;
    }

    @Override
    public double getHeartsDamage() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 3;
    }
    @Override
    public void kill() {
        super.kill();
        BossFightManager.getInstance().leechSwarmKill();
    }
}
