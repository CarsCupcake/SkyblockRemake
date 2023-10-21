package me.CarsCupcake.SkyblockRemake.Skyblock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardDisplayer {
    public static void setScore(Player p, String score, int ScoreID) {
        Scoreboard board = p.getScoreboard();
        Objective obj = board.getObjective(DisplaySlot.SIDEBAR);
        if (obj == null) return;
        String colorcode = "§" + ScoreID;
        if (ScoreID > 9) {
            if (ScoreID == 10) colorcode = "§a";
            if (ScoreID == 11) colorcode = "§b";
            if (ScoreID == 12) colorcode = "§c";
            if (ScoreID == 13) colorcode = "§d";
            if (ScoreID == 14) colorcode = "§e";
        }
        try {
            Team team = board.getTeam("score-" + ScoreID);
            if (team == null) {
                team = board.registerNewTeam("score-" + ScoreID);
                team.addEntry(colorcode);
                obj.getScore(colorcode).setScore(ScoreID);
            }
            if (score.isEmpty()) // If lenght == 0 set to " " for free space in scoreboard
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


