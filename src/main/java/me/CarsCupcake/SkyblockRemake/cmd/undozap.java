package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.abilitys.BlockZapperAbility;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class undozap implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.sendMessage("§eUnzapped §c" + BlockZapperAbility.blockAmount.get(player) + "§e blocks away!");
            BlockZapperAbility.undoZap(player);
            player.closeInventory();
        }
        return false;
    }
}
