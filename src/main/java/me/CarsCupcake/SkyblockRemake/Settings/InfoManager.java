package me.CarsCupcake.SkyblockRemake.Settings;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.ServerType;
import me.CarsCupcake.SkyblockRemake.isles.AuctionHouse.AuctionHouse;
import me.CarsCupcake.SkyblockRemake.isles.Bazaar.BazaarListener;
import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import net.minecraft.network.protocol.Packet;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class InfoManager {
    private static boolean isClickCooldownEnabled;
    @Getter
    private static boolean unlimitedMinions;
    @Getter
    @Setter
    private static boolean miningFatuigeEnable = true;
    @Getter
    @Setter
    private static boolean movementLag = false;
    @Getter
    @Setter
    private static boolean packetLog = false;
    @Getter
    private static PacketLoggerFilter packetLogFilter = new PacketLoggerFilter();
    private static CustomConfig config;
    @Getter
    @Setter
    private static boolean ignoreCooldwon = false;

    public InfoManager(){
        config = new CustomConfig("settings");
        isClickCooldownEnabled = getValue("clickCooldown", true);
        unlimitedMinions = getValue("unlimitedMinions", false);
        miningFatuigeEnable = getValue("miningFatuige", ServerType.getActiveType() != ServerType.Non && ServerType.getActiveType() != ServerType.PrivateIsle);
        ignoreCooldwon = getValue("ignorecooldown", false);
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
    public static  <T> void setValue(String key, T def){
            config.get().set(key, def);
            config.save();
            config.reload();

    }
    public static class PacketLoggerFilter{
        @Getter
        private boolean in = true;
        @Getter
        private boolean out = true;
        @Getter
        @Setter
        private String search;
        @Getter
        private boolean detailed = false;
        @SneakyThrows
        private PacketLoggerFilter(){
            if(packetLogFilter != null && packetLogFilter != this) throw new IllegalAccessException("You are not allowed to call this!");
        }
        public void toggleIn(){
            in = !in;
            Main.getDebug().debug("Set In filter to " + in, false);
        }
        public void toggleOut(){
            out = !out;
            Main.getDebug().debug("Set Out filter to " + out, false);
        }
        public void reset(){
            in = true;
            out = true;
            search = null;
        }
        public void detailed(){
            detailed = !detailed;
        }
        public void printAsDetailed(Packet<?> packet){
            System.out.println("sdf" + Arrays.toString(packet.getClass().getFields()));
            for (Field f : packet.getClass().getFields()){
                int m = f.getModifiers();
                try {
                    System.out.println(" " + Modifier.toString(m) + " " + f.getName() + ": " + f.get((Modifier.isStatic(m)) ? null : packet));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
