package me.CarsCupcake.SkyblockRemake.cmd.impl.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class particletest implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender arg0, @NotNull Command arg1, String arg2, String[] arg3) {
        if (arg2.equalsIgnoreCase("part"))
            testobjectCMD.tentacle.mirroredPoints = !testobjectCMD.tentacle.mirroredPoints;
        return false;
    }

}
