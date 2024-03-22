package me.CarsCupcake.SkyblockRemake.cmd.impl.admin;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddAccessoryBagSlots implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender arg0, @NotNull Command arg1, @NotNull String arg2, String[] arg3) {
        if (arg0 instanceof Player && arg3.length == 1 && arg2.equalsIgnoreCase("accessoryslots")) {
            try {
                Integer.parseInt(arg3[0]);
            } catch (Exception e) {
                System.out.println("Wrong Arg Use");
                return false;
            }
            SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) arg0);
            int newSlots = Integer.parseInt(arg3[0]);
            player.setMaxAccessoryBagSlots(newSlots);
            return true;
        }
        return false;
    }
}
