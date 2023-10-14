package me.CarsCupcake.SkyblockBungee.features;

import lombok.Getter;
import me.CarsCupcake.SkyblockBungee.BungeeConfig;
import me.CarsCupcake.SkyblockBungee.Main;
import me.CarsCupcake.SkyblockBungee.net.ServerHandler;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.concurrent.TimeUnit;

public class Time {
    @Getter
    private static Season season;
    private static int day;
    private static int hour;
    private static int minute;
    private static String ampm;

    public static void addConnection(ServerInfo c) {
        Main.sendMessage("season:" + season.getId(), c);
        Main.sendMessage("time:" + timeString(), c);
    }

    private static void sendTimeMessage() {
        for (ServerHandler sH : ServerHandler.servers.values())
            sH.sendMessage("time:" + timeString());
    }

    public static String timeString() {
        return hour + "|" + minute + "|" + ampm + "|" + day;
    }

    public static void start() {
        BungeeConfig c = new BungeeConfig("time");
        season = Season.getById(c.get().getInt("season", 1));
        day = c.get().getInt("day", 1);
        hour = c.get().getInt("hour", 0);
        minute = c.get().getInt("minute", 0);
        ampm = c.get().getString("ampm", "am");

        Main.getMain().getProxy().getScheduler().schedule(Main.getMain(), () -> {
            incrementTime();
            sendTimeMessage();
        }, 10, 10, TimeUnit.SECONDS);
    }

    private static void incrementTime() {
        minute += 10;
        if (minute == 60) {
            hour++;
            minute = 0;
        }
        if (hour > 12) {
            hour = 1;
            minute = 0;
            if (ampm.equals("am")) {
                ampm = "pm";
            } else {
                ampm = "am";
                if (day == 31) {
                    day = 0;
                    if (season.getId() == 12) {
                        season = Season.EARLY_SPRING;
                    } else season = Season.getById(season.getId() + 1);
                    for (ServerHandler sH : ServerHandler.servers.values()) {
                        sH.sendMessage("season:" + season.getId());
                    }

                }

                day++;
            }
        }
    }

    public static void stop() {
        BungeeConfig c = new BungeeConfig("time");
        c.get().set("season", season.getId());
        c.get().set("day", day);
        c.get().set("hour", hour);
        c.get().set("minute", minute);
        c.get().set("ampm", ampm);
        c.save();
    }

    @Getter
    public enum Season {
        EARLY_SPRING("Early Spring", 1), SPRING("Spring", 2), LATE_SPRING("Late Spring", 3), EARLY_SUMMER("Early Summer", 4), SUMMER("Summer", 5), LATE_SUMMER("Late Summer", 6), EARLY_AUTUMN("Early Autumn", 7), AUTUMN("Autumn", 8), LATE_AUTUMN("Late Autumn", 9), EARLY_WINTER("Early Winter", 10), WINTER("Winter", 11), LATE_WINTER("Late Winter", 12);
        private final String name;
        private final int id;

        Season(String s, int id) {
            name = s;
            this.id = id;
        }

        public static Season getById(int i) {
            for (Season season : values())
                if (season.getId() == i) return season;
            return null;
        }
    }
}

