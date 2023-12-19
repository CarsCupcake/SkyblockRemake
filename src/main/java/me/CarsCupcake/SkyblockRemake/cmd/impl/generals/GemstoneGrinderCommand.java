package me.CarsCupcake.SkyblockRemake.cmd.impl.generals;

import me.CarsCupcake.SkyblockRemake.Items.Gemstones.GemstoneGrinderInv;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GemstoneGrinderCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ((Player) commandSender).openInventory(GemstoneGrinderInv.getInv());
        return false;
    }
}
