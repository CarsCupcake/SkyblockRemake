package me.CarsCupcake.SkyblockRemake.Skyblock.scoreboard;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockScoreboard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ScoreboardSection {
    private final HashMap<SkyblockPlayer, Integer> index = new HashMap<>();
    private final Set<SkyblockPlayer> players = new HashSet<>();
    /**
     *
     * @return the index in the scoreboard
     * throws an exception if its not in the list
     */
    public final int getIndex(SkyblockPlayer player) {
        //TODO
        if (!index.containsKey(player)) throw new IllegalStateException("Not in list!");
        return index.get(player);
    }
    public void suspendAll() {
        for (SkyblockPlayer player : new HashSet<>(players))
            suspend(player);
    }
    public void suspend(SkyblockPlayer player) {
        int index = getIndex(player);
        this.index.remove(player);
        players.remove(player);
        player.getScoreboardSections().remove(index);
    }

    public static void addToScoreboard(int index, ScoreboardSection scoreboardSection, SkyblockPlayer player) {
        player.getScoreboardSections().add(index, scoreboardSection);
        scoreboardSection.players.add(player);
        scoreboardSection.index.put(player, index);
        SkyblockScoreboard.updateScoreboard(player);
    }
    public static void addToScoreboard(ScoreboardSection scoreboardSection, SkyblockPlayer player) {
        addToScoreboard(player.getScoreboardSections().size(), scoreboardSection, player);
    }
    public abstract String[] score(SkyblockPlayer player);
}
