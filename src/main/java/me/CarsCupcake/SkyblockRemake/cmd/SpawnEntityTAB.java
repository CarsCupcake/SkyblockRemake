package me.CarsCupcake.SkyblockRemake.cmd;

import me.CarsCupcake.SkyblockRemake.Entitys.IceWalker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpawnEntityTAB implements TabCompleter {

    List<String> arguments = new ArrayList<>();
    @Nullable
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        List<String> result = new ArrayList<>();
        if(arguments.isEmpty()) {
            arguments.add("ICE_WALKER");
            arguments.add("THUNDER");
            arguments.add("LORD_JAWBUS");
            arguments.add("SUPERIOR_DRAGON");
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
