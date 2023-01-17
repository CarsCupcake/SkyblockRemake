package me.CarsCupcake.SkyblockRemake.utils;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Time extends BukkitRunnable {
    @Getter
    private static Time instance;
    private int hour;
    private int minute;
    private int runVar;
    private String ampm = "am";
    private final boolean hasBungeeCord;



    public Time() {
        this.hour = 0;
        this.minute = 0;
        instance = this;
        hasBungeeCord = Main.getMain().getConfig().getBoolean("bungeeCordTime", false);
        load();
    }
    public void load(){
        if(hasBungeeCord){
            MessageHandler.sendMessage("requestTime");
            return;
        }
        CustomConfig c = new CustomConfig("time");
        hour = c.get().getInt("hour", 0);
        minute = c.get().getInt("minute", 0);
        runVar = c.get().getInt("phase", 0);

    }
    public void save(){
        CustomConfig c = new CustomConfig("time");
        c.get().set("hour", hour);
        c.get().set("minute", minute);
        c.get().set("phase", runVar);
        c.save();
    }


    public void incrementTime() {
        this.minute += 10;
        if (this.minute == 60) {
            this.hour++;
            this.minute = 0;
        }
        if (this.hour > 12) {
            this.hour = 1;
            this.minute = 0;
            if(this.ampm.equals("am")){
                this.ampm = "pm";
            }else{
                this.ampm = "am";
            }
        }
    }

    public String getTime() {
        String hourStr = String.format("%02d", this.hour);
        String minuteStr = String.format("%02d", this.minute);
        return hourStr + ":" + minuteStr + ampm;
    }
    public void deserialize(String s){
        String[] message = s.split("\\|");
        hour = Integer.parseInt(message[0]);
        minute = Integer.parseInt(message[1]);
        ampm = message[2];
        //Season comming soon
        for (Player p : Bukkit.getOnlinePlayers())
            SkyblockScoreboard.updateScoreboard(p);
    }



    @Override
    public void run() {
        if(!hasBungeeCord){
            runVar++;
            if (runVar == 200) {
                incrementTime();
                runVar = 0;
            }
        }else cancel();
    }

    public enum Season{

    }
}
