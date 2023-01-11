package me.CarsCupcake.SkyblockRemake.Settings;

import me.CarsCupcake.SkyblockRemake.AuctionHouse.AuctionHouse;
import me.CarsCupcake.SkyblockRemake.Bazaar.BazaarListener;
import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;

public class InfoManager {
    private static CustomConfig config;

    public InfoManager(){
        config = new CustomConfig("settings");
    }
    public static boolean isBazaarEnabled(){
        return getValue("bazaar", true);
    }
    public static void setBazaarEnabled(boolean b){
        setValue("bazaar", b);
        if(!b)
            BazaarListener.shutdownBazzar();
    }
    public static void setAhEnabled(boolean b){
        setValue("ah", b);
        if(!b)
            AuctionHouse.getInstance().shutdown();
    }
    public static boolean isSkyblockMenuEnabled(){
        return getValue("sbmenu", true);
    }
    public static void setSkyblockMenuEnabled(boolean b){
        setValue("sbmenu", b);
    }

    public static  <T> T getValue(String key, T def){
        T value = (T)config.get().get(key);
        if(config.get().get(key) == null) {
            config.get().set(key, def);
            config.save();
            config.reload();
            return def;
        }
        return value;
    }
    private static  <T> void setValue(String key, T def){
            config.get().set(key, def);
            config.save();
            config.reload();

    }
}
