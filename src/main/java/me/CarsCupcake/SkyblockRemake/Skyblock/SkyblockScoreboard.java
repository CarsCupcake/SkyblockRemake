package me.CarsCupcake.SkyblockRemake.Skyblock;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import me.CarsCupcake.SkyblockRemake.Skyblock.scoreboard.ScoreboardImpl;
import me.CarsCupcake.SkyblockRemake.Skyblock.scoreboard.ScoreboardSection;
import me.CarsCupcake.SkyblockRemake.isles.rift.RiftPlayer;
import me.CarsCupcake.SkyblockRemake.utils.Time;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import me.CarsCupcake.SkyblockRemake.isles.dwarven.DwarvenEvents.DwarvenEvent;
import me.CarsCupcake.SkyblockRemake.isles.dwarven.DwarvenEvents.DwarvenEvents;


public class SkyblockScoreboard implements Listener {

    @EventHandler
    public void sbonJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                updateScoreboard(SkyblockPlayer.getSkyblockPlayer(event.getPlayer()));
            }
        }.runTaskLater(Main.getMain(), 5);


    }

    public static void updateScoreboard(SkyblockPlayer player) {
        int score = 0;
        for (ScoreboardSection scoreboardSection : player.getScoreboardSections()) {
            for (String s : scoreboardSection.score(player)){
                ScoreboardDisplayer.setScore(player, s, score);
                score--;
            }
        }
        if (Math.abs(score) < player.getScoreboard().getEntries().size() - 1) {
            for (int i = Math.abs(score); i <= player.getScoreboard().getEntries().size(); i++) {
                Team t = player.getScoreboard().getTeam("score-" + (-i));
                t.removeEntry(ScoreboardDisplayer.buildString(Math.abs(i)));
                t.unregister();
                player.getScoreboard().resetScores("score-" + (-i));
                player.getScoreboard().resetScores(ScoreboardDisplayer.buildString(Math.abs(i)));
            }
        }
    }

    public static void createScoreboard(SkyblockPlayer player) {
        SkyblockPlayer p = SkyblockPlayer.getSkyblockPlayer(player);
        ScoreboardManager manager = Bukkit.getScoreboardManager();

        assert manager != null;
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("SkyBlockBoard", "dummy", ChatColor.BOLD + "§6§lSKYBLOCK");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(board);
        for (ScoreboardImpl i : ScoreboardImpl.values())
            ScoreboardSection.addToScoreboard(i.getSection(), player);
    }

    public static String getGoneWithTheWindString(SkyblockPlayer player) {
        int dir = DwarvenEvent.ActiveEvent.getGoneWithTheWindYaw();
        int yaw = (int) player.getLocation().getYaw();

        if (Tools.isInRange(dir + 20, dir - 20, yaw)) {

            int spaces = (dir - yaw) / 2;

            String finalstring = " ";
            int totspaces = 10 + spaces;


            for (int i = 0; i < totspaces; i++)
                finalstring += " ";
            finalstring += getWindColor(yaw) + "‰ˆ";

            return finalstring;

        } else {
            if (yaw < dir) {
                return "                      >";
            } else
                return "<";

        }
    }

    public static String getWindColor(int yaw) {
        int dir = DwarvenEvent.ActiveEvent.getGoneWithTheWindYaw();
        if (Tools.isInRange(dir + 5, dir - 5, yaw))
            return "§2";
        if (Tools.isInRange(dir + 15, dir - 15, yaw))
            return "§a";

        return "";
    }
} 
