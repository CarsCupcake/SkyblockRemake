package me.CarsCupcake.SkyblockRemake.Settings;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.isles.AuctionHouse.AuctionHouse;
import me.CarsCupcake.SkyblockRemake.isles.Bazaar.BazaarListener;
import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;

public class InfoManager {
    private static boolean isClickCooldownEnabled;
    @Getter
    private static boolean unlimitedMinions;
    private static CustomConfig config;

    public InfoManager(){
        config = new CustomConfig("settings");
        isClickCooldownEnabled = getValue("clickCooldown", true);
        unlimitedMinions = getValue("unlimitedMinions", false);
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
    public static void setClickCooldownEnabled(boolean b){
        setValue("clickCooldown", b);
        isClickCooldownEnabled = b;
    }
    public static void setUnlimitedMinion(boolean b){
        setValue("unlimitedMinions", b);
        unlimitedMinions = b;
    }
    public static boolean isClickCooldownEnabled(){
        return isClickCooldownEnabled;
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
