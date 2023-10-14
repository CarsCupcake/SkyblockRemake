package me.CarsCupcake.SkyblockRemake.utils;

import lombok.Getter;
import me.CarsCupcake.SkyblockRemake.Configs.CustomConfig;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Time extends BukkitRunnable {
    @Getter
    private static Time instance;
    @Getter
    private int day = 1;
    private int hour;
    private int minute;
    private int runVar;
    private String ampm = "am";
    private final boolean hasBungeeCord;
    @Getter
    private Season season = Season.EARLY_SPRING;


    public Time() {
        this.hour = 0;
        this.minute = 0;
        instance = this;
        hasBungeeCord = Main.getMain().checkIfBungee();
        load();
    }
    public void load(){
        if(hasBungeeCord){
            return;
        }
        CustomConfig c = new CustomConfig("time");
        season = Season.getById(c.get().getInt("season", 1));
        day = c.get().getInt("day", 1);
        hour = c.get().getInt("hour", 0);
        minute = c.get().getInt("minute", 0);
        runVar = c.get().getInt("phase", 0);

    }
    public void save(){
        CustomConfig c = new CustomConfig("time");
        c.get().set("season", season.getId());
        c.get().set("day", day);
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
                if(day == 31){
                    day = 0;
                    if(season.getId() == 12)
                        season = Season.getById(1);
                    else
                        season = Season.getById(season.getId() + 1);
                }

                day++;
            }
        }
    }

    public String getTime() {
        String hourStr = String.format("%02d", this.hour);
        String minuteStr = String.format("%02d", this.minute);
        return hourStr + ":" + minuteStr + ampm;
    }
    public String getDaySuffix(){
        return switch (day){
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }
    public void deserialize(String s){
        String[] message = s.split("\\|");
        hour = Integer.parseInt(message[0]);
        minute = Integer.parseInt(message[1]);
        ampm = message[2];
        day = Integer.parseInt(message[3]);
        //Season comming soon
        for (Player p : Bukkit.getOnlinePlayers())
            SkyblockScoreboard.updateScoreboard(p);
    }

    public void setSeason(int i){
        season = Season.getById(i);
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

    @Getter
    public enum Season{
        EARLY_SPRING("Early Spring", 1),
        SPRING("Spring", 2),
        LATE_SPRING("Late Spring", 3),
        EARLY_SUMMER("Early Summer", 4),
        SUMMER("Summer", 5),
        LATE_SUMMER("Late Summer", 6),
        EARLY_AUTUMN("Early Autumn", 7),
        AUTUMN("Autumn", 8),
        LATE_AUTUMN("Late Autumn", 9),
        EARLY_WINTER("Early Winter", 10),
        WINTER("Winter", 11),
        LATE_WINTER("Late Winter", 12);
        private final String name;
        private final int id;

        Season(String s, int id) {
            name = s;
            this.id = id;
        }
        public static Season getById(int i){
            for (Season season : values())
                if(season.getId() == i)
                    return season;
            return null;
        }
    }
}
