package me.CarsCupcake.SkyblockRemake.cmd.impl.generals;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.abilities.BlockZapperAbility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UndoZap implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length < 1)
            return false;
        UUID uuid;
        try {
            uuid = UUID.fromString(strings[0]);
        }catch (Exception ignored){
            commandSender.sendMessage("§cIllegal id!");
            return false;
        }
        if(!BlockZapperAbility.Zapper.zapps.containsKey(uuid))
            return false;
        if(commandSender instanceof Player player) {
            BlockZapperAbility.Zapper zapper = BlockZapperAbility.Zapper.zapps.get(uuid);
            player.sendMessage("§eUnzapped §c" + zapper.getDone() + "§e blocks away!");
            zapper.undoZap(SkyblockPlayer.getSkyblockPlayer(player));
        }
        return false;
    }
}
