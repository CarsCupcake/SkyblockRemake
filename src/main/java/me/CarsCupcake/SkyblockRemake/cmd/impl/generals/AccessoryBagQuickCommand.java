package me.CarsCupcake.SkyblockRemake.cmd.impl.generals;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.AccessoryBag.AccessoryInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AccessoryBagQuickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) commandSender);
        if (player.getMaxAccessoryBagSlots() < 1) {
            player.sendMessage("§cYou do not have unlocked this!");
            return true;
        }
        new AccessoryInventory(player).open();
        return true;
    }
}
