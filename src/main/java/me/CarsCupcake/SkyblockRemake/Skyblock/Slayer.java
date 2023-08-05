package me.CarsCupcake.SkyblockRemake.Skyblock;

import me.CarsCupcake.SkyblockRemake.Items.ItemManager;
import me.CarsCupcake.SkyblockRemake.utils.Assert;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;

public abstract class Slayer extends SkyblockEntity{
    private static final HashMap<SkyblockPlayer, Slayer> slayers = new HashMap<>();
    protected final SkyblockPlayer owner;
    public Slayer(SkyblockPlayer player){
        owner = player;
    }

    public static void summonSlayer(Location location, Slayer slayer){
        Assert.state(!slayers.containsKey(slayer.owner), "Multiple Slayers!");
        slayer.spawn(location);
        slayers.put(slayer.owner, slayer);
        SkyblockEntity.livingEntity.addEntity(slayer.getEntity(), slayer);
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

    public abstract boolean hasNoKB();

    public abstract int getTrueDamage();

    public static String getBaseName(Slayer slayer){
        return "§c" + Character.toChars(9760)[0] + " §b" + slayer.getName()
                + " §a" + Tools.toShortNumber(slayer.getHealth()) + "§c" + Stats.Health.getSymbol();
    }
}
