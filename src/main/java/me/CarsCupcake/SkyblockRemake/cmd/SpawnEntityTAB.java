package me.CarsCupcake.SkyblockRemake.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpawnEntityTAB implements TabCompleter {

    List<String> arguments = new ArrayList<>();
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        List<String> result = new ArrayList<>();
        if(arguments.isEmpty()) {
            arguments.add("ICE_WALKER");
            arguments.add("THUNDER");
            arguments.add("LORD_JAWBUS");
            arguments.add("LASR");
            arguments.add("BIGFOOT");
            arguments.add("DIAMOND");
            arguments.add("JOLLY");
            arguments.add("SADAN");
            arguments.add("NECRON");
            arguments.add("MASTERMODE_NECRON");
        }

        if(args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        if (args.length >= 2) {
            for (String a : arguments) {
                a = "";
                result.add(a);
            }
            return result;
        }



        return null;
    }

}
