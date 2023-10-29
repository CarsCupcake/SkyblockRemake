package me.CarsCupcake.SkyblockRemake.cmd;


import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.NPC.Questing.DialogBuilder;
import me.CarsCupcake.SkyblockRemake.NPC.disguise.PlayerDisguise;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.scoreboard.ScoreboardSection;
import me.CarsCupcake.SkyblockRemake.isles.Dungeon.mobs.special.Fel;
import me.CarsCupcake.SkyblockRemake.utils.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class testobjectCMD implements CommandExecutor {
    private ScoreboardSection scoreboardSection;
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) sender);
        if (scoreboardSection == null){
            scoreboardSection = new ScoreTest();
            ScoreboardSection.addToScoreboard(scoreboardSection, player);
        } else {
            scoreboardSection.suspendAll();
            scoreboardSection = null;
        }
        return false;
    }
    public static class ScoreTest extends ScoreboardSection {
        @Override
        public String[] score(SkyblockPlayer ingored) {
            return new String[] {"Test", "kek!"};
        }
    }
}
