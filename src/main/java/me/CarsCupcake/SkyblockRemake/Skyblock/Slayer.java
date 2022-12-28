package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;

public abstract class Slayer extends SkyblockEntity{
    private static HashMap<SkyblockPlayer, Slayer> slayers = new HashMap<>();
    protected final SkyblockPlayer owner;
    public Slayer(SkyblockPlayer player){
        owner = player;
    }

    public static void summonSlayer(Location location, Slayer slayer){
        slayer.spawn(location);
        slayers.put(slayer.owner, slayer);
        SkyblockEntity.livingEntity.put(slayer.getEntity(), slayer);
    }
    public SkyblockPlayer getOwner(){
        return owner;
    }

    public static Slayer getSlayer(SkyblockPlayer player){
        return slayers.get(player);
    }
    public static boolean hasActiveSlayer(SkyblockPlayer player){
        return slayers.containsKey(player);
    }

    public abstract LivingEntity getEntity();

    public abstract void setHealth(int i);

    public abstract int getDamage();

    public abstract void spawn(Location loc);

    public abstract String getName();

    public abstract HashMap<ItemManager, Integer> getDrops(SkyblockPlayer player);

    public abstract void updateNameTag();

    public abstract void kill();

    public abstract void damage(double damage, SkyblockPlayer player);

    public abstract boolean hasNoKB();

    public abstract int getTrueDamage();
}
