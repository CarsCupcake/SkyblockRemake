package me.CarsCupcake.SkyblockRemake.Skyblock;

import org.bukkit.Location;

import java.util.HashMap;

public abstract class Slayer implements SkyblockEntity{
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
}
