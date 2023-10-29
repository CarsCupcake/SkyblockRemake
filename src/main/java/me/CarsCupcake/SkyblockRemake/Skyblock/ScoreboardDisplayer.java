package me.CarsCupcake.SkyblockRemake.Skyblock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardDisplayer {
    public static void setScore(Player p, String score, int scoreID) {
        Scoreboard board = p.getScoreboard();
        Objective obj = board.getObjective(DisplaySlot.SIDEBAR);
        if (obj == null) return;
        try {
            Team team = board.getTeam("score-" + scoreID);
            if (team == null) {
                team = board.registerNewTeam("score-" + scoreID);
                String entry = buildString(Math.abs(scoreID));
                team.addEntry(entry);
                obj.getScore(entry).setScore(scoreID);
            }
            if (score.isEmpty())
                score = " ";
            String[] s = getScorePrefixSuffix(score, 64, 999);
            try {
                team.setPrefix(s[0]);
                team.setSuffix(s[1]);
            } catch (IllegalArgumentException e) {
                team.setPrefix(ChatColor.RED + "-too long-");
                Bukkit.broadcastMessage("an error occured while creating the scoreboard");

            }
        } catch (IllegalStateException ignored) {}
    }
    public static String buildString(int s) {
        StringBuilder b = new StringBuilder();
        for (char c : String.valueOf(s).toCharArray())
            b.append("§").append(c);
        return b.toString();
    }
    //if(sm != null)
    //sm.addPlayer(p);


    public static String[] getScorePrefixSuffix(String score, int split, int maxchars) {
        String[] s = new String[2];
        if (score.length() > maxchars) return null;

        if (score.length() > split) { // Check if suffix is needed
            s[0] = score.substring(0, split); // Set the prefix
            s[1] = ChatColor.getLastColors(s[0]) + score.substring(split); // Get last color + everything in the string after the split
        } else {
            s[0] = score; // Set prefix
            s[1] = "";
        }
        return s;
    }
}


