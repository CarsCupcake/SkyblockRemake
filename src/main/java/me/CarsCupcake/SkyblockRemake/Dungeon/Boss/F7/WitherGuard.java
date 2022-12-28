package me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockEntity;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftWither;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftWitherSkeleton;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class WitherGuard extends SkyblockEntity {
    private int health = 3500000;
    private LivingEntity entity;
    @Override
    public int getMaxHealth() {
        return 3500000;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }


    @Override
    public int getDamage() {
        return 20000;
    }

    @Override
    public void spawn(Location loc) {
        entity = loc.getWorld().spawn(loc, WitherSkeleton.class, w->{
            ItemStack stack = new ItemStack(Material.BOW);
            stack.addEnchantment(Enchantment.ARROW_FIRE,1);
           w.getEquipment().setItemInMainHand(stack);
           w.addScoreboardTag("combatxp:125");

        });
        Attributable zombieAt = entity;
        AttributeInstance attributeSpeed = zombieAt.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        attributeSpeed.setBaseValue(0);
        SkyblockEntity.livingEntity.put(entity, this);

        ((CraftWitherSkeleton)entity).setTarget((Player) Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())]);

    }


    @Override
    public String getName() {
        return "Wither Guard";
    }

    @Override
    public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName("§cWither Guard §a" + Tools.toShortNumber(health));
    }

    @Override
    public void kill() {

    }

    @Override
    public void damage(double damage, SkyblockPlayer player) {
        health -= damage;
    }

    @Override
    public boolean hasNoKB() {
        return true;
    }

    @Override
    public int getTrueDamage() {
        return 0;
    }
}
