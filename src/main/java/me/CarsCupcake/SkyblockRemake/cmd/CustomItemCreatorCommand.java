package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Items.customItems.CustomItemsManager;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CustomItemCreatorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player player)) {
            return false;
        }
        if(strings.length != 1) {
            commandSender.sendMessage("§cNot a valid item id!");
            return false;
        }
        CustomItemsManager.edit(strings[0].toUpperCase(), SkyblockPlayer.getSkyblockPlayer(player));
        return true;
    }
}
