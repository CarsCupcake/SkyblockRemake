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
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftWitherSkeleton;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class WitherMiner extends SkyblockEntity {
    private int health = 10000000;
    private LivingEntity entity;
    @Override
    public int getMaxHealth() {
        return 10000000;
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

           w.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_PICKAXE));
           w.addScoreboardTag("combatxp:125");

        });
        ((CraftWitherSkeleton)entity).setTarget((Player) Bukkit.getOnlinePlayers().toArray()[ new Random().nextInt(Bukkit.getOnlinePlayers().size())]);
        SkyblockEntity.livingEntity.put(entity,this);

    }


    @Override
    public String getName() {
        return "Wither Miner";
    }

    @Override
    public HashMap<ItemManager,Integer> getDrops(SkyblockPlayer player) {
        return null;
    }

    @Override
    public void updateNameTag() {
        entity.setCustomName("§cWither Miner §a" + Tools.toShortNumber(health));
    }

    @Override
    public void kill() {
        if(entity.getKiller() != null){
            Location lo = entity.getLocation().setDirection(entity.getKiller().getEyeLocation().subtract(0,0.5,0).subtract(entity.getEyeLocation()).toVector());
            entity.teleport(lo);
            entity.launchProjectile(WitherSkull.class);
        }else {
            Player nearest = null;
            for(Player p : Bukkit.getServer().getOnlinePlayers()){
                if(nearest == null){
                    nearest = p;
                }else{
                    if(nearest.getLocation().distance(entity.getLocation()) > p.getLocation().distance(entity.getLocation()))
                        nearest = p;
                }
            }
            Location lo = entity.getLocation().setDirection(nearest.getEyeLocation().subtract(0,0.5,0).subtract(entity.getEyeLocation()).toVector());
            entity.teleport(lo);
            entity.launchProjectile(WitherSkull.class);
        }
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
